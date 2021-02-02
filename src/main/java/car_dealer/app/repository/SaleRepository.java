package car_dealer.app.repository;

import car_dealer.app.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    //Query6
    @Query("select s from Sale s")
    List<Sale> salesDiscounts();
}
