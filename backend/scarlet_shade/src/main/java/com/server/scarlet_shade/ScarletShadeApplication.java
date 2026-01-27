package com.server.scarlet_shade;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.server.scarlet_shade.configuration.DotEnvInitializer;

@SpringBootApplication
public class ScarletShadeApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(ScarletShadeApplication.class)
			.initializers(new DotEnvInitializer())
			.run(args);
	}
}