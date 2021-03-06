package com.tweltar.TrelloClone.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
	@Value("${app.version}")
	private String appVersion;
	
	@GetMapping("/tweltar/trelloClone/")
	public Map getStatus() {
		Map map = new HashMap<String, String>();
		map.put("name", "Trello (Clone)");
		map.put("app-version", appVersion);
		return map;
	}
}
