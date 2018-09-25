package com.iwe.avenger.dynamodb.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDao {

	private Map<String, Avenger> mapper = new HashMap<>();

	public AvengerDao() {
		mapper.put("aaaa-sdsad-sdsa-asds", new Avenger("aaaa-sdsad-sdsa-asds", "Iron Man", "Tony Stark"));
		mapper.put("aaaa-aaaa-aaaa-aaaa", new Avenger("aaaa-aaaa-aaaa-aaaa", "Hulk", "Bruce Banner"));
		mapper.put("bbbb-bbbb-bbbb-bbbb", new Avenger("aaaa-aaaa-aaaa-aaaa", "Hulk", "Bruce Banner"));
	}

	public Avenger search(String id) {
		return mapper.get(id);
	}

	public Avenger update(final Avenger avenger) {
		return mapper.replace(avenger.getId(), avenger);
	}
	
	public Avenger create(Avenger avenger) {
		String id = "IssoFoiGerado";
		avenger.setId(id);
		mapper.put(id, avenger);
		return avenger;
	}
	
	public void remove(Avenger avenger) {
		mapper.remove(avenger.getId());
	}

}