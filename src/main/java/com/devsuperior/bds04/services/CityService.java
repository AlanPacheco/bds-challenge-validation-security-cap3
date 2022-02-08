package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> cities = repository.findAll();
		return cities.stream().map(city -> new CityDTO(city))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		copyDtoToEntity(cityDTO, city);
		city = repository.save(city);
		return new CityDTO(city);
	}
	
	private void copyDtoToEntity(CityDTO cityDTO, City city) {
		city.setName(cityDTO.getName());
	}
	
}
