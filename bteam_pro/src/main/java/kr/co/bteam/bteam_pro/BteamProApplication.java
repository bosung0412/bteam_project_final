package kr.co.bteam.bteam_pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import kr.co.bteam.bteam_pro.config.WebConfig;

@SpringBootApplication
@Import(WebConfig.class)
public class BteamProApplication {

	public static void main(String[] args) {
		SpringApplication.run(BteamProApplication.class, args);
	}

}
