package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController
{
    @GetMapping("/size/{people}")
    public ResponseEntity<?> getCountriesWithPopGreaterThan(@PathVariable int people)
    {
        CountriesList countries = CountriesApplication.countriesList;
        ArrayList<Country> rtnCountries = countries.findCountries(
                c -> c.getPopulation() >= people
        );
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @GetMapping("/size/min")
    public ResponseEntity<?> getCountriesWithLeastPop()
    {
        CountriesList countries = CountriesApplication.countriesList;
        countries.countryList.sort(
                (c1, c2) -> c1.getPopulation() - c2.getPopulation()
        );
        return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
    }

    @GetMapping("/size/max")
    public ResponseEntity<?> getCountriesWithMostPop()
    {
        CountriesList countries = CountriesApplication.countriesList;
        countries.countryList.sort(
                (c1, c2) -> c2.getPopulation() - c1.getPopulation()
        );
        return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
    }


}
