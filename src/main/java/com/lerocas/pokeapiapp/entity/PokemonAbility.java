package com.lerocas.pokeapiapp.entity;

import com.lerocas.pokeapiapp.utils.NamedAPIResource;
import com.lerocas.pokeapiapp.utils.converters.NamedAPIResourcesConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

@Embeddable
public class PokemonAbility {
	private boolean is_hidden;
	private int slot;
	@Convert(converter = NamedAPIResourcesConverter.class)
	private NamedAPIResource ability;

	public boolean getIsHidden() {
		return is_hidden;
	}

	public PokemonAbility setIsHidden(boolean is_hidden) {
		this.is_hidden = is_hidden;
		return this;
	}

	public int getSlot() {
		return slot;
	}

	public PokemonAbility setSlot(int slot) {
		this.slot = slot;
		return this;
	}

	public NamedAPIResource getAbility() {
		return ability;
	}

	public PokemonAbility setAbility(NamedAPIResource ability) {
		this.ability = ability;
		return this;
	}

	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}
}