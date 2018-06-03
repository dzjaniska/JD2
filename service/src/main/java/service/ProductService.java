package service;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> productPag(Category category, Option option, Integer startPrice, Integer endPrice, Integer page, Integer size) {
        return productRepository.findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(category, option, startPrice, endPrice, PageRequest.of(page, size));
    }
}
