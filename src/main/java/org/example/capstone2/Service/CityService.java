package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.City;
import org.example.capstone2.Repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Integer id) {
        City city = cityRepository.findCityById(id);
        if (city == null) {
            throw new ApiException("City not found");
        }
        return city;
    }

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(Integer id, City city) {
        City oldCity = cityRepository.findCityById(id);
        if (oldCity == null) {
            throw new ApiException("City not found");
        }
        oldCity.setName(city.getName());
        cityRepository.save(oldCity);
    }

    public void deleteCity(Integer id) {
        City city = cityRepository.findCityById(id);
        if (city == null) {
            throw new ApiException("City not found");
        }
        cityRepository.delete(city);
    }
}
