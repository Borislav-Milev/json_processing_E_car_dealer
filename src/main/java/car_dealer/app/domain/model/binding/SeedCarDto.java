package car_dealer.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
public class SeedCarDto {

    @Expose
    @NotNull
    private String make;

    @Expose
    @NonNull
    private String model;

    @Expose
    @NotNull
    @PositiveOrZero
    private Long travelledDistance;
}
