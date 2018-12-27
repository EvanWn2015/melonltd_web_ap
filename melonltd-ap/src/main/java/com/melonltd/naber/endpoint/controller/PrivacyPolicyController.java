package com.melonltd.naber.endpoint.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "" })
public class PrivacyPolicyController {

//	@GetMapping(value = "naber/privac/policy")
	@RequestMapping(value = "naber/privac/policy", method = RequestMethod.GET)
	public String privacPolicy(HttpServletRequest req, HttpServletResponse resp) {
		return "policy";
//		return "index";
	}
}
