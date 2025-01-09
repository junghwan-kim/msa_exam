package com.mysite.msa_exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApiController {

	@GetMapping("/api/test")
	public ResponseEntity<?> getTest(){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("name", "hairmetal");
		resultMap.put("age",42);
		resultMap.put("job", "developer");
		resultMap.put("hobby", "guitar");
		return new ResponseEntity<>(resultMap,HttpStatus.OK);
	}
	
}
