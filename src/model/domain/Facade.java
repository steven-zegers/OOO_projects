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