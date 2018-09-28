package ru.goryacms.testmvc.city.service;

import ru.goryacms.testmvc.city.dto.CityDto;
import ru.goryacms.testmvc.util.exception.ResourceNotFoundException;
import ru.goryacms.testmvc.util.exception.TestmvcException;

import java.util.List;
import java.util.Map;

public interface CityService {
    CityDto loadById(long id) throws ResourceNotFoundException;

    List<CityDto> findAll();

    String delete(long id) throws TestmvcException;

    CityDto save(CityDto CityDto);

    CityDto update(CityDto CityDto);

    CityDto patch(Map<String, Object> updates, long id);
}