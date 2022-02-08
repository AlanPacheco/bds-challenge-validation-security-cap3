package com.devsuperior.bds04.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	
	@Autowired
	private CityService service;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> citiesDTO = service.findAll();
		return ResponseEntity.ok().body(citiesDTO);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO cityDTO) {
		cityDTO = service.insert(cityDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cityDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cityDTO);
	}
}