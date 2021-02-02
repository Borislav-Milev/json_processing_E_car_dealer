package car_dealer.app.config;

import car_dealer.app.util.FileIOImpl;
import car_dealer.app.util.ReaderImpl;
import car_dealer.app.util.ValidatorUtilImpl;
import car_dealer.app.util.contract.FileIO;
import car_dealer.app.util.contract.Reader;
import car_dealer.app.util.contract.ValidatorUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public FileIO fileReader(){
        return new FileIOImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public Reader reader(){
        return new ReaderImpl();
    }
}
