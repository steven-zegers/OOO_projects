package model.domain;

import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test
{
    private final HashMap<String, Integer> totalQuestionsOfEachCategory;
    private ObservableList<Question> questions;
    private Facade facade;

    private int questionPointer;
    private int score;
    private Map<String, Integer> scoresOfCategories;

    private String fullFeedback = "";
    private boolean isTestFinished = false;


    public Test(Facade facade) {
        questions = facade.getQuestions();
        this.facade = facade;
        List<String> titles = facade.getCategoryTitles();
        scoresOfCategories = new HashMap<>();
        for (String title : titles) {
            scoresOfCategories.put(title, 0);
        }
        totalQuestionsOfEachCategory = new HashMap<>();
        initializeQuestionAmountsPerCategory();
        this.setQuestionPointer(0);
        this.setScore(0);
    }

    public int getAmountOfQuestionOfCategory(String categoryTitle) {
        return totalQuestionsOfEachCategory.get(categoryTitle);
    }

    private void initializeQuestionAmountsPerCategory() {
        for (String categoryTitle : facade.getCategoryTitles()) {
            totalQuestionsOfEachCategory.put(categoryTitle, 0);
        }
        for (Question question : facade.getQuestions()) {
            if (totalQuestionsOfEachCategory.containsKey(question.getCategoryTitle())) {
                totalQuestionsOfEachCategory.put(question.getCategoryTitle(), totalQuestionsOfEachCategory.get(question.getCategoryTitle()) + 1);
            } else {
                totalQuestionsOfEachCategory.put(question.getCategoryTitle(), 1);
            }
        }
    }

	public void questionOfCategoryCorrect(String categoryTitle) {
		scoresOfCategories.put(categoryTitle, scoresOfCategories.get(categoryTitle) + 1);
	}

	public void handleIncorrectAnswer(Question question) {
    	fullFeedback += question.getFeedback() + "\n";
	}

    public String getFullFeedback() {
        return fullFeedback;
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

    public int getQuestionPointer() {
        return this.questionPointer;
    }

    public boolean canAdvance() {
        return questionPointer + 1 < questions.size();
    }
    /**
     * Returns whether the test is finished or not.
     */

    public boolean isFinished()
    {
        return isTestFinished;
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

    public void setTestFinished() {
        this.isTestFinished = true;
    }
}
