package com.deepak.hazelcastmapstoreexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.transaction.Transactional;

@SpringBootApplication(excludeName = {"org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration"})
@ImportResource({"classpath:/context-tx.xml"})
public class HazelcastMapstoreExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastMapstoreExampleApplication.class, args);
	}
}
