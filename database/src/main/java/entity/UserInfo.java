package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info", schema = "shop")
public class UserInfo extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "surname", nullable = true, unique = false)
    private String surname;

    @Column(name = "second_name", nullable = true, unique = false)
    private String secondName;
}

