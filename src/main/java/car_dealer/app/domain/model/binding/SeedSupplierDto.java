package car_dealer.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class SeedSupplierDto {

    @Expose
    @NotNull
    @Length(max = 200)
    private String name;

    @Expose
    @NotNull
    private Boolean isImporter;
}
