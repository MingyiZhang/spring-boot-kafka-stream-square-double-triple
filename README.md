# Spring Boot and Kafka Streams: Square Double Triple

This is a simple example to demonstrate how to use spring boot with kafka streams. 

## Description
1. `KafkaNumberProducer` produces natural number in sequence, one per second, 
to topic `input-topic`.
2. Stream application `double-square` consumes number of `input-topic` and 
    - if it's even, square and send to topic `square-output`
    - if it's odd, double and send to topic `double-output`
    - send to topic `triple-input`, another stream consumes, triples and sends to topic `triple-output`
3. `KafkaSquareConsumer`, `KafkaDoubleConsumer` and `KafkaTripleConsumer` consume from 
`square-output`, `double-output` and `triple-output`, respectively, and log to screen.

## Run
```shell script
gradle bootRun
```