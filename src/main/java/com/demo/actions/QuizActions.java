package com.demo.actions;

import com.demo.pages.Pages;
import org.openqa.selenium.By;

public class QuizActions {

    public void selectionTest(String[] answers) {
        for (String answer : answers) {
            String votingOptionsPath = String.format("//div[contains(@class, \"voting-options\")]//button[contains(.,\"%s\")]", answer);
            By votingOptions = By.xpath(votingOptionsPath);
            Pages.quizPage().clickButton(votingOptions);
        }
    }

    public void rankingSelectionTest(String[] answers) throws InterruptedException {
        for (int i = 0; i < Pages.quizPage().getDragAndDropElements().size() ; i++) {
            if(!Pages.quizPage().getDragAndDropElements().get(i).getText().equals(answers[i])) {
                String dragPath = String.format("//li[.//h4[text()=\"%s\"]]", answers[i]);
                By voitinOptions = By.xpath(dragPath);
                Pages.quizPage().receiveActions()
                        .moveToElement(Pages.quizPage().getElement(voitinOptions))
                        .clickAndHold(Pages.quizPage().getElement(voitinOptions))
                        .moveToElement(Pages.quizPage().getDragAndDropElements().get(i))
                        .release()
                        .build()
                        .perform();
            }
        }
    }

    public void singleSelectionAbstentionTest(String[] response) {
        if (response[0].equals("Abstain")) {
            Pages.quizPage().clickForAbstention();
            Pages.quizPage().clickForConfirmAbstention();
        } else {
            selectionTest(response);
        }
    }
}
