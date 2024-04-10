package www.bible.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BiblaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblaryApplication.class, args);
	}

}
