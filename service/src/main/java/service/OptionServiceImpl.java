package service;

import entity.Category;
import entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OptionRepository;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    private ProductRepository productRepository;
    private OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(ProductRepository productRepository, OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public List<Option> findAllByCategory(Category category) {
        return optionRepository.findAllByCategory(category);
    }

    @Override
    public List<Option> findAll() {
        List<Option> options = new ArrayList<>();
        optionRepository.findAll().forEach(options::add);
        return options;
    }
}
