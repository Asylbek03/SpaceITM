package com.example.spaceitm.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Epic {

    private String epicDate;
    private String epicImage;
    private String epicCaption;

    public Epic() {
    }

    public Epic(String epicDate, String epicImage, String epicCaption) {
        super();
        this.epicDate = epicDate;
        this.epicImage = epicImage;
        this.epicCaption = epicCaption;
    }

    public String getEpicDate() {
        return epicDate;
    }

    public void setEpicDate(String epicDate) {
        this.epicDate = epicDate;
    }

    public String getEpicImage() {
        return epicImage;
    }

    public void setEpicImage(String epicImage) {
        this.epicImage = epicImage;
    }

    public String getEpicCaption() {
        return epicCaption;
    }

    public void setEpicCaption(String epicCaption) {
        this.epicCaption = epicCaption;
    }

    public String getFormattedEpicDate() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(epicDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Epic [epicDate=" + epicDate + ", epicImage=" + epicImage + ", epicCaption=" + epicCaption + "]";
    }
}
