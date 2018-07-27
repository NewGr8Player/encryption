package com.xavier.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@PostMapping(path = "/genToken")
	public String genToker(
			@RequestParam("systemName") String systemName,
			@RequestParam("url") String url
	) {
		return "";
	}
}
