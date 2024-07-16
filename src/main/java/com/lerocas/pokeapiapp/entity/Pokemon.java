package com.lerocas.pokeapiapp.entity;

import java.io.Serializable;
import java.util.List;

import com.lerocas.pokeapiapp.utils.Information;
import com.lerocas.pokeapiapp.utils.NamedAPIResource;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Pokemon extends NamedAPIResource implements Serializable {

	@Id
	@Column(name = "pokemonId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int base_experience;
	private int height;
	private boolean is_default;
	private int order;
	private int weight;
	private String location_area_encounters;

	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "pokemonId"))
	private List<PokemonAbility> abilities;

	public int getId() {
		return id;
	}

	public Pokemon setId(int id) {
		this.id = id;
		return this;
	}

	public int getBaseExperience() {
		return base_experience;
	}

	public Pokemon setBaseExperience(int base_experience) {
		this.base_experience = base_experience;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public Pokemon setHeight(int height) {
		this.height = height;
		return this;
	}

	public boolean getIsDefault() {
		return is_default;
	}

	public Pokemon setIsDefault(boolean is_default) {
		this.is_default = is_default;
		return this;
	}

	public int getOrder() {
		return order;
	}

	public Pokemon setOrder(int order) {
		this.order = order;
		return this;
	}

	public int getWeight() {
		return weight;
	}

	public Pokemon setWeight(int weight) {
		this.weight = weight;
		return this;
	}

	public List<PokemonAbility> getAbilities() {
		return abilities;
	}

	public Pokemon setAbilities(List<PokemonAbility> abilities) {
		this.abilities = abilities;
		return this;
	}

	public String getLocationAreaEncounters() {
		return location_area_encounters;
	}

	public Pokemon setLocationAreaEncounters(String location_area_encounters) {
		this.location_area_encounters = location_area_encounters;
		return this;
	}

	private static Pokemon get(String url) {
		Pokemon obj = new com.google.gson.Gson().fromJson(Information.fromInternet(url), Pokemon.class);
		obj.setIsFetched(true);
		return obj;
	}

	public Pokemon get() {
		return Pokemon.get(this.getUrl());
	}

	public static Pokemon getById(int id) {
		return get("https://pokeapi.co/api/v2/pokemon/" + id);
	}

	public static Pokemon getByName(String name) {
		return get("https://pokeapi.co/api/v2/pokemon/" + name);
	}
}
