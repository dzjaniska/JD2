package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route", schema = "shop")
public class Route extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "courier_id")
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "order_route", schema = "shop",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Orders> orders;

    public Route(User user, LocalDate date, Status status) {
        this.user = user;
        this.date = date;
        this.status = status;
    }
}
