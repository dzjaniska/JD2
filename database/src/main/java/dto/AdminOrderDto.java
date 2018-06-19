package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminOrderDto {

    private Long orderId;

    private Long userId;
    private String userName;
    private String userSecondName;
    private String address;

    private Long productId;
    private String productName;
    private String productImage;
    private Integer quantity;

    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
}
