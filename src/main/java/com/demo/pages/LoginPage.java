package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class LoginPage extends PageTools {

    private final By userNameSection = By.xpath("//button[contains(., \"Username\")]");
    private final By usernameField = By.xpath("//input[@placeholder=\"Username\"]");
    private final By passwordField = By.xpath("//input[@placeholder=\"Password\"]");
    private final By signInButton = By.xpath("//button[contains(., \"Sign in\")]");
    private final By errorMessage = By.xpath("//div[contains(@class, \"text-destructive\")]//span");

    public boolean isErrorMessageVizibility() {
        return isCondition(Condition.exist, errorMessage);
    }

    public void clickUserNameSection() {
        click(userNameSection);
    }

    public boolean isUserNameVisibility() throws InterruptedException {
        Thread.sleep(10000);
        return isCondition(Condition.visible ,usernameField);
    }

    public void setUsername(String username) throws InterruptedException {
        type(username, usernameField);
    }

    public void setPassword(String password) {
        type(password, passwordField);
    }

    public void clickLoginButton() {
        click(signInButton);
    }

}