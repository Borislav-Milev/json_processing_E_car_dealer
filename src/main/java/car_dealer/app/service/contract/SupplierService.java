package car_dealer.app.service.contract;

import car_dealer.app.domain.entity.Supplier;

public interface SupplierService {

    void seedSuppliers(String jsonSuppliers);

    Supplier getRandomSupplier();

    Supplier findSupplierById(Integer id);

    String getLocalSuppliers();
}
