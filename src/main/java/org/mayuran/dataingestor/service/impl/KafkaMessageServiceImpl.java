package org.mayuran.dataingestor.service.impl;

import org.mayuran.dataingestor.exceptions.DataIngestorException;
import org.mayuran.dataingestor.models.AppMessage;
import org.mayuran.dataingestor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageServiceImpl implements MessageService {

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	@Override
	public void pushMessage(String topicName, AppMessage appMessage) throws DataIngestorException {
		kafkaTemplate.send(topicName, appMessage.toString());
	}

}
