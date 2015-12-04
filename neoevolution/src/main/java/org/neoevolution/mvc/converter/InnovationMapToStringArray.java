package org.neoevolution.mvc.converter;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @version 1.0
 */
public class InnovationMapToStringArray implements AttributeConverter<Map<String, Long>, String[]> {

	public static final String SEPARATOR = "=";

	@Override
	public String[] toGraphProperty(Map<String, Long> value)
	{
		List<String> values = new ArrayList<>(value.size());

		for (Map.Entry<String, Long> entry : value.entrySet()) {
			values.add(entry.getKey() + SEPARATOR + entry.getValue());
		}
		return values.toArray(new String[value.size()]);
	}

	@Override
	public Map<String, Long> toEntityAttribute(String[] value)
	{
		Map<String, Long> innovations = new HashMap<>(value.length);

		for (String entry : value) {
			String[] entries = entry.split(SEPARATOR);
			innovations.put(entries[0], Long.valueOf(entries[1]));
		}
		return innovations;
	}

}
