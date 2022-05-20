package org.mayuran.dataingestor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AppMonitorController {

	@GetMapping("/dataingestor/isalive")
	public String isalive() {
		return "Data Ingestor is up and running....!!!";
	}

}