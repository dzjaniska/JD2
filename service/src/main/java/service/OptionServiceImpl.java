package service;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OptionRepository;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "oprions")
public class OptionServiceImpl implements OptionService {

    private ProductRepository productRepository;
    private OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(ProductRepository productRepository, OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    @Cacheable
    public List<Option> findAllByCategory(Category category) {
        return optionRepository.findAllByCategory(category);
    }
}
