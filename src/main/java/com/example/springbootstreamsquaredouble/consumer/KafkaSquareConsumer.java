package com.example.springbootstreamsquaredouble.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSquareConsumer {

  @KafkaListener(topics = "${kafka.topic.even-output}")
  public void consume(Long number) {
    log.debug(String.format("Consumed :: Squared Even :: %d", number));
  }

}

