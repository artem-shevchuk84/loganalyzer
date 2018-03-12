package ua.com.escondido.service.impl;

import ua.com.escondido.model.LogFile;
import ua.com.escondido.model.LogLine;
import ua.com.escondido.service.Analyzer;

import java.util.*;

public class OneWordAnalyzer implements Analyzer<LogFile> {

    //May be implemented as Helper
    private Set<List<LogLine>> printedLines = new HashSet<>();

    private List<Integer> linesForIgnore = new ArrayList<>();

    @Override
    public void analyze(LogFile logFile) {
        List<LogLine> data = logFile.getRows();
        findSimilar(data);
    }

    private void findSimilar(List<LogLine> lines){
        if(lines.size() > 1){
            for (int j = 0; j < lines.size(); j++) {
                Map<Integer, List<LogLine>> linesToPrint = new TreeMap<>();
                Map<Integer, List<String>> wordsToPrint = new TreeMap<>();

                String[] firstLine = lines.get(j).getText();
                int wordCount = firstLine.length;
                for (int i = j + 1; i < lines.size(); i++) {
                    String[] logText = lines.get(i).getText();
                    if(Arrays.equals(firstLine, logText)){
                        linesForIgnore.add(i);
                        break;
                    }
                    if (wordCount != logText.length || linesForIgnore.contains(i))  {
                        break;
                    }
                    else {
                        // place to improve policy for make this code scalable
                        int notEqual = 0;
                        int index = 0;
                        String firstWord = "";
                        String anotherWord = "";
                        for (int k = 0; k < firstLine.length; k++) {
                            if (!firstLine[k].equals(logText[k])) {
                                firstWord = firstLine[k];
                                anotherWord = logText[k];
                                notEqual++;
                                index = k;
                            }
                            if (notEqual > 1) {
                                break;
                            }
                        }
                        if (notEqual == 1) {
                            saveToPrint(lines.get(j), linesToPrint, wordsToPrint, lines.get(i), index, firstWord, anotherWord);
                        }
                    }
                }
                printLines(linesToPrint, wordsToPrint);
            }
        }
    }

    //May be implemented as Helper
    private void saveToPrint(LogLine firstLine, Map<Integer, List<LogLine>> linesToPrint, Map<Integer, List<String>> wordsToPrint, LogLine iLine, int index, String firstWord, String anotherWord) {
        List<LogLine> logLines = linesToPrint.get(index);
        if(logLines == null){
            logLines = new ArrayList<>();
            logLines.add(firstLine);
            linesToPrint.put(index, logLines);
        }
        logLines.add(iLine);
        linesToPrint.replace(index, logLines);

        List<String> words = wordsToPrint.get(index);
        if(words == null){
            words = new ArrayList<>();
            words.add(firstWord);
            wordsToPrint.put(index, words);
        }
        words.add(anotherWord);
        wordsToPrint.replace(index, words);
    }

    //May be implemented as Helper
    private void printLines(Map<Integer, List<LogLine>> lines, Map<Integer, List<String>> words) {
        if(!lines.isEmpty()) {
            for (List<LogLine> newLines : lines.values()) {
                for (List<LogLine> printed : printedLines) {
                    if (printed.containsAll(newLines)) {
                        return;
                    }
                }
            }
            for (Integer idx : lines.keySet()) {
                for (LogLine line : lines.get(idx)) {
                    line.print();
                }
                for (String word : words.get(idx)) {
                    System.out.println(word);
                }
            }
            printedLines.addAll(lines.values());
        }
    }
}
