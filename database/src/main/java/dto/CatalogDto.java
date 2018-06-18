package dto;

import entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogDto {

    private Long id;
    private String productName;
    private String productImage;
    private Double rating;

    private List<Option> options;

    private Integer minPrice;
    private Integer maxPrice;

    private Integer offers;
}
