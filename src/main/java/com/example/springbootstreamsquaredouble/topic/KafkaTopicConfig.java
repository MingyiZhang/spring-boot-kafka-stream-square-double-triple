package com.example.springbootstreamsquaredouble.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j
public class KafkaTopicConfig {

  @Value(value = "${kafka.topic.input}")
  public String inputTopic;

  @Value(value = "${kafka.topic.even-output}")
  public String evenOutputTopic;

  @Value(value = "${kafka.topic.odd-output}")
  public String oddOutputTopic;

  @Value("${kafka.topic.triple-input}")
  public String tripleInputTopic;

  @Value("${kafka.topic.triple-output}")
  public String tripleOutputTopic;

// not needed because the configuration is already in application.yml
//  @Bean
//  public KafkaAdmin kafkaAdmin(KafkaProperties kafkaProperties) {
//    Map<String, Object> config = new HashMap<>();
//    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//    return new KafkaAdmin(config);
//  }

  private NewTopic createNewTopic(String topic, Integer partitions, Integer replicas) {
    log.debug("Create topic :: " + topic);
    return TopicBuilder.name(topic)
        .partitions(partitions)
        .replicas(replicas)
        .build();
  }

  @Bean
  public NewTopic getInputTopic() {
    return createNewTopic(inputTopic, 1, 1);
  }

  @Bean
  public NewTopic getEvenOutputTopic() {
    return createNewTopic(evenOutputTopic, 1, 1);
  }

  @Bean
  public NewTopic getOddOutputTopic() {
    return createNewTopic(oddOutputTopic, 1, 1);
  }

  @Bean
  public NewTopic getTripleInputTopic() {
    return createNewTopic(tripleInputTopic, 1, 1);
  }

  @Bean
  public NewTopic getTripleOutputTopic() {
    return createNewTopic(tripleOutputTopic, 1, 1);
  }
}
