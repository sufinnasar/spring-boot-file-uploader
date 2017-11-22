package com.bloomberg.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
//@EnableAsync
public class FxApplication extends SpringBootServletInitializer{
	
	 @Override
	    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
	        return builder.sources(FxApplication.class);
	    }


	public static void main(String[] args) {
		SpringApplication.run(FxApplication.class, args);
	}
	
	 /*@Bean
	    public Executor asyncExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(99);
	        executor.setMaxPoolSize(99);
	        executor.setThreadNamePrefix("Insert-");
	        executor.initialize();
	        return executor;
	    }*/
	
	
}
