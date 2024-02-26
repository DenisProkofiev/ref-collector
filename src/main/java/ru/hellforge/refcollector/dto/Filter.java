package ru.hellforge.refcollector.dto;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;

public abstract class Filter {

    public boolean isFilterEmpty() {
        return isNull(this) ||
               Arrays.stream(this.getClass()
                       .getDeclaredFields())
                       .peek(field -> {
                           try {
                               field.get(field.getName());
                           } catch (IllegalAccessException e) {
                               throw new RuntimeException(e);
                           }
                       })
                       .allMatch(Objects::isNull);
    }
}
