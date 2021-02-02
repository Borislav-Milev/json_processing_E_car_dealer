package car_dealer.app.service.contract;

import car_dealer.app.domain.entity.Customer;

import java.util.List;

public interface CustomerService {

    void seedCustomers(String jsonCustomers);

    int getCount();

    Customer findCustomerById(Integer id);

    Customer getRandomCustomer();

    //Query1
    String getOrderedCustomers();

    String getCustomersTotalSales();

    double round(double value);
}
