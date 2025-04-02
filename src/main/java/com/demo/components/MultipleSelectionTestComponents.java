package com.demo.components;

import org.openqa.selenium.By;

public class MultipleSelectionTestComponents {
    private final By votingOptions = By.xpath("//div[contains(@class, \"voting-options\")]//button");

    public void getAnswer(String[] answers){
        for (String answer : answers) {
            
        }
    }
}
