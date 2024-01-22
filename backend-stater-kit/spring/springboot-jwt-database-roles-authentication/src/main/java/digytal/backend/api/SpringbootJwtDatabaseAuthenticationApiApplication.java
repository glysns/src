package digytal.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootJwtDatabaseAuthenticationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtDatabaseAuthenticationApiApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("UserP@ss"));
		//System.out.println(new BCryptPasswordEncoder().encode("M@nager"));
	}


}
