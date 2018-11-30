package model.domain;

import javafx.collections.ObservableList;
import model.db.QuestionDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test
{
    private ObservableList<Question> questions;

    private int questionPointer;
    private int score;
    private Map<String, Integer> scoresOfCategories;


    public Test(Facade facade) {
        questions = facade.getQuestions();
        List<String> titles = facade.getCategoryTitles();
        scoresOfCategories = new HashMap<>();
        for (String title : titles) {
            scoresOfCategories.put(title, 0);
        }
        this.setQuestionPointer(0);
        this.setScore(0);
    }

    public void questionOfCategoryCorrect(String categoryTitle) {
        scoresOfCategories.put(categoryTitle, scoresOfCategories.get(categoryTitle) + 1);
    }

    public int getScoreOfCategory(String categoryTitle) {
        return scoresOfCategories.get(categoryTitle);
    }

    public void setQuestionPointer(int i) {
        this.questionPointer = i;
    }
    /**
     * Returns the current Question.
     * @return
     * Question that is currently being tested
     */

    public Question getCurrentQuestion()
    {
        return questions.get(questionPointer);
    }

    /**
     * Increments the questionPointer, allowing the test to continue to the next Question.
     */

    public void advanceTest()
    {
        if(questionPointer == questions.size() - 1)
        {
            questionPointer = -1;
        }
        else
        {
            questionPointer++;
        }
    }

    public boolean canAdvance() {
        return questionPointer + 1 < questions.size();
    }
    /**
     * Returns whether the test is finished or not.
     * @return
     * true if the questionPointer is -1, false if anything else
     */

    public boolean isFinished()
    {
        if(questionPointer == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the statements of the current Question.
     * @return
     * A List of Strings containing the statements
     */

    public List<String> getCurrentStatements()
    {
        return getCurrentQuestion().getStatements();
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
