package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProductDto {

    private Long productId;
    private String productName;
    private String productImage;
    private Long shopId;
    private String shopLogo;
    private Integer quantity;
}
