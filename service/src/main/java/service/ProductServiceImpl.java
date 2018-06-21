package service;

import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.ProductDto;
import dto.ReviewDto;
import dto.ShopDto;
import dto.ShopProductDto;
import entity.Category;
import entity.Product;
import entity.ReviewProduct;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public CatalogDto findByIdCatalogItem(Long id) {
        Product product = productRepository.findById(id).get();

        return new CatalogDto().builder()
                .id(product.getId())
                .productName(product.getDescription())
                .productImage(product.getImage())
                .rating(product.getReviews().stream().mapToDouble(ReviewProduct::getRating).average().orElse(0.0))
                .options(product.getOptions())
                .maxPrice(product.getShopProduct().stream().map(ShopProduct::getPrice).max(Comparator.comparing(Integer::valueOf)).orElse(0))
                .minPrice(product.getShopProduct().stream().map(ShopProduct::getPrice).min(Comparator.comparing(Integer::valueOf)).orElse(0))
                .offers(product.getShopProduct().stream().filter(it -> it.getQuantity() > 0).collect(Collectors.toSet()).size())
                .reviews(product.getReviews().stream().map(it -> new ReviewDto().builder()
                        .userName(it.getUser().getUserInfo().getName())
                        .userSecondName(it.getUser().getUserInfo().getSurname())
                        .rating(it.getRating())
                        .text(it.getText())
                        .date(it.getDate())
                        .build())
                        .sorted(Comparator.comparing(ReviewDto::getDate).reversed())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public ProductDto findByIdWithShopsDto(Long id) {
        Product product = productRepository.findById(id).get();
        Set<ShopProduct> shopProduct = product.getShopProduct().stream().filter(it -> it.getQuantity() > 0).collect(Collectors.toSet());
        List<ShopDto> shops = new ArrayList<>();
        shopProduct.forEach(it ->
                shops.add(new ShopDto(
                        it.getShop().getId(),
                        it.getShop().getLogo(),
                        it.getShop().getName(),
                        it.getPrice()
                )));

        return new ProductDto(product.getId(), product.getDescription(), product.getImage(), product.getOptions(), shops);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //    for admin search
    @Override
    public List<ShopProductDto> findAllByDescriptionContainingIgnoreCase(String name) {
        List<ShopProductDto> productDtos = new ArrayList<>();

        List<Product> products = productRepository.findAllByDescriptionContainingIgnoreCase(name);
        products.forEach(it -> productDtos.add(new ShopProductDto().builder()
                .productName(it.getDescription())
                .productImage(it.getImage())
                .productId(it.getId())
                .build()));

        return productDtos;
    }

    //    for user search
    @Override
    public List<CatalogDto> findAllByDescriptionContainingIgnoreCaseCatalog(String name) {
        List<Product> products = productRepository.findAllByDescriptionContainingIgnoreCaseCatalog(name.toLowerCase());

        return createCatalogDtoList(products);
    }

    @Override
    public CatalogPageDto findDistinctAllByCategoryAndOptionsOrderByPrice(Category category, Long id, Pageable pageable) {
        Page<Product> products = productRepository.findDistinctAllByCategoryAndOptionsOrderByPrice(category, id, pageable);
        List<CatalogDto> catalogDtoList = createCatalogDtoList(products);

        return new CatalogPageDto(catalogDtoList, products.getTotalPages());
    }

    @Override
    public CatalogPageDto findDistinctAllByCategory(Category category, Pageable pageable) {
        Page<Product> products = productRepository.findDistinctAllByCategory(category, pageable);
        List<CatalogDto> catalogDtoList = createCatalogDtoList(products);

        return new CatalogPageDto(catalogDtoList, products.getTotalPages());
    }

    @Override
    public CatalogPageDto findDistinctAllByCategoryAndOptions(Category category, Long[] ids) {
        List<Product> products = productRepository.findDistinctAllByCategoryAndOptionsCustom(category, ids);
        List<CatalogDto> catalogDtoList = createCatalogDtoList(products);

        return new CatalogPageDto();
    }

   /* @Override
    public CatalogPageDto findDistinctAllByCategoryAndOptions(Category category, Long[] ids, Pageable pageable) {
        final int size = ids.length;
        Map<Product, Integer> productMap = addProductsToMap(category, ids);
        List<Product> productList = getAllOptionsProducts(size, productMap);
        List<CatalogDto> catalogDtoList = createCatalogDtoList(productList);

        return new CatalogPageDto(getProductsOnPage(pageable, catalogDtoList), getPagesNumber(pageable, catalogDtoList));
    }*/

    private <T extends Iterable<Product>> List<CatalogDto> createCatalogDtoList(T products) {
        List<CatalogDto> catalogDtoList = new ArrayList<>();

        products.forEach(it -> catalogDtoList.add(new CatalogDto().builder()
                .id(it.getId())
                .productName(it.getDescription())
                .productImage(it.getImage())
                .rating(it.getReviews().stream().mapToDouble(ReviewProduct::getRating).average().orElse(Double.NaN))
                .options(it.getOptions())
                .maxPrice(it.getShopProduct().stream().map(ShopProduct::getPrice).max(Comparator.comparing(Integer::valueOf)).orElse(0))
                .minPrice(it.getShopProduct().stream().map(ShopProduct::getPrice).min(Comparator.comparing(Integer::valueOf)).orElse(0))
                .offers((int) it.getShopProduct().stream().filter(thus -> thus.getQuantity() > 0).count())
                .build()));

        return catalogDtoList;
    }

    private List<CatalogDto> getProductsOnPage(Pageable pageable, List<CatalogDto> catalogDtoList) {
        return catalogDtoList.stream().skip(pageable.getOffset()).limit(pageable.getPageSize()).collect(Collectors.toList());
    }

    private int getPagesNumber(Pageable pageable, List<CatalogDto> catalogDtoList) {
        return (int) Math.ceil((double) catalogDtoList.size() / pageable.getPageSize());
    }

    private Map<Product, Integer> addProductsToMap(Category category, Long[] ids) {
        Map<Product, Integer> prods = new HashMap<>();

        for (Long optionId : ids) {
            productRepository.findDistinctAllByCategoryAndOptions(category, optionId).forEach(it -> {
                if (prods.containsKey(it)) {
                    prods.put(it, prods.get(it) + 1);
                } else {
                    prods.put(it, 1);
                }
            });
        }

        return prods;
    }

    private List<Product> getAllOptionsProducts(int targetSize, Map<Product, Integer> prods) {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : prods.entrySet()) {
            if (entry.getValue() == targetSize) {
                productList.add(entry.getKey());
            }
        }

        return productList;
    }
}
