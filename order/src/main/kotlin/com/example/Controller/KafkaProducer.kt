package com.example.Controller

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.concurrent.Future
import javax.ws.rs.Produces

@Produces
class KafkaProducer {

    @GetMapping("/produce")
    fun produceMessage(@RequestParam("message") message: String): ResponseEntity<String> {

        var producerRecord: ProducerRecord<String, String> = ProducerRecord("order-submitted", message)

        val map = mutableMapOf<String, String>()

        map["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

        map["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

        map["bootstrap.servers"] = "localhost:9092"

        var producer = KafkaProducer<String, String>(map as Map<String, Any>?)

        var future: Future<RecordMetadata> = producer?.send(producerRecord)!!

        return ResponseEntity.ok(" message sent to " + future.get().topic());

    }
}