package com.example.scooterrentcontrolsystem.service.impl;

import com.example.scooterrentcontrolsystem.controller.exception.EntityNotFoundByIdException;
import com.example.scooterrentcontrolsystem.dao.AbstractDao;
import com.example.scooterrentcontrolsystem.service.AbstractService;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractServiceImpl<T, D extends AbstractDao<T>> implements AbstractService<T> {
    protected abstract D getDefaultDao();
    protected abstract Class<T> getDefaultEntityClass();

    @Transactional
    @Override
    public void create(T element) {
        getDefaultDao().create(element);
    }

    @Transactional
    @Override
    public T update(T element) {
        return getDefaultDao().update(element);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        getDefaultDao().delete(getDefaultDao().getById(id)
                .orElseThrow(() -> new EntityNotFoundByIdException()));
    }

    @Override
    public List<T> getAll(@NonNull Map<String, Object> fieldNamesAndValuesToSelectBy,
                          String orderBy, boolean ascending, int limit) {
        fieldNamesAndValuesToSelectBy.entrySet().removeIf(entry -> Objects.isNull(entry.getValue()));
        return getDefaultDao().getAll(fieldNamesAndValuesToSelectBy, orderBy, ascending, limit);
    }

    @Override
    public T getById(Long id) {
        return getDefaultDao().getById(id)
                .orElseThrow(() -> new EntityNotFoundByIdException());
    }

    //TODO: to refactor
    @Override
    public <DTO, O> O updateEntityFromDto(O original, DTO dto, Class<O> originalClass) {
        Field[] dtoFields = dto.getClass().getDeclaredFields();

        Arrays.stream(dtoFields).forEach(dtoField -> {
            try {
                dtoField.setAccessible(true);
                if (Objects.isNull(dtoField.get(dto))) {
                    // Null-fields in the DTO are not set to the original,
                    // so that it will be able to send only 1 modified field to DTO
                    // meanwhile others will be set to null by default
                    return;
                }
                Field originalField = original.getClass().getDeclaredField(dtoField.getName());
                originalField.setAccessible(true);
                originalField.set(original, dtoField.get(dto));
            } catch (NoSuchFieldException e) {
//                log.error("У оригинального объекта нет поля с именем " + dtoField.getName(), e);
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
//                log.error("Поле объекта DTO не доступно для модификации", e);
                throw new RuntimeException(e);
            }
        });
        return original;
    }

    //TODO: try in dto updating
    @Override
    public Map<String, Object> getMapOfObjectFieldsAndValues(Object model) {
        if (Objects.isNull(model)) {
            return new HashMap<>();
        }

        Field[] fields = model.getClass().getDeclaredFields();
        Map<String, Object> result = new HashMap<>();

        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(model);
                if (Objects.nonNull(fieldValue)) {
                    result.put(fieldName, fieldValue);
                }
            } catch (IllegalAccessException e) {
//                log.error("Поле объекта DTO не доступно для модификации", e);
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
