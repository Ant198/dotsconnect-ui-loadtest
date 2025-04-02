package com.demo.pages;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

import java.util.List;

public class QuizPage extends PageTools {
    private final By questionsLocator = By.xpath("//div[contains(@class, \"gap-1\")]");
    //private final By answer = By.xpath("//div[@class=\"voting-options\"]/");
    private final By submitVoteLocator = By.xpath("//button[text()=\"Submit Vote\"]");

    public List<SelenideElement> getElements() {
        return getElements(questionsLocator);
    }

    public void submitVote() {
        click(submitVoteLocator);
    }

    public void MultipleSelectionTest(String[] answer) {
        By votingOptions = By.xpath("//div[contains(@class, \"voting-options\")]//button");
    }
}