package service;

import entity.Category;
import entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OptionRepository;
import repository.ProductRepository;

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
    public List<Option> findAllByCategory(Category category) {
        return optionRepository.findAllByCategory(category);
    }
}
