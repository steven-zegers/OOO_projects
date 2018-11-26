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
}
