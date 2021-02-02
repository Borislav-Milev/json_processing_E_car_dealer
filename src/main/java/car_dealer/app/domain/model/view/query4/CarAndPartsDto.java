package car_dealer.app.domain.model.view.query4;

import car_dealer.app.domain.model.view.PartDto;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarAndPartsDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Long travelledDistance;

    @Expose
    private PartDto[] parts;
}
