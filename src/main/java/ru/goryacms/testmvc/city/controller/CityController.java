package ru.goryacms.testmvc.city.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.goryacms.testmvc.city.dto.CityDto;
import ru.goryacms.testmvc.city.model.City;
import ru.goryacms.testmvc.city.service.CityFormValidator;
import ru.goryacms.testmvc.city.service.CityService;
import ru.goryacms.testmvc.util.dto.ErrorDo;
import ru.goryacms.testmvc.util.dto.ResponseDto;
import ru.goryacms.testmvc.util.exception.ResourceNotFoundException;
import ru.goryacms.testmvc.util.exception.TestmvcException;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping(value = "/city", produces = APPLICATION_JSON_UTF8_VALUE)
public class CityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;
    private final CityFormValidator cityFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(cityFormValidator);
    }

    public CityController(CityService cityService, CityFormValidator cityFormValidator) {
        this.cityService = cityService;
        this.cityFormValidator = cityFormValidator;
    }

    @GetMapping(value = "/{id}/update")
    public String showUpdateCityForm(@PathVariable("id") long id, Model model) {

        LOGGER.info("showUpdateCityForm() : {}", id);

        CityDto cityDto = cityService.loadById(id);
        model.addAttribute("cityForm", cityDto);

        return "cities/formCity";
    }

    @GetMapping(value = "/{id}")
    public String cityById(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
        LOGGER.info("Get from city table by id = {}", id);
        CityDto cityDto = cityService.loadById(id);
        if (cityDto == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("city", cityDto);

        return "cities/city";
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
    public String updateCity(@ModelAttribute("cityForm") @Validated CityDto cityDto,
                             BindingResult result, Model model,
                             final RedirectAttributes redirectAttributes) {
        LOGGER.info("Update city table by {}", cityDto);
        if (result.hasErrors()) {
            return "cities/formCity";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
//            if(city.isNew()){
//                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
//            }else{
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
         //   }

            cityService.update(cityDto);

            // POST/REDIRECT/GET
            return "redirect:/city/" + cityDto.getId();

            // POST/FORWARD/GET
            // return "user/list";

        }

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