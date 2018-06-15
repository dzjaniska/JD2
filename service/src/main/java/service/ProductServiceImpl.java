package service;

import dto.ProductDto;
import dto.ShopDto;
import entity.Category;
import entity.Product;
import entity.Shop;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

import java.util.ArrayList;
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

    @Override
    public Product findByIdWithShops(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDto findByIdWithShopsDto(Long id) {
        Product product = productRepository.findById(id).get();
        Set<ShopProduct> shopProduct = product.getShopProduct();
        List<ShopDto> shops = new ArrayList<>();
        shopProduct.forEach(it ->
                        shops.add(new ShopDto(
                                it.getShop().getLogo(),
                                it.getShop().getName(),
                                it.getPrice()
                        )));

        return new ProductDto(product.getDescription(), product.getImage(), product.getOptions(),shops);
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
    public List<Product> findDistinctAllByCategory(Category category) {
        return productRepository.findDistinctAllByCategory(category);
    }

    @Override
    public List<Product> findAllCategoryOrderByMinPrice(Category category) {
        return productRepository.findAllCategoryOrderByMinPrice(category);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findFirstByDescription(name);
    }
}
