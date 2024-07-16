package com.lerocas.pokeapiapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerocas.pokeapiapp.entity.Pokemon;
import com.lerocas.pokeapiapp.service.PokemonService;

@RestController
@RequestMapping("/pokemonList")
class PokemonAPIController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAll() {
        try {
            List<Pokemon> items = new ArrayList<Pokemon>();

            this.pokemonService.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Pokemon>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable("id") int id) {
        Optional<Pokemon> existingItemOptional = this.pokemonService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon item) {
        try {
            Pokemon savedItem = pokemonService.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Pokemon(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Pokemon> update(@PathVariable("id") int id, @RequestBody Pokemon item) {
        Optional<Pokemon> existingItemOptional = this.pokemonService.findById(id);
        if (existingItemOptional.isPresent()) {
            Pokemon existingItem = existingItemOptional.get();
            existingItem.setHeight(item.getHeight());
            existingItem.setLocationAreaEncounters(item.getLocationAreaEncounters());
            existingItem.setWeight(item.getWeight());
            return new ResponseEntity<>(this.pokemonService.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            pokemonService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}