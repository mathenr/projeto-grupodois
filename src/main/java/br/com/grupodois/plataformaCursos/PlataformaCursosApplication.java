package br.com.grupodois.plataformaCursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class PlataformaCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaCursosApplication.class, args);
	}

}
