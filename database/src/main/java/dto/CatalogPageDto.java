package dto;

import entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogPageDto {

    private List<CatalogDto> products;
    private Map<Parameter, List<OptionDto>> options;
    private Integer pages;
}
