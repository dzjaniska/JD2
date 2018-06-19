package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private String userName;
    private String userSecondName;
    private String text;
    private Integer rating;
    private LocalDate date;
    private Long productId;
    private Long shopId;
}
