package service;

import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.ProductDto;
import dto.ShopDto;
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
import java.util.List;
import java.util.Optional;
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

    /*@Override
    public List<CartProductDto> findByIdIn(List<Long> ids) {
        List<Product> products = productRepository.findAllByIdIn(ids);
        List<CartProductDto> productDtos = new ArrayList<>();
        products.stream().map(it -> productDtos.add(new CartProductDto().builder()
                .productName(it.getDescription())
                .productImage(it.getImage())
                .options(it.getOptions())
                .availableQuantity()
                .build()))
    }*/

    @Override
    public Product findByIdWithShops(Long id) {
        return productRepository.findById(id).get();
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

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public CatalogPageDto findDistinctAllByCategory(Category category, Pageable pageable) {
        List<CatalogDto> catalogDtoList = new ArrayList<>();

        Page<Product> products = productRepository.findDistinctAllByCategory(category, pageable);
        for (Product product : products) {

            List<ShopProduct> notEmptyShopProductList = product.getShopProduct()
                    .stream()
                    .filter(it -> it.getQuantity() > 0)
                    .collect(Collectors.toList());

            catalogDtoList.add(new CatalogDto().builder()
                    .id(product.getId())
                    .productName(product.getDescription())
                    .productImage(product.getImage())
                    .rating(product.getReviews().stream().mapToDouble(ReviewProduct::getRating).average().orElse(Double.NaN))
                    .options(product.getOptions())
                    .maxPrice(notEmptyShopProductList.stream().map(ShopProduct::getPrice).max(Comparator.comparing(Integer::valueOf)).orElse(0))
                    .minPrice(notEmptyShopProductList.stream().map(ShopProduct::getPrice).min(Comparator.comparing(Integer::valueOf)).orElse(0))
                    .offers(notEmptyShopProductList.size())
                    .build());
        }

        return new CatalogPageDto(catalogDtoList, products.getTotalPages());
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findFirstByDescription(name);
    }
}
