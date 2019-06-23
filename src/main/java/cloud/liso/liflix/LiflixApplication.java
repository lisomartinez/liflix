package cloud.liso.liflix;

import cloud.liso.liflix.services.impl.searchEngine.SortCriteriaMap;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiflixApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiflixApplication.class, args);
    }

    @Bean
    public SortCriteriaMap searchCriteriaMap() {
        return new SortCriteriaMap();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    @Qualifier("zooqleSelectors")
//    public Selectors zooqleSelectors() {
//        return new ZooqleSelectors();
//    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        // Do any additional configuration here
//        return builder.build();
//    }
}
