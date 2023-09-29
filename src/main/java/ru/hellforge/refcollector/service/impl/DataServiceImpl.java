package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hellforge.refcollector.service.DataService;
import ru.hellforge.refcollector.service.ReferenceService;

import java.sql.SQLData;

@Component
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final ReferenceService referenceService;
    @Override
    public SQLData getDefaultData() {
        return referenceService.getAllGlobalReference();
    }
}
