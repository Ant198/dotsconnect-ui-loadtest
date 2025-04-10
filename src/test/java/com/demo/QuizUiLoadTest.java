package com.demo;

import com.demo.actions.Actions;
import com.demo.core.base.BaseTest;
import com.demo.pages.Pages;
import com.demo.utils.Constants;
import com.demo.utils.CsvReader;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Performance Testing")
@Feature("UI Load Test")
@Owner("QA Fedorov Anton")
public class QuizUiLoadTest extends BaseTest {

    @DataProvider(name = "quizData", parallel = true)
    public Object[][] quizData() throws CsvException {
        List<String[]> rows = CsvReader.readCSV(Constants.CSVPATH);
        Object[][] data = new Object[rows.size() - 2][1];
        for (int i = 0; i < rows.size() - 2; i++) {
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
        String[] singleSelectionAbstentionResponse = new String[]{row[8]};
        Assert.assertEquals(Actions.mainActions().getCurrentUrl(), Constants.URL, "HomePage did not open");
        Pages.homePage().clickSignInButton();
        Pages.loginPage().clickUserNameSection();
        Assert.assertTrue(Pages.loginPage().isUserNameVisibility(), "user name field did not exist");
        Pages.loginPage().setUsername(userName);
        Pages.loginPage().setPassword(password);
        Pages.loginPage().clickLoginButton();
        Assert.assertTrue(Pages.dashBoardPage().isPastEventsVizible(), "dashboard did not open");
        Pages.dashBoardPage().clickPastEventsButton();
        Pages.dashBoardPage().clickDemoEvent();
        Pages.quizPage().selectQuestion(1);
        Actions.quizActions().selectionTest(multipleSelectionResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after multiple selection");
        Pages.quizPage().selectQuestion(2);
        Actions.quizActions().selectionTest(singleSelectionResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after single selection");
        Pages.quizPage().selectQuestion(3);
        Actions.quizActions().selectionTest(exactSelectionResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after exact selection");
        Pages.quizPage().selectQuestion(4);
        Actions.quizActions().rankingSelectionTest(rankingSelectionResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after ranking selection");
        Pages.quizPage().selectQuestion(5);
        Pages.quizPage().distributedVotingTest(distributedVotingResponse[0], distributedVotingResponse[1]);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "Vote was submitted after distributed selection");
        Pages.quizPage().selectQuestion(6);
        Pages.quizPage().textInputTest(textInputResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "vote was submited after text input");
        Pages.quizPage().selectQuestion(7);
        Actions.quizActions().singleSelectionAbstentionTest(singleSelectionAbstentionResponse);
        //Pages.quizPage().submitVote();
        Assert.assertFalse(Pages.quizPage().isVoteSubmited(), "vote was submited after single selection abstention ");
    }
}