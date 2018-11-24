package model.db;

import model.domain.Question;

import java.util.List;

public class QuestionDB {
    List<Question> questions;

    public QuestionDB() {
        QuestionDbBuilder builder = new QuestionDbBuilder("src/model/db/vraag.txt");
        this.questions = builder.readQuestions();
        //testQuestions();
    }

    private void testQuestions() {
        for (Question question : questions) {
            System.out.print("Category: " + question.getCategory() + ", question: " + question.getQuestion() + "statements: " );
            for (String statement : question.getStatements()) {
                System.out.print(statement + ", ");
            }
            System.out.print(", feedback: " + question.getFeedback());
        }
    }
}
