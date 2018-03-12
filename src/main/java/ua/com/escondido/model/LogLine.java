package ua.com.escondido.model;

import java.util.Arrays;

public class LogLine {

    private String[] metadata;

    private String[] text;

    public String[] getMetadata() {
        return metadata;
    }

    public void setMetadata(String[] metadata) {
        this.metadata = metadata;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void print() {
        System.out.println(Arrays.toString(getMetadata()) + Arrays.toString(getText()));
    }
}
