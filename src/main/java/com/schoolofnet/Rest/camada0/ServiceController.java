package com.schoolofnet.Rest.camada0;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/services")
public class ServiceController {
	
	@PostMapping
	public ResponseEntity<Object> service(@RequestBody final JSONObject data) {
		String method = data.getAsString("method");
		
		if (method.equals("TEST_METHOD"))
			return new ResponseEntity<Object>(method, HttpStatus.OK);
		else if (method.equals("METHOD_1"))
			return new ResponseEntity<Object>("METHOD 1 RETURN", HttpStatus.OK);
		else if (method.equals("METHOD_2"))
			return new ResponseEntity<Object>("METHOD 2 RETURN", HttpStatus.OK);
		else
			return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
}
