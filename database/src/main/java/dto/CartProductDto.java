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
public class CartProductDto {

    private Long productId;
    private String productName;
    private String productImage;
    private List<Option> options;

    private Integer price;

    private Long shopId;
    private Integer availableQuantity;
    private Long version;
}
