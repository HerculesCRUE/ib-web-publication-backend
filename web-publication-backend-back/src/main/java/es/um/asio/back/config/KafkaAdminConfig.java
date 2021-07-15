package es.um.asio.back.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Kafka admin related configuration
 */
@Profile("!unit-test")
@Configuration
public class KafkaAdminConfig {
    /**
     * General topic name.
     */
    @Value("${app.kafka.import-error-topic-name}")
    private String importErrorTopicName;
        
    /**
     * General topic.
     * 
     * @return
     */
    @Bean
    public NewTopic importErrorTopic() {
        return new NewTopic(this.importErrorTopicName, 1, (short) 1);
    }
    
    
}
