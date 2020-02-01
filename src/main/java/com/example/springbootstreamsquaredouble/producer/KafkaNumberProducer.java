package com.example.springbootstreamsquaredouble.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaNumberProducer {

  private long counter = 0;

  @Value("${kafka.topic.input}")
  private String inputTopic;

  private final KafkaTemplate<String, Long> kafkaTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  KafkaNumberProducer(KafkaTemplate<String, Long> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Scheduled(fixedRate = 1000)
  public void produce() {
    log.debug("Produced :: " + counter);
    this.kafkaTemplate.send(inputTopic, counter++);
//    this.kafkaTemplate.sendDefault(counter++);  // use only when spring.kafka.template.default-topic is set
  }

}

