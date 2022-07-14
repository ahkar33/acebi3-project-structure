package com.ace.web.pf.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class CustomDateTimeZoneSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

		String formattedDate = dateFormat.format(out);
		gen.writeString(formattedDate);
	}
}
