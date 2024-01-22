package digytal.backend.api;

import digytal.backend.api.enums.Sexo;
import digytal.backend.api.model.cliente.ClienteEntity;
import digytal.backend.api.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringbootCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ClienteRepository repository) throws Exception {
		return args -> {
			ClienteEntity gleyson = new ClienteEntity();
			gleyson.setCpf("123");
			gleyson.setNome("gleyson sampaio");
			gleyson.setSexo(Sexo.MASCULINO);
			gleyson.setRendaMensal(1234.5);
			gleyson.setDataNascimento(LocalDate.of(1988,10,16));
			repository.save(gleyson);

			ClienteEntity izabelly = new ClienteEntity();
			izabelly.setCpf("456");
			izabelly.setNome("izabelly sampaio");
			izabelly.setSexo(Sexo.FEMININO);
			izabelly.setRendaMensal(10876.98);
			izabelly.setDataNascimento(LocalDate.of(2023,04,16));
			repository.save(izabelly);
		};
	}


}
