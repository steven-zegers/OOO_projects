package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.CategoryDB;
import model.db.QuestionDB;

import java.util.ArrayList;
import java.util.List;

public class Facade implements Subject {
    private CategoryDB categoryDB;
    private QuestionDB questionDB;
    private List<Observer> observers;
    private Test test;

    public Facade() {
        this.categoryDB = new CategoryDB();
        this.questionDB = new QuestionDB();
        this.observers = new ArrayList<>();
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

    public void addCategory(Category category) {
    	this.getCategoryDB().addCategory(category);
    	this.notifyObservers();
    }

    public Category getCategory(String categoryTitle) {
    	return this.getCategoryDB().getCategory(categoryTitle);
    }

    public void addQuestion(Question question) {
    	this.getQuestionDB().addQuestion(question);
    	this.notifyObservers();
    }

    public void handleCorrectAnswer() {
        getCurrentTest().setScore(getCurrentTest().getScore() + 1);
    }

    public int getAmountOfQuestionsOfCategory(String categoryTitle) {
        return getCurrentTest().getAmountOfQuestionOfCategory(categoryTitle);
    }
    public String getCurrentQuestionCategoryTitle() {
        return getCurrentQuestion().getCategoryTitle();
    }

    public void handleQuestionOfCategoryCorrect(String categoryTitle) {
        getCurrentTest().questionOfCategoryCorrect(categoryTitle);
    }

    public void handleIncorrectAnswer(Question question) {
        getCurrentTest().handleIncorrectAnswer(question);
    }

    public String getFullFeedback() {
        return getCurrentTest().getFullFeedback();
    }

    public int getScoreOfCategory(String categoryTitle) {
        return getCurrentTest().getScoreOfCategory(categoryTitle);
    }

    public void setCurrentTestFinished() {
        getCurrentTest().setTestFinished();
        this.notifyObservers();
    }

    public Question getCurrentQuestion() {
        return getCurrentTest().getCurrentQuestion();
    }
    @Override
    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new DomainException("No observer given");
        }
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null) {
            throw new DomainException("No observer given");
        }
        if (!this.observers.contains(observer)) {
            throw new DomainException("Observer is not in list");
        }
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }

}