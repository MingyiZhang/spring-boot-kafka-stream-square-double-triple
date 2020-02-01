package com.example.springbootstreamsquaredouble.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripleNumberProcessor {

  @Value("${kafka.topic.triple-output}")
  private String tripleOutputTopic;

  public void process(KStream<String, Long> stream) {
    stream
        .mapValues(value -> {
          log.debug("Processing :: Triple Number :: " + value);
          return value * 3;
        })
        .to(tripleOutputTopic);
  }
}
