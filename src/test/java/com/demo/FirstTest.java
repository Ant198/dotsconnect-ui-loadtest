package com.demo;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.BaseTest;
import com.demo.pages.Pages;
import com.demo.utils.Constants;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import com.demo.utils.CsvReader;

import java.util.List;

@Epic("Test Epic")
@Feature("Test feature")
@Owner("QA Bohomazov Dmytro")
public class FirstTest extends BaseTest {

    @Test(description = "FirstTest")
    public void firstTest() throws InterruptedException, CsvException {
        List<String[]> data = CsvReader.readCSV(Constants.CSVPATH);
        for(String[]row : data) {
            System.setProperty("userName", row[0]);
            System.setProperty("password", row[1]);
        }
        String userName = System.getProperty("userName");
        String password = System.getProperty("password");
        //logInfo("searchWord is " + searchWord);
        Thread.sleep(2000);
        Pages.homePage().clickSignInButton();
        Pages.loginPage().clickUserNameSection();
        Pages.loginPage().setUsername(userName);
        Pages.loginPage().setPassword(password);
        Pages.loginPage().clickSignInButton();
        Pages.dashBoardPage().clickPastEventsButton();
        Pages.dashBoardPage().clickDemoEvent();
        /*
        Pages.searchPage().waitForElements();
        List<SelenideElement> elements = Pages.searchPage().getFoundElements();
        Pages.searchPage().checkForSearchWord(searchWord, elements);

         */
    }
}
