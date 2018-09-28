package ru.goryacms.testmvc.city.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.goryacms.testmvc.city.model.City;

import java.util.List;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    List<City> findAllByOrderById();
}