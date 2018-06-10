package com.deepak.hazelcastmapstoreexample.config;

import com.deepak.hazelcastmapstoreexample.mapstore.CustomerMapStore;
import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelCastConfig {

    @Bean
    public Config config(CustomerMapStore customerMapStore) {
        return new Config()
                .setNetworkConfig(
                        new NetworkConfig()
                                .setJoin(
                                        new JoinConfig()
                                                .setMulticastConfig(new MulticastConfig().setEnabled(false))
                                                .setTcpIpConfig(
                                                        new TcpIpConfig()
                                                                .setEnabled(true)
                                                                .addMember("localhost")
                                                )
                                )
                )
                .setProperty("hazelcast.logging.type", "slf4j")
                .addMapConfig(
                        new MapConfig("customersMap")
                                .setMapStoreConfig(
                                        new MapStoreConfig()
                                                .setImplementation(customerMapStore)
                                                .setWriteDelaySeconds(0) // write-through
                                )
                );
    }
}
