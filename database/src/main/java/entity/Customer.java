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
@Table(name = "customer", schema = "shop")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {

    @Column(name = "address", nullable = false, unique = false)
    private String address;

    public Customer(String login, String password, Role role, UserInfo userInfo, String address) {
        super(login, password, role, userInfo);
        this.address = address;
    }
}
