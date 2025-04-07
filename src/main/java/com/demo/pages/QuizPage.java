package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class QuizPage extends PageTools {
    private final By voteSubmittedLocator = By.xpath("//h3[text()=\"Vote Submitted\"]");
    private final By distributedVotingTestLocator1 = By.xpath("(//input[@type=\"text\"])[1]");
    private final By distributedVotingTestLocator2 = By.xpath("(//input[@type=\"text\"])[2]");
    private final By submitVoteLocator = By.xpath("//button[text()=\"Submit Vote\"]");
    private final By textInputTestLocator = By.xpath("//div[contains(@class, \"voting-options\")]//textarea");
    private final By dropAndDragOptionsLocator = By.xpath("//li//h4");
    private final By abstentionLocator = By.xpath("//div[contains(@class, \"voting-options\")]//button[text()=\"Abstain\"]");
    private final By abstentionConfirmLocator = By.xpath("//div[contains(@class, \"voting-options\")]//button[contains(., \"Confirm Abstention\")]");

    public boolean isVoteSubmited() {
        return isCondition(Condition.exist, voteSubmittedLocator);
    }

    public SelenideElement getElement(By by) {
        return getSelenideElement(by);
    }

    public Actions receiveActions() {
        return getActions();
    }

    public void submitVote() {
        click(submitVoteLocator);
    }

    public List<SelenideElement> getDragAndDropElements() {
        return getElements(dropAndDragOptionsLocator);
    }

    public void selectQuestion(int index) {
        String questionsPath = String.format("//div[contains(@class, \"gap-1\")]/button[contains(.,\"%d\")]", index);
        click(By.xpath(questionsPath));

    }
    public void clickButton(By by) {
        click(by);
    }

    public void distributedVotingTest(String value1, String value2) {
        type(value1, distributedVotingTestLocator1);
        type(value2, distributedVotingTestLocator2);
    }

    public void textInputTest(String text) {
        type(text, textInputTestLocator);
    }

    public void clickForAbstention() {
        click(abstentionLocator);
    }

    public void clickForConfirmAbstention() {
        click(abstentionConfirmLocator);
    }
}