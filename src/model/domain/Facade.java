package model.domain;

import model.db.CategoryDB;
import model.db.QuestionDB;

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

}
