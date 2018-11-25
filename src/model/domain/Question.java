package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> statements = new ArrayList<>();
    private Category category;
    private String feedback;
    private String categoryTitle;

    public Question(String question, Category category, String feedback){
        setQuestion(question);
        setCategory(category);
        setFeedback(feedback);
        setCategoryTitle(category.getTitle());
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new DomainException("Question can't be empty");
        }
        this.question = question;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements) {
        if (statements == null || statements.isEmpty()) {
            throw new DomainException("Statements can't be empty");
        }
        this.statements = statements;
    }

    public void addStatement(String statement) {
        if (statement == null || statement.isEmpty()) {
            throw new DomainException("Statement can't be empty");
        }
        this.statements.add(statement);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new DomainException("Category can't be empty");
        }
        this.category = category;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        if (feedback == null || feedback.isEmpty()) {
            throw new DomainException("Feedback can't be empty");
        }
        this.feedback = feedback;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        if (categoryTitle == null || categoryTitle.isEmpty()) {
            throw new DomainException("CategoryTitle can't be empty");
        }
        this.categoryTitle = categoryTitle;
    }

}
