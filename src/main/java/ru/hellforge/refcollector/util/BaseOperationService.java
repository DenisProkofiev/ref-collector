package ru.hellforge.refcollector.util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;
import static ru.hellforge.refcollector.util.Constants.OBJECT_CODE_NOT_CREATED;

@Component
public class BaseOperationService {
    public static boolean isIdValid(Long number) {
        return nonNull(number) && number >= 1;
    }

    public static boolean collectionIsEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.stream().allMatch(Objects::isNull);
    }

    public static boolean collectionNotEmpty(Collection<?> collection) {
        return !collectionIsEmpty(collection);
    }

    public static boolean notEqual(Object first, Object second) {
        return !first.equals(second);
    }

    public static boolean isArgumentExist(String[] arguments, int argumentIndex) {
        return !isEmpty(arguments) && arguments.length > argumentIndex && nonNull(arguments[argumentIndex]);
    }

    public static String convertUUIDToString(UUID objectCode) {
        return isNull(objectCode) ? OBJECT_CODE_NOT_CREATED : objectCode.toString();
    }

    public static UUID convertStringToUUID(String objectCode) {
        return isNull(objectCode) ? null : UUID.fromString(objectCode);
    }

    public static List<UUID> convertListStringToListUUID(List<String> objectCodeList) {
        return objectCodeList.stream()
                .map(BaseOperationService::convertStringToUUID)
                .collect(toList());
    }
}