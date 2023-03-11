package com.example.scooterrentcontrolsystem.dao;

import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AbstractDao<T> {
    void create(T element);

    Optional<T> getById(Long id);

    T update(T element);

    void delete(T element);

    List<T> getAll(@NonNull Map<String, Object> mapOfFieldNamesAndValuesToSelectBy,
                   String orderBy,
                   boolean asc,
                   int limit);
}
