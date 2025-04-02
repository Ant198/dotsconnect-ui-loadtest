package com.demo.pages;

import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class HomePage extends PageTools {

    private final By signInButton = By.xpath("//a[@href=\"/sign-in\"]");

    public void clickSignInButton() {
        click(signInButton);
    }
}