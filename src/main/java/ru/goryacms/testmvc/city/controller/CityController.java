package ru.goryacms.testmvc.city.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.goryacms.testmvc.city.dto.CityDto;
import ru.goryacms.testmvc.city.service.CityService;
import ru.goryacms.testmvc.util.dto.ErrorDo;
import ru.goryacms.testmvc.util.dto.ResponseDto;
import ru.goryacms.testmvc.util.exception.ResourceNotFoundException;
import ru.goryacms.testmvc.util.exception.TestmvcException;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping(value = "/api/city", produces = APPLICATION_JSON_UTF8_VALUE)
public class CityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> cityById(@PathVariable("id") long id) throws ResourceNotFoundException {
        LOGGER.info("Get from city table by id = {}", id);
        return ResponseEntity.ok(new ResponseDto<CityDto>(cityService.loadById(id)));
    }

    @GetMapping(value = "/")
    public String cityList(Model model) {
        LOGGER.info("Get list from city table");
        List<CityDto> cities = cityService.findAll();
        int size = cities.size();
        LOGGER.info("There are {} positions", size);
        model.addAttribute("listCity", cities);
        return "cities/listCities";
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseDto> saveCity(@RequestBody CityDto cityDto) {
        LOGGER.info("Save to city table by this parameters: {}", cityDto);
        return ResponseEntity.ok(new ResponseDto<CityDto>(cityService.save(cityDto)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateCity(@RequestBody CityDto cityDto,
                                                   @PathVariable("id") long id) {
        cityDto.setId(id);
        LOGGER.info("Update city table by {}", cityDto);
        return ResponseEntity.ok(new ResponseDto<CityDto>(cityService.update(cityDto)));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> patchCity(@RequestBody Map<String, Object> updates,
                                                  @PathVariable("id") long id) {
        LOGGER.info("Update city table by id = {}", id);
        return ResponseEntity.ok(new ResponseDto<CityDto>(cityService.patch(updates, id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteCity(@PathVariable("id") long id) throws TestmvcException {
        LOGGER.info("Delete from city table base by {}", id);
        return ResponseEntity.ok(new ResponseDto<String>(cityService.delete(id)));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> notFoundErrorHandler(ResourceNotFoundException e) {
        LOGGER.error("Information not found from database", e.getMessage());
        ResponseDto responseDto = new ResponseDto(new ErrorDo(null, e.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
}