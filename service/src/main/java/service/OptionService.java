package service;

import entity.Category;
import entity.Option;

import java.util.List;

public interface OptionService {

    Option save(Option option);

    List<Option> findDistinctByCategory(Category category);

}
