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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "product")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option", schema = "shop")
public class Option extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Parameter name;

    @ManyToMany(mappedBy = "options")
    private Set<Product> products;

    @Column(name = "value", nullable = false)
    private String value;

    public Option(Parameter name, String value) {
        this.name = name;
        this.value = value;
    }
}
