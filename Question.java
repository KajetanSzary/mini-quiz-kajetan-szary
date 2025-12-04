package com.example.myapplication;

public class Question {
    public String text;
    public String a;
    public String b;
    public String c;
    public String correct;

    public Question(String text, String a, String b, String c, String correct) {
        this.text = text;
        this.a = a;
        this.b = b;
        this.c = c;
        this.correct = correct;
    }
}
