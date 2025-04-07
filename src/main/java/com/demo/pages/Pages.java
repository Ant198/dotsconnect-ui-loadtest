package com.demo.pages;

import com.demo.core.allure.AllureLogger;

public class Pages extends AllureLogger {
    private static LoginPage loginPage;
    private static DashBoardPage dashBoardPage;
    private static HomePage homePage;
    private static QuizPage quizPage;

    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static DashBoardPage dashBoardPage() {
        if (dashBoardPage == null) {
            dashBoardPage = new DashBoardPage();
        }
        return dashBoardPage;
    }

    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static QuizPage quizPage() {
        if (quizPage == null) {
            quizPage = new QuizPage();
        }
        return quizPage;
    }
}