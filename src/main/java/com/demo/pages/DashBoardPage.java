package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class DashBoardPage extends PageTools {

    private final By pastEvents = By.xpath("//button[text()=\"Past Events\"]");
    private final By demoEvent = By.xpath("//a[@href=\"/demo/demo-event\"]");

    public boolean isPastEventsVizible() {
        waitForElementVisibility(pastEvents);
        return isCondition(Condition.visible, pastEvents);
    }
    public void clickPastEventsButton() {
        waitForElementVisibility(pastEvents);
        click(pastEvents);
    }
    public void clickDemoEvent() {
       click(demoEvent);
    }

}