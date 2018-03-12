package ua.com.escondido;

import ua.com.escondido.model.LogFile;
import ua.com.escondido.service.Analyzer;
import ua.com.escondido.service.DataService;
import ua.com.escondido.service.impl.DataServiceImpl;
import ua.com.escondido.service.impl.OneWordAnalyzer;

public class LogAnalyzerApp {

    public static void main(String[] args) {
        DataService dataService = new DataServiceImpl();
        Analyzer<LogFile> logFileAnalyzer = new OneWordAnalyzer();

        LogFile logFile = dataService.load();
        logFileAnalyzer.analyze(logFile);
    }
}
