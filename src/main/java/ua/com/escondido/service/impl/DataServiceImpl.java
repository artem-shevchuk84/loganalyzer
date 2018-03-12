package ua.com.escondido.service.impl;

import ua.com.escondido.model.LogFile;
import ua.com.escondido.model.LogLine;
import ua.com.escondido.service.DataService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class DataServiceImpl implements DataService{

    public LogFile load() {
        LogFile logFile = new LogFile();
        try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()))) {
            lines.forEach(line -> readLines(line, logFile));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return logFile;
    }

    private void readLines(String text, LogFile logFile){
        LogLine line = new LogLine();
        int endOfMetadata = text.indexOf(" ", text.indexOf(" ") + 1); //TODO: lets use as constant as simple solution
        String metadata = text.substring(0, endOfMetadata);
        String log = text.substring(endOfMetadata + 1, text.length());
        line.setMetadata(metadata.split(" "));
        line.setText(log.split(" "));

        //System.out.println(Arrays.toString(line.getMetadata()));
        //System.out.println(Arrays.toString(line.getText()));

        logFile.put(line);
    }
}
