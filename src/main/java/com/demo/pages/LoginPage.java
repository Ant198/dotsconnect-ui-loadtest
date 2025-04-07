package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginPage extends PageTools {

    private final By userNameSection = By.xpath("//button[contains(., \"Username\")]");
    private final By usernameField = By.xpath("//input[@placeholder=\"Username\"]");
    private final By passwordField = By.xpath("//input[@placeholder=\"Password\"]");
    private final By signInButton = By.xpath("//button[contains(., \"Sign in\")]");
    private final By errorMessage = By.xpath("//div[contains(@class, \"text-destructive\")]//span");

    public boolean isErrorMessageVizibility() {
        return isCondition(Condition.visible,errorMessage);
    }

    public void clickUserNameSection() {
        click(userNameSection);
    }

    public void setUsername(String username) throws InterruptedException {
        //waitForElementPresent(userNameSection);
        getSelenideElement(usernameField).shouldBe(Condition.visible, Duration.ofSeconds(40));
        type(username, usernameField);
    }

    public void setPassword(String password) {
        type(password, passwordField);
    }

    public void clickLoginButton() {
        click(signInButton);
    }

}