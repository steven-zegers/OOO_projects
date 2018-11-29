package model.domain;

import javafx.collections.ObservableList;
import model.db.QuestionDB;

import java.util.List;

public class Test
{
    private ObservableList<Question> questions;

    private int questionPointer;


    public Test(Facade facade) {
        questions = facade.getQuestions();
        this.setQuestionPointer(0);
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
        if(questionPointer == questions.size())
        {
            questionPointer = -1;
        }
        else
        {
            questionPointer++;
        }
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


}
