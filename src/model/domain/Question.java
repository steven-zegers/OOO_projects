package model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Question are the objects of which tests consist. Questions are to be answered by the user of the tests, after which feedback will be given.
 * They can be open questions, or be multiple choice, containing statements the user can choose from.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class Question {

    /**
     * The question to be asked to the user.
     */

    private String question;

    /**
     * A list of statements of which the user can select an answer.
     */

    private List<String> statements = new ArrayList<>();

    /**
     * The Category to which this Question belongs.
     */

    private Category category;

    /**
     * The feedback the user receives after answering the Question.
     */

    private String feedback;

    /**
     * The title of this Question's Category.
     */

    private String categoryTitle;


    /**
     * Creates a new Question with it's question, category and feedback given. It will be an open Question, unless statements are added afterwards.
     * @param question
     * The question as a String
     * @param category
     * The Category of this Question
     * @param feedback
     * The feedback as a String
     */

    public Question(String question, Category category, String feedback){
        setQuestion(question);
        setCategory(category);
        setFeedback(feedback);
        setCategoryTitle(category.getTitle());
    }

    /**
     * Returns the question.
     * @return
     * question as a String
     */

    public String getQuestion() {
        return question;
    }

    /**
     * Sets this Question's question.
     * @param question
     * question as a String
     */

    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new DomainException("Question can't be empty");
        }
        this.question = question;
    }

    /**
     * Returns a List of all statements belonging to this Question.
     * @return
     * statements as a List of Strings
     */

    public List<String> getStatements() {
        return statements;
    }

    /**
     * Sets this Question's statements to an existing List of statements.
     * @param statements
     * statements as a List of Strings
     */

    public void setStatements(List<String> statements) {
        if (statements == null || statements.isEmpty()) {
            throw new DomainException("Statements can't be empty");
        }
        this.statements = statements;
    }

    /**
     * Adds a statement to this Question. They will be added on the first available spot in the List
     * @param statement
     * The statement as a String
     */

    public void addStatement(String statement) {
        if (statement == null || statement.isEmpty()) {
            throw new DomainException("Statement can't be empty");
        }
        this.statements.add(statement);
    }

    /**
     * Returns this Question's category.
     * @return
     * category as a Category
     */

    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of this Question.
     * @param category
     * category as a Category
     */

    public void setCategory(Category category) {
        if (category == null) {
            throw new DomainException("Category can't be empty");
        }
        this.category = category;
    }

    /**
     * Returns the feedback of this Question.
     * @return
     * feedback as a String
     */

    public String getFeedback() {
        return feedback;
    }

    /**
     * Sets the feedback of this Question.
     * @param feedback
     * feedback as a String
     */

    public void setFeedback(String feedback) {
        if (feedback == null || feedback.isEmpty()) {
            throw new DomainException("Feedback can't be empty");
        }
        this.feedback = feedback;
    }

    /**
     * Returns the title of the Category assigned to this Question.
     * @return
     * category's title as a String
     */

    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * Sets the title of this Question's category.
     * @param categoryTitle
     * The String categoryTitle should be set to
     */

    public void setCategoryTitle(String categoryTitle) {
        if (categoryTitle == null || categoryTitle.isEmpty()) {
            throw new DomainException("CategoryTitle can't be empty");
        }
        this.categoryTitle = categoryTitle;
    }

    public String getTitle() {
        return this.question;
    }

    @Override
    public String toString() {
        String string = getCategoryTitle() + ": " + getTitle() + ": " + getFeedback() + ": ";
        for (String statement : getStatements()) {
            string += statement + "; ";
        }
        string = string.substring(0, string.length()-2);
        return string;
    }
}
