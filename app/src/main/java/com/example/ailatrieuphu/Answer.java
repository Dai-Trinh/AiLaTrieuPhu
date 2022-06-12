package com.example.ailatrieuphu;

public class Answer {
    private String content;
    private boolean isCheck;

    public Answer(String content, boolean isCheck) {
        this.content = content;
        this.isCheck = isCheck;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
