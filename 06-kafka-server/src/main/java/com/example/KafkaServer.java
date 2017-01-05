package com.example;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;
import org.apache.curator.test.TestingServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class KafkaServer {

    private static TestingServer zookeeper;
    private static KafkaServerStartable kafkaServer;
    private static Path kafkaLogDir;


    private static void start(int zookeeperPort, int kafkaPort) throws Exception {
        zookeeper = new TestingServer(zookeeperPort, true);
        String zookeeperConnectionString = zookeeper.getConnectString();
        KafkaConfig kafkaConfig = buildKafkaConfig(zookeeperConnectionString, kafkaPort);
        kafkaServer = new KafkaServerStartable(kafkaConfig);
        kafkaServer.startup();
    }

    private static KafkaConfig buildKafkaConfig(String zookeeperQuorum, int kafkaPort) throws IOException {
        kafkaLogDir = Files.createTempDirectory("kafka_maven");

        Properties props = new Properties();
        props.put("port", kafkaPort + "");
        props.put("broker.id", "1");
        props.put("log.dirs", kafkaLogDir.toAbsolutePath().toString());
        props.put("zookeeper.connect", zookeeperQuorum);

        return new KafkaConfig(props);
    }

    public static void main(String[] args) throws Exception {
        start(2181, 2182);
    }
}
