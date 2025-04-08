package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

import java.util.List;

import static java.util.Arrays.stream;

public class DashBoardPage extends PageTools {

    private final By pastEvents = By.xpath("//button[text()=\"Past Events\"]");
    private final By demoEvent = By.xpath("//a[@href=\"/demo/demo-event\"]");

    public boolean isPastEventsVizible() throws InterruptedException {
        Thread.sleep(10000);
        return isCondition(Condition.exist, pastEvents);
    }
    public void clickPastEventsButton() {
        click(pastEvents);
    }
    public void clickDemoEvent() {
       click(demoEvent);
    }
}