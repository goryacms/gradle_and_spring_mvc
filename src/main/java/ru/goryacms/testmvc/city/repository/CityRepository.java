package ru.goryacms.testmvc.city.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.goryacms.testmvc.city.model.City;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
}