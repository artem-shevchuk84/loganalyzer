package ua.com.escondido.service;

import ua.com.escondido.model.LogFile;

public interface Analyzer <E extends LogFile> {

    void analyze(E data);
}
