package ru.hellforge.refcollector.util;

import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.nonNull;

@Component
public class BaseOperation {

    public static boolean isIdValid(Long number) {
        return nonNull(number) && number >= 0;
    }

    public static boolean collectionNotEmpty(Collection<?> collection) {
        return !(collection == null || collection.isEmpty());
    }

    public static boolean notEqual(Object first, Object second) {
        return !first.equals(second);
    }
}
