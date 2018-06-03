package repository;

import entity.Option;
import entity.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

    Option findByNameAndValue(Parameter parameter, String value);
}
