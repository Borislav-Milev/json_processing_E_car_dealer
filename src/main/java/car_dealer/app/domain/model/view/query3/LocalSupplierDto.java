package car_dealer.app.domain.model.view.query3;

import com.google.gson.annotations.Expose;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LocalSupplierDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private Long partsCount;
}
