package com.example.springbootstreamsquaredouble.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
@Slf4j
public class KafkaStreamConfig {

  @Value("${kafka.topic.input}")
  private String inputTopic;

  @Value("${kafka.topic.triple-input}")
  private String tripleInputTopic;

  private OddNumberProcessor oddNumberProcessor;

  private EvenNumberProcessor evenNumberProcessor;

  private TripleNumberProcessor tripleNumberProcessor;

  KafkaStreamConfig(
      OddNumberProcessor oddNumberProcessor,
      EvenNumberProcessor evenNumberProcessor,
      TripleNumberProcessor tripleNumberProcessor
  ) {
    this.oddNumberProcessor = oddNumberProcessor;
    this.evenNumberProcessor = evenNumberProcessor;
    this.tripleNumberProcessor = tripleNumberProcessor;
  }

//  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//  public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties) {
//    Map<String, Object> config = new HashMap<>();
//    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//    config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
//    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
//    return new KafkaStreamsConfiguration(config);
//  }

  @Bean
  public KStream<String, Long> kStream(StreamsBuilder kStreamBuilder) {
    KStream<String, Long> stream = kStreamBuilder.stream(inputTopic);
    this.oddNumberProcessor.process(stream);
    this.evenNumberProcessor.process(stream);
    stream.to(tripleInputTopic);
    return stream;
  }

  @Bean
  public KStream<String, Long> tripleStream(StreamsBuilder kStreamBuilder) {
    KStream<String, Long> stream = kStreamBuilder.stream(tripleInputTopic);
    this.tripleNumberProcessor.process(stream);
    return stream;
  }

}

