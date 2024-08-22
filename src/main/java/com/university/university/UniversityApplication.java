package com.university.university;

import com.university.university.services.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CommandService commandService) {
		return args -> {
			if (args.length > 0) {
				String command = String.join(" ", args);
				String result = commandService.executeCommand(command);
				System.out.println(result);
			} else {
				System.out.println("No command provided.");
			}
		};
	}
}
