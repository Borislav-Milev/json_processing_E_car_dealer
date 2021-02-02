package car_dealer.app.service;

import car_dealer.app.domain.entity.Car;
import car_dealer.app.domain.model.binding.SeedCarDto;
import car_dealer.app.domain.model.view.query2.ToyotaCarDto;
import car_dealer.app.domain.model.view.query4.CarAndPartsDto;
import car_dealer.app.repository.CarRepository;
import car_dealer.app.service.contract.CarService;
import car_dealer.app.service.contract.PartService;
import car_dealer.app.util.contract.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final PartService partService;
    private final CarRepository carRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CarServiceImpl(PartService partService, CarRepository carRepository,
                          ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.partService = partService;
        this.carRepository = carRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCars(String jsonCars) {
        if (this.carRepository.count() != 0) {
            return;
        }
        SeedCarDto[] seedCarDtos = this.gson.fromJson(jsonCars, SeedCarDto[].class);

        System.out.println();
        for (SeedCarDto seedCarDto : seedCarDtos) {
            if (this.validatorUtil.ifNotValidPrintViolations(seedCarDto)) {
                return;
            }
            Car car = this.modelMapper.map(seedCarDto, Car.class);
            System.out.println();
            car.getParts().addAll(this.partService.getRandomParts());
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public List<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    @Override
    public String getToyotaCars() {
        List<ToyotaCarDto> toyotaCarDtos = new ArrayList<>();
        List<Car> cars = this.carRepository.toyotaCars();

        for (Car car : cars) {
            toyotaCarDtos.add(this.modelMapper.map(car, ToyotaCarDto.class));
        }

        return this.gson.toJson(toyotaCarDtos);
    }

    @Override
    @Transactional
    public String getCarsAndParts() {
        List<Car> cars = new ArrayList<>(this.carRepository.carsAndParts());
        List<CarAndPartsDto> carAndPartsDtos = new ArrayList<>();
        for (Car car : cars) {
            carAndPartsDtos.add(this.modelMapper.map(car, CarAndPartsDto.class));
        }
        return this.gson.toJson(carAndPartsDtos);
    }
}
