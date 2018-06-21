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
public class CatalogPageDto {

    private List<CatalogDto> products;
    private List<Option> options;
    private Integer pages;

    public CatalogPageDto(List<CatalogDto> products, Integer pages) {
        this.products = products;
        this.pages = pages;
    }
}
