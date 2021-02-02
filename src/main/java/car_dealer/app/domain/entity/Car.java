package car_dealer.app.domain.entity;

import car_dealer.app.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @NonNull
    @NotNull
    @Column(nullable = false, length = 50)
    private String make;

    @NonNull
    @NotNull
    @Column(nullable = false, length = 100)
    private String model;

    @NonNull
    @NotNull
    @Column(nullable = false)
    @PositiveOrZero
    private Long travelledDistance;

    @ManyToMany
    @ToString.Exclude
    private List<Part> parts = new ArrayList<>();
}
