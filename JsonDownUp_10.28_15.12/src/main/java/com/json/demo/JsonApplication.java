package com.json.demo;


import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.json.demo.exeCommand;


@Log4j
@SpringBootApplication
@Configuration
@EnableSwagger2
@EnableScheduling
public class JsonApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(JsonApplication.class,args);
//        exeCommand exe=new exeCommand();
//        int i = exe.exeCommand();
//        System.out.println(i);

        log.trace("run-------------------------");
    }
}

