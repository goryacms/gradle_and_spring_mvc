package ru.goryacms.testmvc.city.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.goryacms.testmvc.city.dto.CityDto;
import ru.goryacms.testmvc.city.model.City;

@Component
public class CityFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CityDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CityDto city = (CityDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.cityForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "district", "NotEmpty.cityForm.district");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "population", "NotEmpty.cityForm.population");

        if(city.getPopulation()<=0){
            errors.rejectValue("population", "NotEmpty.cityForm.population");
        }
    }


}