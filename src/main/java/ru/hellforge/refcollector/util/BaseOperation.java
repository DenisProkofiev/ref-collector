package ru.hellforge.refcollector.util;

import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class BaseOperation {

    public static boolean isIdValid(Long number) {
        return nonNull(number) && number >= 0;
    }

    public static boolean isArgumentExist(String[] arguments, int argumentIndex) {
        return !isEmpty(arguments) && arguments.length > argumentIndex && nonNull(arguments[argumentIndex]);
    }
}