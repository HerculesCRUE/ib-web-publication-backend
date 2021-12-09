package es.um.asio.service.service.sparql.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.MapFunction;

@Component
public class SqparqlResponseDateFormatter implements MapFunction {

	@Value("${app.trellis.dateFormatOutput}")
	private String targetFormat;

	@Override
	public Object map(Object currentValue, Configuration configuration) {

		if (currentValue != null) {
			try {
				OffsetDateTime dateToTransform = OffsetDateTime.parse((String) currentValue);
				
				return DateTimeFormatter.ofPattern(targetFormat).format(dateToTransform);
			} catch (Exception e) {

			}
		}
		return currentValue;
	}

}
