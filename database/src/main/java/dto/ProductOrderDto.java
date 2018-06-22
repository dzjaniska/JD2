package dto;

import entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDto {

    private Long productId;
    private Orders orders;
    private Long shopId;
    private Integer quantity;
    private Long version;
}
