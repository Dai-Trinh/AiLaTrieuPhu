package com.example.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtQuestion, txtContentQuestion;
    private TextView txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4;
    private List<Question> mListQuestion;
    private int curentQuestion = 0;
    private Question mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        mListQuestion = getListQuestion();
        setDataQuestion(mListQuestion.get(curentQuestion));
    }

    private void setDataQuestion(Question question) {
        if (question == null){
            return;
        }

        txtAnswer1.setBackgroundResource(R.drawable.bg_blue_coner_30);
        txtAnswer2.setBackgroundResource(R.drawable.bg_blue_coner_30);
        txtAnswer3.setBackgroundResource(R.drawable.bg_blue_coner_30);
        txtAnswer4.setBackgroundResource(R.drawable.bg_blue_coner_30);

        mQuestion = question;
        String titleQuestion = "Question" + question.getNumber();
        txtQuestion.setText(titleQuestion);
        txtContentQuestion.setText(question.getContentQuestion());
        txtAnswer1.setText(question.getAnswerList().get(0).getContent());
        txtAnswer2.setText(question.getAnswerList().get(1).getContent());
        txtAnswer3.setText(question.getAnswerList().get(2).getContent());
        txtAnswer4.setText(question.getAnswerList().get(3).getContent());

        txtAnswer1.setOnClickListener(this);
        txtAnswer2.setOnClickListener(this);
        txtAnswer3.setOnClickListener(this);
        txtAnswer4.setOnClickListener(this);
    }

    private void AnhXa() {
        txtQuestion = findViewById(R.id.tv_question_number);
        txtContentQuestion = findViewById(R.id.tv_content_question);
        txtAnswer1 = findViewById(R.id.tv_answer1);
        txtAnswer2 = findViewById(R.id.tv_answer2);
        txtAnswer3 = findViewById(R.id.tv_answer3);
        txtAnswer4 = findViewById(R.id.tv_answer4);

    }

    private List<Question> getListQuestion(){
        List<Question> list = new ArrayList<>();
        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("Gà", true));
        answerList1.add(new Answer("Cá", false));
        answerList1.add(new Answer("Bò", false));
        answerList1.add(new Answer("Lợn", false));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("Cá", false));
        answerList2.add(new Answer("Đá", true));
        answerList2.add(new Answer("Gối", false));
        answerList2.add(new Answer("Nước", false));

        List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer("Thái Nguyên", false));
        answerList3.add(new Answer("Hải Phòng", false));
        answerList3.add(new Answer("Hà Nội", true));
        answerList3.add(new Answer("Bắc Ninh", false));

        list.add(new Question(1, "Con nào là gia cầm?", answerList1));
        list.add(new Question(2, "Chân cứng, ... mềm", answerList2));
        list.add(new Question(3, "Đâu là thủ đô của Việt Nam", answerList3));
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_answer1:
                txtAnswer1.setBackgroundResource(R.drawable.bg_orgine_coner_10);
                checkQuestionCorrect(txtAnswer1, mQuestion, mQuestion.getAnswerList().get(0));
                break;

            case R.id.tv_answer2:
                txtAnswer2.setBackgroundResource(R.drawable.bg_orgine_coner_10);
                checkQuestionCorrect(txtAnswer2, mQuestion, mQuestion.getAnswerList().get(1));
                break;

            case R.id.tv_answer3:
                txtAnswer3.setBackgroundResource(R.drawable.bg_orgine_coner_10);
                checkQuestionCorrect(txtAnswer3, mQuestion, mQuestion.getAnswerList().get(2));
                break;

            case R.id.tv_answer4:
                txtAnswer4.setBackgroundResource(R.drawable.bg_orgine_coner_10);
                checkQuestionCorrect(txtAnswer4, mQuestion, mQuestion.getAnswerList().get(3));
                break;
        }

    }

    private void checkQuestionCorrect(TextView textView, Question question, Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(answer.isCheck()){
                    textView.setBackgroundResource(R.drawable.bg_green_coner_30);
                    nextQuestion();
                } else {
                    textView.setBackgroundResource(R.drawable.bg_red_coner_30);
                    showAnswerCorrect(question);
                    gameOver();
                }
            }
        }, 1000);
    }

    private void nextQuestion(){
        if(curentQuestion == mListQuestion.size() - 1){
            showDialog("You Win");
        } else {
            curentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestion(mListQuestion.get(curentQuestion));
                }
            }, 1000);
        }

    }

    private void showAnswerCorrect(Question question){
        if(question == null || question.getAnswerList() == null || question.getAnswerList().isEmpty()){
            return;
        }

        if(question.getAnswerList().get(0).isCheck()){
            txtAnswer1.setBackgroundResource(R.drawable.bg_green_coner_30);
        } else if(question.getAnswerList().get(1).isCheck()){
            txtAnswer2.setBackgroundResource(R.drawable.bg_green_coner_30);
        } else if(question.getAnswerList().get(2).isCheck()){
            txtAnswer3.setBackgroundResource(R.drawable.bg_green_coner_30);
        } else if(question.getAnswerList().get(3).isCheck()){
            txtAnswer4.setBackgroundResource(R.drawable.bg_green_coner_30);
        }
    }

    private void gameOver(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");

            }
        },1000);

    }

    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                curentQuestion = 0;
                setDataQuestion(mListQuestion.get(curentQuestion));
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}