package digytal.backend.api;

import digytal.backend.api.mercadopago.MercadoPagoIntegration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootMercadoPagoIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMercadoPagoIntegrationApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(MercadoPagoIntegration integration) throws Exception {
		return args -> {
			integration.execute();
		};
	}
}
