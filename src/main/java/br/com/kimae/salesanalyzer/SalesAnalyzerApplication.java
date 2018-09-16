package br.com.kimae.salesanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class SalesAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesAnalyzerApplication.class, args);
    }

}
