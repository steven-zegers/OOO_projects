package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> statements = new ArrayList<>();
    private Category category;
    private String feedback;

    public Question(String question, Category category, String feedback){
        setQuestion(question);
        setCategory(category);
        setFeedback(feedback);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements) {
        this.statements = statements;
    }

    public void addStatement(String statement) {
        this.statements.add(statement);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
