package org.neoevolution.mvc.converter;

import org.neo4j.ogm.typeconversion.AttributeConverter;
import org.neoevolution.core.activation.ActivationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class ActivationToStringConverter implements AttributeConverter<ActivationFunction, String> {

	@Autowired
	private ActivationTypeToActivationConverter converter;

	@Override
	public String toGraphProperty(ActivationFunction value) {
		return value.getType().getName();
	}

	@Override
	public ActivationFunction toEntityAttribute(String value) {
		return converter.convert(value);
	}
}
