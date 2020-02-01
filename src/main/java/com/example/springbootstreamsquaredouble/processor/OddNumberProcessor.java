package com.example.springbootstreamsquaredouble.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OddNumberProcessor {

  @Value(value = "${kafka.topic.odd-output}")
  private String oddOutputTopic;

  public void process(KStream<String, Long> stream) {
    stream.filter((key, value) -> value % 2 != 0)
        .mapValues(value -> {
          log.debug("Processing :: Doubling Odd :: " + value);
          return value * 2;
        })
        .to(oddOutputTopic);
  }
}
