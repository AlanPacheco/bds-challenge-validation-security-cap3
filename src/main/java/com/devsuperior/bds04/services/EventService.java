package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable){
		Page<Event> cities = repository.findAll(pageable);
		
		return cities.map(event -> new EventDTO(event));
	}
	
	@Transactional
	public EventDTO insert(EventDTO eventDTO) {
		Event event = new Event();
		copyDtoToEntity(eventDTO, event);
		event = repository.save(event);
		return new EventDTO(event);
	}
	
	private void copyDtoToEntity(EventDTO eventDTO, Event event) {
		event.setName(eventDTO.getName());
		event.setDate(eventDTO.getDate());
		event.setUrl(eventDTO.getUrl());
		event.setCity(new City(eventDTO.getCityId(), null));
	}
	
}
