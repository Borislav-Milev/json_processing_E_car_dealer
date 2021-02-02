package car_dealer.app.domain.model.view.query6;

import car_dealer.app.domain.model.view.CarDto;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SaleWithDiscountDto {

    @Expose
    private CarDto car;


    @Expose
    private String customerName;

    @Expose
    private Double discount;

    @Expose
    private double price;

    @Expose
    private double priceWithoutDiscount;
}
