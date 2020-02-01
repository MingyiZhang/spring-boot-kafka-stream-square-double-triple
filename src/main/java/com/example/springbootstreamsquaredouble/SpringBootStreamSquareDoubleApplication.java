package com.example.springbootstreamsquaredouble;

import com.example.springbootstreamsquaredouble.producer.KafkaNumberProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootStreamSquareDoubleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootStreamSquareDoubleApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(KafkaNumberProducer producer) {
    return (args -> producer.produce());
  }

}
