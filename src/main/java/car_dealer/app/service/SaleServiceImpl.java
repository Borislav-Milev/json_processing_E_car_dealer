package car_dealer.app.service;

import car_dealer.app.domain.entity.Car;
import car_dealer.app.domain.entity.Customer;
import car_dealer.app.domain.entity.Part;
import car_dealer.app.domain.entity.Sale;
import car_dealer.app.domain.model.view.CarDto;
import car_dealer.app.domain.model.view.query6.SaleWithDiscountDto;
import car_dealer.app.repository.SaleRepository;
import car_dealer.app.service.contract.CarService;
import car_dealer.app.service.contract.CustomerService;
import car_dealer.app.service.contract.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SaleServiceImpl implements SaleService {

    private final CustomerService customerService;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final Gson gson;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService,
                           CustomerService customerService, ModelMapper modelMapper, Gson gson) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void seedSales() {
        if (this.saleRepository.count() != 0) {
            return;
        }
        Double[] discounts = new Double[]{1d, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};

        for (Car car : this.carService.getAllCars()) {
            Random random = new Random();
            Sale sale = new Sale();
            sale.setCar(car);
            Customer customer = this.customerService.getRandomCustomer();
            sale.setCustomer(customer);

            if (customer.getIsYoungDriver()) {
                sale.setDiscount(Arrays.copyOfRange(discounts, 1, discounts.length)
                        [random.nextInt(discounts.length - 1)]);
            } else sale.setDiscount(discounts[random.nextInt(discounts.length)]);

            this.saleRepository.saveAndFlush(sale);
        }
    }

    @Override
    @Transactional
    public String getSalesWithDiscount() {
        List<Sale> sales = this.saleRepository.salesDiscounts();
        List<SaleWithDiscountDto> saleWithDiscountDtos = new ArrayList<>();

        for (Sale sale : sales) {
            saleWithDiscountDtos.add(this.modelMapper.map(sale, SaleWithDiscountDto.class));
        }
        System.out.println();
        for (int i = 0; i < sales.size(); i++) {

            double totalPriceSale = 0;
            for (Part part : sales.get(i).getCar().getParts()) {
                totalPriceSale += part.getPrice().doubleValue();
            }

            saleWithDiscountDtos.get(i).setPriceWithoutDiscount(this.customerService.round(totalPriceSale));
            totalPriceSale -= totalPriceSale * sales.get(i).getDiscount();
            saleWithDiscountDtos.get(i).setPrice(this.customerService.round(totalPriceSale));
        }

        return this.gson.toJson(saleWithDiscountDtos);
    }


}
