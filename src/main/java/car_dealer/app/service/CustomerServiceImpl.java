package car_dealer.app.service;

import car_dealer.app.domain.entity.Customer;
import car_dealer.app.domain.entity.Part;
import car_dealer.app.domain.entity.Sale;
import car_dealer.app.domain.model.binding.SeedCustomerDto;
import car_dealer.app.domain.model.view.query1.OrderedCustomerDto;
import car_dealer.app.domain.model.view.query5.CustomerTotalSalesDto;
import car_dealer.app.repository.CustomerRepository;
import car_dealer.app.service.contract.CustomerService;
import car_dealer.app.util.contract.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final DecimalFormat format = new DecimalFormat("##################.##");

    public CustomerServiceImpl(CustomerRepository customerRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCustomers(String jsonCustomers) {
        if (this.customerRepository.count() != 0) {
            return;
        }
        System.out.println();
        SeedCustomerDto[] seedCustomerDtos = this.gson.fromJson(jsonCustomers, SeedCustomerDto[].class);

        for (SeedCustomerDto seedCustomerDto : seedCustomerDtos) {
            if (this.validatorUtil.ifNotValidPrintViolations(seedCustomerDto)) {
                return;
            }
            Customer customer = this.modelMapper.map(seedCustomerDto, Customer.class);
            customer.setBirthDate(LocalDate.parse(seedCustomerDto.getBirthDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.customerRepository.count());
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such customer."));
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        return this.findCustomerById(random.nextInt(this.getCount()) + 1);
    }

    @Override
    public String getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.orderedCustomers();

        List<OrderedCustomerDto> orderedCustomerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            orderedCustomerDtos.add(this.modelMapper.map(customer, OrderedCustomerDto.class));
        }

        return this.gson.toJson(orderedCustomerDtos);
    }

    @Override
    @Transactional
    public String getCustomersTotalSales() {
        List<Customer> customers = this.customerRepository.customerTotalSales();
        List<CustomerTotalSalesDto> customerTotalSalesDtos = new ArrayList<>();

        for (Customer customer : customers) {
            customerTotalSalesDtos.add(this.modelMapper.map(customer, CustomerTotalSalesDto.class));
        }

        for (int i = 0; i < customers.size(); i++) {
            customerTotalSalesDtos.get(i).setBoughtCars(customers.get(i).getSales().size());
            double spentMoney = 0;
            for (Sale sale : customers.get(i).getSales()) {
                double totalPriceSale = 0;
                for (Part part : sale.getCar().getParts()) {
                    totalPriceSale += part.getPrice().doubleValue();
                }
                spentMoney += totalPriceSale - totalPriceSale * sale.getDiscount();
            }
            customerTotalSalesDtos.get(i).setSpentMoney(this.round(spentMoney));
        }
        return this.gson.toJson(customerTotalSalesDtos);
    }

    @Override
    public double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
