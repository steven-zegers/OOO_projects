package model.domain;

import javafx.collections.ObservableList;

import java.io.*;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class Test
{
    private ObservableList<Question> questions;
    private Facade facade;

    private int questionPointer;
    private int score;

    private Evaluation evaluation;

    private boolean isTestFinished = false;
    private Map<String, Integer> totalQuestionsOfEachCategory = new HashMap<>();;


    public Test(Facade facade) {
        questions = facade.getQuestions();
        this.facade = facade;
        switch (this.facade.getEvaluationType()){
            case "score":
                this.setEvaluation(new ScoreEvaluation());
                break;
            case "feedback":
                this.setEvaluation(new FeedbackEvaluation());
                break;
            default:
                throw new DomainException("Evaluation type is not recognized");
        }
        facade.setEvaluation(this.evaluation);
        initializeQuestionAmountsPerCategory();
        initializeScoresOfCategories();
        this.setQuestionPointer(0);
        this.setScore(0);
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void initializeQuestionAmountsPerCategory() {
        for(String categoryTitle : facade.getCategoryTitles()) {
            totalQuestionsOfEachCategory.put(categoryTitle, 0);
        }
        for (Question question : questions) {
            if (totalQuestionsOfEachCategory.containsKey(question.getCategoryTitle())) {
                totalQuestionsOfEachCategory.put(question.getCategoryTitle(), totalQuestionsOfEachCategory.get(question.getCategoryTitle()) + 1);
            } else {
                totalQuestionsOfEachCategory.put(question.getCategoryTitle(), 1);
            }
        }
    }

    public int getAmountOfQuestionOfCategory(String categoryTitle) {
        return totalQuestionsOfEachCategory.get(categoryTitle);
    }

    public void initializeScoresOfCategories() {
        List<String> titles = facade.getCategoryTitles();
        for (String title : titles) {
            this.evaluation.addCategory(title, totalQuestionsOfEachCategory.get(title));
        }
    }

	public void questionOfCategoryCorrect(String categoryTitle) {
        this.evaluation.questionOfCategoryCorrect(categoryTitle);
	}

	public void handleIncorrectAnswer(Question question) {
        this.evaluation.addEvaluationText(question.getFeedback());
	}

    public String getEvaluationText() {
        return evaluation.getEvaluationText();
    }

    public int getScoreOfCategory(String categoryTitle) {
        return evaluation.getScoreOfCategory(categoryTitle);
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
        writeScore();
        this.isTestFinished = true;
        facade.setPropertyFinishedBefore();
    }

    public void writeScore() {
        File localFile = new File(File.separator + "ZelfEvaluatieApp" + File.separator + "score.txt");
        try {
            boolean created = localFile.createNewFile();
            if (!created) {
                PrintWriter pw = new PrintWriter(localFile.getPath());
                pw.close();
            }

            String[] lines = facade.getScoreString().split("\n");
            FileWriter fileWriter = new FileWriter(localFile.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int number = 0;
            for (String line : lines) {
                if (number != 0) {
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(line);
                number++;
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
