package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.CategoryDB;
import model.db.Database;
import model.db.QuestionDB;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Facade implements Subject {
    private Database<Category> categoryDB;
    private QuestionDB questionDB;
    private List<Observer> observers;
    private Test test;

    public Facade() {
        this.categoryDB = new CategoryDB();
        this.questionDB = new QuestionDB();
        this.observers = new ArrayList<>();
    }


    //DB
    private Database<Category> getCategoryDB() {
        return categoryDB;
    }

    private Database<Question> getQuestionDB() {
        return questionDB;
    }


    //Category
    public Category getCategory(String categoryTitle) {
        return ((CategoryDB)getCategoryDB()).getCategory(categoryTitle);
    }

    public void addCategory(Category category) {
    	this.getCategoryDB().addItem(category);
    	this.getCurrentTest().initializeScoresOfCategories();
    	this.getCurrentTest().initializeQuestionAmountsPerCategory();
    	this.notifyObservers();
    }

    public ObservableList<Category> getCategories() {
        return FXCollections.observableArrayList(getCategoryDB().getItems());
    }

    public List<String> getCategoryTitles() {
        return ((CategoryDB) getCategoryDB()).getCategoryTitles();
    }


    //Question
    public ObservableList<Question> getQuestions() {
        return FXCollections.observableArrayList(getQuestionDB().getItems());
    }

    public void addQuestion(Question question) {
    	this.getQuestionDB().addItem(question);
    	this.getCurrentTest().initializeQuestionAmountsPerCategory();
    	this.notifyObservers();
    }

    public Question getCurrentQuestion() {
        return getCurrentTest().getCurrentQuestion();
    }



    //Test
    public void setCurrentTest(Test test){
        this.test = test;
    }

    public Test getCurrentTest() {
        return this.test;
    }


    public String getTitleOfCurrentQuestionOfCurrentTest() {
        return getCurrentTest().getCurrentQuestion().getQuestion();
    }

    public String getCurrentQuestionCategoryTitle() {
        return getCurrentQuestion().getCategoryTitle();
    }

    public List<String> getStatementsOfCurrentQuestion() {
        return getCurrentTest().getCurrentQuestion().getStatements();
    }

    public int getAmountOfQuestionsOfCategory(String categoryTitle) {
        return getCurrentTest().getAmountOfQuestionOfCategory(categoryTitle);
    }


    public void handleCorrectAnswer() {
        getCurrentTest().setScore(getCurrentTest().getScore() + 1);
    }

    public void advanceCurrentTest() {
        getCurrentTest().advanceTest();
    }

    public void handleIncorrectAnswer(Question question) {
        getCurrentTest().handleIncorrectAnswer(question);
    }


    public String getFullFeedback() {
        return getCurrentTest().getFullFeedback();
    }

    public String getScoreString() {
        String scoreString = "";
        String totaleScore = Integer.toString(getCurrentTest().getScore());
        scoreString += "Your score: " + totaleScore + "/" + getQuestions().size() + "\n";
        for (String categoryTitle : getCategoryTitles()) {
            scoreString += "Category " + categoryTitle + ": " + Integer.toString(getScoreOfCategory(categoryTitle)) + "/" + getAmountOfQuestionsOfCategory(categoryTitle) + "\n";
        }
        return scoreString;
    }

    public boolean isItAPerfectTest() {
        return getCurrentTest().getScore() == getQuestions().size();
    }

    public void setCurrentTestFinished() {
        getCurrentTest().setTestFinished();
        this.notifyObservers();
    }


    public void handleQuestionOfCategoryCorrect(String categoryTitle) {
        getCurrentTest().questionOfCategoryCorrect(categoryTitle);
    }

    public int getScoreOfCategory(String categoryTitle) {
        return getCurrentTest().getScoreOfCategory(categoryTitle);
    }

    public String getFeedbackType() {
        return getCurrentTest().getFeedbackType();
    }
    //Observer
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