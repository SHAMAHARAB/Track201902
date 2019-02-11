package jp.co.softbank.trackproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleContoller {
	@GetMapping
	public String sample() {
		return "Heroku Operation Check!!";
	}
}
