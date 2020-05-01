package com.example.e_examapp;

public class QuserstuinModel {
    private String question ,optionA,optionB,optionC,optionD,corectans;

    public QuserstuinModel(String question, String optionA, String optionB, String optionC, String optionD, String corectans) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.corectans = corectans;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorectans() {
        return corectans;
    }

    public void setCorectans(String corectans) {
        this.corectans = corectans;
    }
}

