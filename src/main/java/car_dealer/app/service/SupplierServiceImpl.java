package car_dealer.app.service;

import car_dealer.app.domain.entity.Supplier;
import car_dealer.app.domain.model.binding.SeedSupplierDto;
import car_dealer.app.domain.model.view.query3.LocalSupplierDto;
import car_dealer.app.repository.SupplierRepository;
import car_dealer.app.service.contract.SupplierService;
import car_dealer.app.util.contract.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSuppliers(String jsonSuppliers) {
        if(supplierRepository.count() != 0){
            return;
        }
        SeedSupplierDto[] seedSupplierDtos = this.gson.fromJson(jsonSuppliers, SeedSupplierDto[].class);

        for (SeedSupplierDto seedSupplierDto : seedSupplierDtos) {
            if(this.validatorUtil.ifNotValidPrintViolations(seedSupplierDto)){
                return;
            }
            Supplier supplier = this.modelMapper.map(seedSupplierDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    public Supplier getRandomSupplier(){
        Random random = new Random();
        return this.findSupplierById(random.nextInt(this.getCount()) + 1);
    }

    @Override
    public Supplier findSupplierById(Integer id){
        return this.supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such supplier"));
    }

    @Override
    @Transactional
    public String getLocalSuppliers() {
        List<LocalSupplierDto> localSupplierDtos = this.supplierRepository.localSuppliers();
        return this.gson.toJson(localSupplierDtos);
    }

    public int getCount(){
        return Math.toIntExact(this.supplierRepository.count());
    }
}
