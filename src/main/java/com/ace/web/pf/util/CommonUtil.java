package com.ace.web.pf.util;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ace.web.pf.datamodel.User;
import com.ace.web.pf.service.impl.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtil {

	@Autowired
	@Qualifier("userService")
	private UserService userServiceImpl;

	public User loadLoginUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userServiceImpl.findByUserName(auth.getName());
		return user;
	}

	public String convertToJson(Object input) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			return mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public <T> T convertToObject(String jsonContent, TypeReference<T> typeReference)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		return mapper.readValue(jsonContent, typeReference);
	}
}
