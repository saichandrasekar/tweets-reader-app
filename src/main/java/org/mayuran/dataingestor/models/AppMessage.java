package org.mayuran.dataingestor.models;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppMessage {
	private Long id;

	private String data;

	private Calendar createdDate;

	public AppMessage(final String data) {
		Calendar now = Calendar.getInstance();
		this.id = now.getTimeInMillis() * 31;
		this.data = data;
		this.createdDate = now;
	}

	@Override
	public String toString() {
		return "AppMessage [id=" + id + ", data=" + data + ", createdDate=" + createdDate + "]";
	}
	
	
}