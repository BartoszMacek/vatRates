package pl.cytawek.vatRates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:config.properties")
public class VatRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VatRatesApplication.class, args);


	}

}
