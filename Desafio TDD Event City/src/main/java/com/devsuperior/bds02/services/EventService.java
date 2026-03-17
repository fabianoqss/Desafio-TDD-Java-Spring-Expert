package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event event = eventRepository.getReferenceById(id);
            copyDtoToEntity(event, dto);
            event = eventRepository.save(event);
            return new EventDTO(event);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Evento não encontrado");
        }
    }

    private void copyDtoToEntity(Event entity , EventDTO dto){
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        City city = cityRepository.getReferenceById(dto.getCityId());
        entity.setCity(city);
    }
}
