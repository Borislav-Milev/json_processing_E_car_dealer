package car_dealer.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SeedCustomerDto {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String birthDate;

    @Expose
    @NotNull
    private Boolean isYoungDriver;
}
