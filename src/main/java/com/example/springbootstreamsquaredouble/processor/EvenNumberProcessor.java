package com.example.springbootstreamsquaredouble.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EvenNumberProcessor {

  @Value(value = "${kafka.topic.even-output}")
  private String evenOutputTopic;

  public void process(KStream<String, Long> stream) {
    stream.filter((k, v) -> v % 2 == 0)
        .mapValues(v -> {
          log.debug("Processing :: Squaring Even :: " + v);
          return v * v;
        })
        .to(evenOutputTopic);
  }
}
