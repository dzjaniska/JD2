package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(callSuper = true,exclude = "shop")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin", schema = "shop")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false, unique = true)
    private Shop shop;

    public Admin(String login, String password, Role role, UserInfo userInfo, Shop shop) {
        super(login, password, role, userInfo);
        this.shop = shop;
    }
}
