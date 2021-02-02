package car_dealer.app.domain.model.view.query2;

import car_dealer.app.domain.model.view.CarDto;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToyotaCarDto extends CarDto {

    @Expose
    private Integer id;
}
