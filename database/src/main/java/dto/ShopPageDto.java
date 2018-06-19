package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopPageDto {

    private Long id;
    private String shopLogo;
    private String shopName;
    private String description;

    private Double rating;

    private Set<ReviewDto> reviews;
}
