package ru.goryacms.testmvc.country.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.goryacms.testmvc.country.model.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
}