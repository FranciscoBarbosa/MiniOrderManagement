package pt.francisco.miniordermanagement.crosscut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pt.francisco.miniordermanagement")
public class MiniOrderManagementLauncher {

	public static void main(final String[] args) {
		SpringApplication.run(MiniOrderManagementLauncher.class, args);
	}
}
