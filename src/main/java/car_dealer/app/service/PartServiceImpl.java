package car_dealer.app.service;

import car_dealer.app.domain.entity.Part;
import car_dealer.app.domain.model.binding.SeedPartDto;
import car_dealer.app.repository.PartRepository;
import car_dealer.app.service.contract.PartService;
import car_dealer.app.service.contract.SupplierService;
import car_dealer.app.util.contract.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PartServiceImpl implements PartService {

    private final SupplierService supplierService;
    private final PartRepository partRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PartServiceImpl(SupplierService supplierService, PartRepository partRepository,
                           ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.supplierService = supplierService;
        this.partRepository = partRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void seedParts(String jsonParts) {
        if (this.partRepository.count() != 0) {
            return;
        }
        SeedPartDto[] seedPartDtos = this.gson.fromJson(jsonParts, SeedPartDto[].class);

        System.out.println();
        for (SeedPartDto seedPartDto : seedPartDtos) {
            if (this.validatorUtil.ifNotValidPrintViolations(seedPartDto)) {
                return;
            }
            Part part = this.modelMapper.map(seedPartDto, Part.class);
            part.setSupplier(this.supplierService.getRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }
    }
    @Override
    public int getCount() {
        return Math.toIntExact(this.partRepository.count());
    }

    @Override
    public Part findPartById(Integer id) {
        return this.partRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such part."));
    }

    @Override
    public List<Part> getRandomParts() {
        System.out.println();
        List<Part> parts = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= random.nextInt(10) + 10; i++) {
            parts.add(this.getRandomPart());
        }
        return parts;
    }

    @Override
    public Part getRandomPart(){
        Random random = new Random();
        return this.findPartById(random.nextInt(this.getCount()) + 1);
    }

}
