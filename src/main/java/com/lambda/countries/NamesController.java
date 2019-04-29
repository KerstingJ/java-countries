package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NamesController
{
    @GetMapping("/all")
    public ResponseEntity<?> getAllCountries()
    {
        CountriesList countries = CountriesApplication.countriesList;
        ArrayList<Country> rtnCountries = countries.countryList;
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @GetMapping("/start/{letter}")
    public ResponseEntity<?> getCountriesStartingWith(@PathVariable char letter)
    {
        CountriesList countries = CountriesApplication.countriesList;
        ArrayList<Country> rtnCountries =
                countries.findCountries(
                        c -> c.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter)
                );
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @GetMapping("/size/{number}")
    public ResponseEntity<?> getCountriesLargerThan(@PathVariable int number)
    {
        CountriesList countries = CountriesApplication.countriesList;
        ArrayList<Country> rtnCountries =
                countries.findCountries(
                        c -> c.getName().length() >= number
                );
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }
}
