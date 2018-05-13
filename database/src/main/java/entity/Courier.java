package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courier", schema = "shop")
@PrimaryKeyJoinColumn(name = "user_id")
public class Courier extends User {

    @Column(name = "car_type", nullable = false, unique = false)
    private CarType carType;

    public Courier(String login, String password, Role role, UserInfo userInfo, CarType carType) {
        super(login, password, role, userInfo);
        this.carType = carType;
    }
}
