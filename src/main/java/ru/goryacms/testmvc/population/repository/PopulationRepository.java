package ru.goryacms.testmvc.population.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.goryacms.testmvc.population.model.Population;

public interface PopulationRepository extends PagingAndSortingRepository<Population, Long> {
}