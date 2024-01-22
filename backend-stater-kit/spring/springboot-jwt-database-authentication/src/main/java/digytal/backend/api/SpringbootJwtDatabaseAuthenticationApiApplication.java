package digytal.backend.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringbootJwtDatabaseAuthenticationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtDatabaseAuthenticationApiApplication.class, args);
		//System.out.printf(new BCryptPasswordEncoder().encode("Jwt@123"));
	}

	@Bean
	public CommandLineRunner run(PasswordEncoder encoder) throws Exception {
		return args -> {
			//Ver classe WebSecurityConfig
			String senha = encoder.encode("Jwt@123");
			System.out.printf(senha);
		};
	}

}
