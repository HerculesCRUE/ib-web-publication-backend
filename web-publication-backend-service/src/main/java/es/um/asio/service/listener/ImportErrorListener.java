package es.um.asio.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import es.um.asio.domain.importer.ImportError;
import es.um.asio.service.domain.ImportErrorEntity;
import es.um.asio.service.repository.ImportErrorRepository;

/**
 * Import error listener
 */
@Profile("!unit-test")
@Component
public class ImportErrorListener {
    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(ImportErrorListener.class);
    
    @Autowired
    private ImportErrorRepository importErrorRepository;


    /**
     * Method listening input topic name
     * 
     * @param message
     */
    @KafkaListener(id="importErrorKafkaListenerContainerFactory",topics = "#{'${app.kafka.import-error-topic-name}'.split(',')}", containerFactory = "importErrorKafkaListenerContainerFactory")
    public void listen(final ImportError error) {
    	
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Received import error message: {}", error);
        }

        // we populate the import error fields
        ImportErrorEntity errorEntity = new ImportErrorEntity();
        errorEntity.setJobExecutionId(error.getJobExecutionId());
        errorEntity.setDescription(error.getDescription());
        
        // we save the error in the database
        this.importErrorRepository.save(errorEntity);
    }
}
