package ru.goryacms.testmvc.language.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.goryacms.testmvc.language.model.Language;
import ru.goryacms.testmvc.language.model.LanguageId;

public interface LanguageRepository extends PagingAndSortingRepository<Language, LanguageId> {
}