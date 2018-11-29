package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.CategoryDB;
import model.db.QuestionDB;

import java.util.ArrayList;
import java.util.List;

public class Facade {
    private CategoryDB categoryDB;
    private QuestionDB questionDB;
    private Test test;

    public Facade() {
        this.categoryDB = new CategoryDB();
        this.questionDB = new QuestionDB();
    }

    public CategoryDB getCategoryDB() {
        return categoryDB;
    }

    public QuestionDB getQuestionDB() {
        return questionDB;
    }

    public ObservableList<Question> getQuestions() {
        return FXCollections.observableArrayList(getQuestionDB().getQuestions());
    }

    public ObservableList<Category> getCategories() {
        return FXCollections.observableArrayList(getCategoryDB().getCategories());
    }

    public List<String> getCategoryTitles() {
        List<String> titles = new ArrayList<>();
        for (Category category : getCategoryDB().getCategories()) {
            titles.add(category.getTitle());
        }
        return titles;
    }

    public void setCurrentTest(Test test){
        this.test = test;
    }

    public Test getCurrentTest() {
        return this.test;
    }

    public String getTitleOfCurrentQuestionOfCurrentTest() {
        return getCurrentTest().getCurrentQuestion().getQuestion();
    }

    public void advanceCurrentTest() {
        getCurrentTest().advanceTest();
    }

    public List<String> getStatementsOfCurrentQuestion() {
        return getCurrentTest().getCurrentQuestion().getStatements();
    }
}
