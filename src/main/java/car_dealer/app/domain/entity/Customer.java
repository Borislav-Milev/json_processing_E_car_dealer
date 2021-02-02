package car_dealer.app.domain.entity;

import car_dealer.app.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @Column(nullable = false)
    @Past
    private LocalDate birthDate;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer")
    private List<Sale> sales = new ArrayList<>();
}
