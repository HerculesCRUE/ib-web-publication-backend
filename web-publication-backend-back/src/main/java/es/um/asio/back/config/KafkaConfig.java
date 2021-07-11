package es.um.asio.back.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import es.um.asio.domain.importer.ImportError;

/**
 * Kafka related configuration.
 */
@EnableKafka
@Profile("!unit-test")
@Configuration
public class KafkaConfig {
	/**
     * Kafka properties.
     */
    @Autowired
    private KafkaProperties kafkaProperties;

    
    //////////////////////////////////////////////////////////// DATASET /////////////////////////////////////////////////////////////////////////////////
    /**
     * Import error consumer factory.
     *
     * @return the consumer factory
     */
    public ConsumerFactory<String, ImportError> importErrorConsumerFactory() {
    	return new DefaultKafkaConsumerFactory<>(this.getKafkaConfiguration());
    }

    
    /**
     * Import error kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ImportError> importErrorKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ImportError> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.importErrorConsumerFactory());
        return factory;
    }
	
    /**
     * Gets the kafka configuration.
     * customerConfigMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "1000");
     * customerConfigMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 2);
     * customerConfigMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
     * customerConfigMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
     * @return the kafka configuration
     */
    private Map<String, Object> getKafkaConfiguration() {
    	Map<String, Object> customerConfigMap = this.kafkaProperties.getConsumer().buildProperties();
    	customerConfigMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    	customerConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	customerConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    	customerConfigMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    	customerConfigMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    	return customerConfigMap;
    }
    
}
