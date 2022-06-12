package com.example.ailatrieuphu;

import java.util.List;

public class Question {
    private int number;
    private String contentQuestion;
    private List<Answer> answerList;

    public Question(int number, String contentQuestion, List<Answer> answerList) {
        this.number = number;
        this.contentQuestion = contentQuestion;
        this.answerList = answerList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
