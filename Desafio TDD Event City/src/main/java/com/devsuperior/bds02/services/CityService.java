package com.devsuperior.bds02.services;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public List<CityDTO> findAll(){
        List<City> result = cityRepository.findAllByOrderByNameAsc();

        return result.stream().map(CityDTO::new).toList();
    }

    @Transactional
    public CityDTO insert(CityDTO dto){
        City city = new City();
        copyDtoToEntity(city, dto);
        city = cityRepository.save(city);
        return new CityDTO(city);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){

        if(!cityRepository.existsById(id)){
            throw new ResourceNotFoundException("Cidade não encontrada");
        }
        try {
            cityRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }


    private void copyDtoToEntity(City entity, CityDTO dto){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
    }

}
