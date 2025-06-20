package group2.swd392_onlineingredientsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("group2.swd392_onlineingredientsystem.model")
@EnableJpaRepositories("group2.swd392_onlineingredientsystem.repository")
public class Swd392OnlineIngredientSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(Swd392OnlineIngredientSystemApplication.class, args);
    }

}
