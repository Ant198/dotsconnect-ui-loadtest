package com.demo;

import com.demo.actions.Actions;
import com.demo.core.base.BaseTest;
import com.demo.pages.Pages;
import com.demo.utils.Constants;
import com.demo.utils.UILoadTimer;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.demo.utils.CsvReader;
import java.util.List;

import static com.demo.utils.UILoadTimer.*;
import static com.demo.utils.UILoadTimer.timerLog;

@Epic("Performance Testing")
@Feature("UI Load Test")
@Owner("QA Fedorov Anton")
public class QuizUiLoadTest extends BaseTest {

    @DataProvider(name = "quizData", parallel = true)
    public Object[][] quizData() throws CsvException {
        List<String[]> rows = CsvReader.readCSV(Constants.CSVPATH);
        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i);
        }
        return data;
    }

    @Test(description = "FirstTest", dataProvider = "quizData")
    public void QuizUiLoadTest(String[] row) throws InterruptedException, CsvException {
        String userName = row[0];
        String password = row[1];
        String[] multipleSelectionResponse = row[2].split(",");
        String[] singleSelectionResponse = new String[]{row[3]};
        String[] exactSelectionResponse = row[4].split(",");
        String[] rankingSelectionResponse = row[5].split(",");
        String[] distributedVotingResponse = row[6].split(",");
        String textInputResponse = row[7];
        String singleSelectionAbstentionResponse = row[8];

        Assert.assertEquals(Actions.mainActions().getCurrentUrl(), Constants.URL, "HomePage did not open");
        Pages.homePage().clickSignInButton();
        timerLog("Login User", () -> {
            Pages.loginPage().clickUserNameSection();
            try {
                Pages.loginPage().setUsername(userName);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Pages.loginPage().setPassword(password);
            Pages.loginPage().clickLoginButton();
        });
        Assert.assertFalse(Pages.loginPage().isErrorMessageVizibility(), "Too many requests");
        Assert.assertTrue(Pages.dashBoardPage().isPastEventsVizible(), "dashboard did not open");
        Pages.dashBoardPage().clickPastEventsButton();
        Pages.dashBoardPage().clickDemoEvent();
        Pages.quizPage().selectQuestion(1);
        Actions.quizActions().selectionTest(multipleSelectionResponse);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after multiple selection");
        Pages.quizPage().selectQuestion(2);
        Actions.quizActions().selectionTest(singleSelectionResponse);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after single selection");
        Pages.quizPage().selectQuestion(3);
        Actions.quizActions().selectionTest(exactSelectionResponse);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after exact selection");
        Pages.quizPage().selectQuestion(4);
        Actions.quizActions().rankingSelectionTest(rankingSelectionResponse);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after ranking selection");
        Pages.quizPage().selectQuestion(5);
        Pages.quizPage().distributedVotingTest(distributedVotingResponse[0], distributedVotingResponse[1]);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after distributed selection");
        Pages.quizPage().selectQuestion(6);
        Pages.quizPage().textInputTest(textInputResponse);
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "vote was submited after text input");
        Pages.quizPage().selectQuestion(7);
        Actions.quizActions().singleSelectionAbstentionTest(new String[]{singleSelectionAbstentionResponse});
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "vote was submited after single selection abstention ");
    }
}