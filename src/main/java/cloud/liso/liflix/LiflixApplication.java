package cloud.liso.liflix;

import cloud.liso.liflix.services.torrent.sortpolicies.SortPolicies;
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
    public SortPolicies searchCriteriaMap() {
        return new SortPolicies();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
