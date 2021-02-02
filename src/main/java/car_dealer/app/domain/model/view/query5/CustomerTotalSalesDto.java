package car_dealer.app.domain.model.view.query5;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerTotalSalesDto {

    @Expose
    private String name;

    @Expose
    private Integer boughtCars;

    @Expose
    private double spentMoney;
}
