package com.example.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by veraxmedax on 08/09/2017.
 */
public class Producer {
    private static Scanner in;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please specify 1 parameters ");
            System.exit(-1);
        }
        String topicName = args[0];
        in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer<String, String>(producerProperties());
        String line = in.nextLine();
        while (!line.equals("exit")) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
            producer.send(rec);
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }

    private static Properties producerProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
