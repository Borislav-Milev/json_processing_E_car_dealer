package car_dealer.app.controller;

import car_dealer.app.queryManager.Manager;
import car_dealer.app.service.contract.*;
import car_dealer.app.util.contract.FileIO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static car_dealer.app.constant.FilePath.*;

@Component
public class AppController implements CommandLineRunner {

    private final Manager manager;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final SaleService saleService;
    private final CarService carService;
    private final FileIO fileIO;

    public AppController(Manager manager, CustomerService customerService, SupplierService supplierService,
                         CarService carService, PartService partService, SaleService saleService, FileIO fileIO) {
        this.manager = manager;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.partService = partService;
        this.saleService = saleService;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();

        this.manager.run();
    }

    private void seedCars() {
        String carsFileContent = this.fileIO.readFile(CARS_PATH);
        this.carService.seedCars(carsFileContent);
    }

    private void seedCustomers() {
        String customersFileContent = this.fileIO.readFile(CUSTOMERS_PATH);
        this.customerService.seedCustomers(customersFileContent);
    }

    private void seedSuppliers(){
        String suppliersFileContent = this.fileIO.readFile(SUPPLIERS_PATH);
        this.supplierService.seedSuppliers(suppliersFileContent);
    }

    private void seedParts() {
        String partsFileContent = this.fileIO.readFile(PARTS_PATH);
        this.partService.seedParts(partsFileContent);
    }

    private void seedSales() {
        this.saleService.seedSales();
    }
}
