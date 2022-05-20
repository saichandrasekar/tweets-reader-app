package org.mayuran.dataingestor.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mayuran.dataingestor.exceptions.DataIngestorException;
import org.mayuran.dataingestor.models.AppMessage;
import org.mayuran.dataingestor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MessageController {

	@Autowired
	private MessageService kafkaMessageService;

	
	
	@GetMapping("/dataingestor/send")
	public void sendMessages() {
		Calendar now = Calendar.getInstance();
		Date dateNow = now.getTime();

		String topicName = "mayuran-" + new SimpleDateFormat("dd").format(dateNow) + "-"
				+ new SimpleDateFormat("MM").format(dateNow);

		//TODO:
		List<AppMessage> appMessages = Arrays.asList(new AppMessage[] {new AppMessage("123123"), new AppMessage("234234")});
		
		if (appMessages != null) {
			for (AppMessage appMessage : appMessages) {
				try {
					kafkaMessageService.pushMessage(topicName, appMessage);
				} catch (DataIngestorException e) {
					e.printStackTrace();
				}
			}
		}

	}

}