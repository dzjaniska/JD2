package service;

import entity.Category;
import entity.Option;

import java.util.List;

public interface OptionService {

    List<Option> findAllByCategory(Category category);
}
