package com.demo.actions;

public class Actions {
    private static MainActions mainActions;
    private static QuizActions quizActions;

    public static QuizActions quizActions() {
        if (quizActions == null) {
            quizActions = new QuizActions();
        }
        return quizActions;
    }

    public static MainActions mainActions() {
        if (mainActions == null) {
            mainActions = new MainActions();
        }
        return mainActions;
    }
}