package com.example.scooterrentcontrolsystem.service;

import com.example.scooterrentcontrolsystem.controller.exception.EntityNotFoundByIdException;
import org.springframework.lang.NonNull;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.Map;

public interface AbstractService<T> {
    void create(T element) throws NonUniqueResultException;

    T update(T element);

    void deleteById(Long id) throws EntityNotFoundByIdException;

    List<T> getAll(@NonNull Map<String, Object> fieldNamesAndValuesToSelectBy,
                   String orderBy,
                   boolean asc,
                   int limit);

    T getById(Long id) throws EntityNotFoundByIdException;

    <DTO, O> O updateEntityFromDto(O original, DTO dto, Class<O> originalClass);

    Map<String, Object> getMapOfObjectFieldsAndValues(Object model);
}
