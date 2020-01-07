package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "operations", "repositories"})
@Resource
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class);
	}
}