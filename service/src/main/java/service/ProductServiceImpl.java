package service;

import entity.Category;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

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
