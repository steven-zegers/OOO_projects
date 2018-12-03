package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.CategoryDBText;
import model.db.QuestionDBText;

import java.util.ArrayList;
import java.util.List;

public class Facade implements Subject {
    private CategoryDBText categoryDBText;
    private QuestionDBText questionDBText;
    private List<Observer> observers;
    private Test test;

    public Facade() {
        this.categoryDBText = new CategoryDBText();
        this.questionDBText = new QuestionDBText();
        this.observers = new ArrayList<>();
    }

    public CategoryDBText getCategoryDBText() {
        return categoryDBText;
    }

    public QuestionDBText getQuestionDBText() {
        return questionDBText;
    }

    public ObservableList<Question> getQuestions() {
        return FXCollections.observableArrayList(getQuestionDBText().getItems());
    }

    public ObservableList<Category> getCategories() {
        return FXCollections.observableArrayList(getCategoryDBText().getItems());
    }

    public List<String> getCategoryTitles() {
        return getCategoryDBText().getCategoryTitles();
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
    	this.getCategoryDBText().addItem(category);
    	this.getCurrentTest().initializeScoresOfCategories();
    	this.getCurrentTest().initializeQuestionAmountsPerCategory();
    	this.notifyObservers();
    }

    public Category getCategory(String categoryTitle) {
    	return this.getCategoryDBText().getCategory(categoryTitle);
    }

    public void addQuestion(Question question) {
    	this.getQuestionDBText().addItem(question);
    	this.getCurrentTest().initializeQuestionAmountsPerCategory();
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