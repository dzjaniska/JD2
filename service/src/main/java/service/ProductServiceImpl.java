package service;

import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.OptionDto;
import dto.ProductDto;
import dto.ReviewDto;
import dto.ShopDto;
import dto.ShopProductDto;
import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
import entity.ReviewProduct;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OptionRepository;
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
    private OptionRepository optionRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
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
    public CatalogPageDto findDistinctAllByCategoryAndOptions(Category category, Long[] ids, String sort, Pageable pageable) {
        List<Option> options = optionRepository.findDistinctByCategory(category);
        Map<Parameter, List<OptionDto>> optionsMap = createOptionDtoMap(options);
        List<List<Long>> targetIdList = new ArrayList<>();
        if (ids != null) {
            targetIdList = getIdsList(createOptionDtoMap(optionRepository.findDistinctByIdIn(ids)));
        }

        List<Product> products = productRepository.findDistinctAllByCategoryAndOptionsCustom(category, targetIdList, sort, pageable);
        List<CatalogDto> catalogDtoList = createCatalogDtoList(products);

        return new CatalogPageDto(getProductsOnPage(pageable, catalogDtoList), optionsMap, getPagesNumber(pageable, catalogDtoList));
    }

    private List<List<Long>> getIdsList(Map<Parameter, List<OptionDto>> optionsMap) {
        List<List<Long>> idsList = new ArrayList<>();
        for (Map.Entry<Parameter, List<OptionDto>> entry : optionsMap.entrySet()) {
            idsList.add(entry.getValue().stream().map(OptionDto::getId).collect(Collectors.toList()));
        }
        return idsList;
    }

    private Map<Parameter, List<OptionDto>> createOptionDtoMap(List<Option> options) {
        Map<Parameter, List<OptionDto>> optionsMap = new HashMap<>();
        options.stream().forEach(it -> {
            if (optionsMap.containsKey(it.getName())) {
                optionsMap.get(it.getName()).add(new OptionDto(it.getId(), it.getValue()));
            } else {
                List<OptionDto> optionDtos = new ArrayList<>();
                optionDtos.add(new OptionDto(it.getId(), it.getValue()));
                optionsMap.put(it.getName(), optionDtos);
            }
        });
        return optionsMap;
    }

    private <T extends Iterable<Product>> List<CatalogDto> createCatalogDtoList(T products) {
        List<CatalogDto> catalogDtoList = new ArrayList<>();

        products.forEach(it -> catalogDtoList.add(new CatalogDto().builder()
                .id(it.getId())
                .productName(it.getDescription())
                .productImage(it.getImage())
                .rating(it.getReviews().stream().mapToDouble(ReviewProduct::getRating).average().orElse(0.0))
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
}
