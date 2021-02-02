package car_dealer.app.service.contract;

import car_dealer.app.domain.entity.Car;

import java.util.List;

public interface CarService {

    void seedCars(String jsonCars);

    List<Car> getAllCars();

    //Query3
    String getToyotaCars();

    //Query4
    String getCarsAndParts();
}
