package car_dealer.app.domain.model.view.query1;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedCustomerDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private Boolean isYoungDriver;
}
