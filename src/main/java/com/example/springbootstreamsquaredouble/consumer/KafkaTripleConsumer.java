package com.example.springbootstreamsquaredouble.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaTripleConsumer {

  @KafkaListener(topics = "${kafka.topic.triple-output}")
  public void consume(Long number) {
    log.debug("Consumed :: Tripled Number :: " + number);
  }
}
