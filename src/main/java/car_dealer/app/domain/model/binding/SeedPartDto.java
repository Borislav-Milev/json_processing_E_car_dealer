package car_dealer.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class SeedPartDto {

    @Expose
    @NonNull
    @NotNull
    private String name;

    @Expose
    @NonNull
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Expose
    @NonNull
    @NotNull
    @PositiveOrZero
    private Short quantity;
}
