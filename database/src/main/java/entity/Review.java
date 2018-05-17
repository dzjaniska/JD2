package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review", schema = "shop")
public class Review extends BaseEntity<Long> {

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
