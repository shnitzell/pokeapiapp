package com.lerocas.pokeapiapp.utils.converters;

import com.lerocas.pokeapiapp.utils.NamedAPIResource;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NamedAPIResourcesConverter implements AttributeConverter<NamedAPIResource, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(NamedAPIResource namedAPIResource) {
        if (namedAPIResource == null) {
            return "";
        } else {
            return namedAPIResource.getName() + SEPARATOR + namedAPIResource.getUrl();
        }
    }

    @Override
    public NamedAPIResource convertToEntityAttribute(String namedAPIResourceStringObject) {
        String[] namedAttr = namedAPIResourceStringObject.split(SEPARATOR);
        NamedAPIResource namedAPIResource = new NamedAPIResource();
        namedAPIResource.setName(namedAttr[0]);
        namedAPIResource.setUrl(namedAttr[1]);
        return namedAPIResource;
    }

}
