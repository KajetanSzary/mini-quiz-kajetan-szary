package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    Button startButton, resetButton, answerA, answerB, answerC;

    ArrayList<Question> questions;
    int currentIndex = 0;
    int score = 0;
    ArrayList<Question> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);

        resetUI();

        loadQuestions();

        startButton.setOnClickListener(v -> startQuiz());

        resetButton.setOnClickListener(v -> {
            score = 0;
            currentIndex = 0;
            scoreText.setText("Wynik: 0");
            resetUI();
        });

        answerA.setOnClickListener(v -> checkAnswer(questions.get(currentIndex).a));
        answerB.setOnClickListener(v -> checkAnswer(questions.get(currentIndex).b));
        answerC.setOnClickListener(v -> checkAnswer(questions.get(currentIndex).c));
    }

    private void resetUI() {
        questionText.setText("");
        answerA.setVisibility(View.INVISIBLE);
        answerB.setVisibility(View.INVISIBLE);
        answerC.setVisibility(View.INVISIBLE);
    }

    private void loadQuestions() {
        questions = new ArrayList<>();

        questions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("Ile sekund ma minuta", "300", "60", "400", "60"));
        questions.add(new Question("Największa planeta?", "Mars", "Jowisz", "Ziemia", "Jowisz"));
        questions.add(new Question("Pierwiastek H to:", "Wodór", "Hel", "Tlen", "Wodór"));
        questions.add(new Question("Jaki kolor to nardo:", "Zielony", "Szary", "Czerwony", "Szary"));
        questions.add(new Question("Stolica Polski:", "Kraków", "Warszawa", "Wrocław", "Warszawa"));

        // losowanie 5 pytań
        Collections.shuffle(questions);
        quizList = new ArrayList<>(questions.subList(0, 5));
        questions = quizList;
    }

    private void startQuiz() {
        startButton.setVisibility(View.INVISIBLE);

        answerA.setVisibility(View.VISIBLE);
        answerB.setVisibility(View.VISIBLE);
        answerC.setVisibility(View.VISIBLE);

        showQuestion();
    }

    private void showQuestion() {
        Question q = questions.get(currentIndex);

        questionText.setText(q.text);
        answerA.setText("A: " + q.a);
        answerB.setText("B: " + q.b);
        answerC.setText("C: " + q.c);
    }

    private void checkAnswer(String answer) {
        if (answer.equals(questions.get(currentIndex).correct)) {
            score++;
            scoreText.setText("Wynik: " + score);
        }

        currentIndex++;

        if (currentIndex >= 5) {
            questionText.setText("Koniec quizu! Twój wynik: " + score + " / 5");
            answerA.setVisibility(View.INVISIBLE);
            answerB.setVisibility(View.INVISIBLE);
            answerC.setVisibility(View.INVISIBLE);
        } else {
            showQuestion();
        }
    }
}
