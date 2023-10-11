package ru.hellforge.refcollector.service;

import java.util.List;

public interface BaseServiceInterface {
    void compareImportDataWithStorageData(List<String> importData, List<String> storageData);
}