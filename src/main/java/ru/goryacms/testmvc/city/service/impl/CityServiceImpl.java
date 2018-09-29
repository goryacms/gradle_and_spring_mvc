package ru.goryacms.testmvc.city.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goryacms.testmvc.city.dto.CityDto;
import ru.goryacms.testmvc.city.model.City;
import ru.goryacms.testmvc.city.repository.CityRepository;
import ru.goryacms.testmvc.city.service.CityService;
import ru.goryacms.testmvc.util.exception.ResourceNotFoundException;
import ru.goryacms.testmvc.util.exception.TestmvcException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public CityDto loadById(long id) throws ResourceNotFoundException {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Information not found")
        );
        LOGGER.info("There is {} was received from wallet_city", city);
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    @Transactional
    public List<CityDto> findAll() {
        List<City> limitIterable = cityRepository.findAllByOrderById();
        List<CityDto> dtoList = limitIterable.stream()
                .map(limit -> modelMapper.map(limit, CityDto.class))
                .collect(Collectors.toList());
        LOGGER.info("There are {} positions was received from city", dtoList.size());
        return dtoList;
    }

    @Override
    @Transactional
    public String delete(long id) throws TestmvcException {
        cityRepository.deleteById(id);
        if (!cityRepository.findById(id).isPresent()) {
            LOGGER.info("Success deleting");
            return "success";
        }
        throw new TestmvcException("Failure from deleting");
    }

    @Override
    @Transactional
    public CityDto save(CityDto CityDto) {
        City city = cityRepository.save(modelMapper.map(CityDto, City.class));
        LOGGER.info("New record from wallet_city is {}", city);
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    @Transactional
    public CityDto update(CityDto CityDto) {
        return this.save(CityDto);
    }

    @Override
    @Transactional
    public CityDto patch(Map<String, Object> updates, long id) {
        updates.put("id", id);
        CityDto CityDto = objectMapper.convertValue(updates, CityDto.class);
        LOGGER.info("Update wallet_city by {}", CityDto);
        return this.save(CityDto);
    }
}