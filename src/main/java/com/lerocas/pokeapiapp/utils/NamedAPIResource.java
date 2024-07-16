package com.lerocas.pokeapiapp.utils;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedAPIResource extends APIResource {
	private String name;

	public String getName() {
		return name;
	}

	public NamedAPIResource setName(String name) {
		this.name = name;
		return this;
	}
}