package com.example.mytasksbackend;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTasksBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTasksBackendApplication.class, args);
	}

}
