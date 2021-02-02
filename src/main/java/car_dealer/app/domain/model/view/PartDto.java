package car_dealer.app.domain.model.view;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PartDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;
}
