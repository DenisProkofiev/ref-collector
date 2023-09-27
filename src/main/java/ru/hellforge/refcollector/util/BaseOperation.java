package ru.hellforge.refcollector.util;

import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class BaseOperation {

    public static boolean isIdValid(Long number) {
        return nonNull(number) && number >= 0;
    }
}
