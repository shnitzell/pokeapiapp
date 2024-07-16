package com.lerocas.pokeapiapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lerocas.pokeapiapp.entity.Pokemon;
import com.lerocas.pokeapiapp.repository.PokemonRepository;
import com.lerocas.pokeapiapp.resources.NamedAPIResourceList;
import com.lerocas.pokeapiapp.utils.NamedAPIResource;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    public List<Pokemon> findAll() {
        List<Pokemon> fetchedPokemonsList = new ArrayList<Pokemon>();
        NamedAPIResourceList paginatedPokemons = NamedAPIResourceList
                .getList(Pokemon.class.getSimpleName().toLowerCase(), 10, 0);
        paginatedPokemons.getResults().forEach((NamedAPIResource result) -> {
            Pokemon storedPokemon = pokemonRepository.findByName(result.getName());
            if (storedPokemon != null) {
                fetchedPokemonsList.add(storedPokemon);
            } else {
                Pokemon fetchedPokemon = Pokemon.getByName(result.getName());
                pokemonRepository.save(fetchedPokemon);
                fetchedPokemonsList.add(fetchedPokemon);
            }
        });
        return fetchedPokemonsList;
    }

    public Optional<Pokemon> findById(int id) {
        Optional<Pokemon> storedPokemon = pokemonRepository.findById(id);

        if (storedPokemon.isPresent()) {
            return storedPokemon;
        } else {
            Pokemon fetchedPokemon = Pokemon.getById(id);
            if (fetchedPokemon.getName() != "") {
                pokemonRepository.save(fetchedPokemon);
                return Optional.of(fetchedPokemon);
            } else {
                return Optional.empty();
            }
        }
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void deleteById(int id) {
        pokemonRepository.deleteById(id);
    }

}
