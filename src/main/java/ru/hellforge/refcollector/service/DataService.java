package ru.hellforge.refcollector.service;

import java.io.IOException;

public interface DataService {
    void saveJsonToFile(Object object, String target) throws IOException;
}
