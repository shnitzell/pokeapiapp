package com.lerocas.pokeapiapp.repository;


import com.lerocas.pokeapiapp.entity.Pokemon;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer>{
    public Pokemon findByName(String name);
}
