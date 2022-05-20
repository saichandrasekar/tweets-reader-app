package org.mayuran.dataingestor.service;

import org.mayuran.dataingestor.exceptions.DataIngestorException;
import org.mayuran.dataingestor.models.AppMessage;

public interface MessageService {

	void pushMessage(final String topicName, final AppMessage appMessage) throws DataIngestorException;

}
