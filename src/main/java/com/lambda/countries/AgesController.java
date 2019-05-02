package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/age")
public class AgesController
{
    @GetMapping("/age/{age}")
    public ResponseEntity<?> getAgesGreaterThan(@RequestParam int age)
    {
        CountriesList countries = CountriesApplication.countriesList;
        ArrayList<Country> rtn = countries.findCountries(
                c -> c.getMedianAge() >= age
        );
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping("/min")
    public ResponseEntity<?> getMinAge()
    {
        ArrayList<Country> countries = CountriesApplication.countriesList.countryList;
        countries.sort(Comparator.comparingInt(Country::getMedianAge));

        return new ResponseEntity<>(countries.get(0), HttpStatus.OK);
    }

    @GetMapping("/max")
    public ResponseEntity<?> getMaxAge()
    {
        ArrayList<Country> countries = CountriesApplication.countriesList.countryList;
        countries.sort(Comparator.comparingInt(Country::getMedianAge));
        Collections.reverse(countries);
        return new ResponseEntity<>(countries.get(0), HttpStatus.OK);
    }


    @GetMapping("/median")
    public ResponseEntity<?> getMedianAge()
    {
        ArrayList<Country> countries = CountriesApplication.countriesList.countryList;
        countries.sort(Comparator.comparingInt(Country::getMedianAge));
        int middle = countries.size() / 2;

        return new ResponseEntity<>(countries.get(middle), HttpStatus.OK);
    }
}
