package model.db;

import model.domain.Category;
import model.domain.Question;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class QuestionDB {
    private List<Question> questions;
    private String path = "src/model/db/vraag.txt";

    public QuestionDB() {
        QuestionDbBuilder builder = new QuestionDbBuilder(path);
        this.questions = builder.readQuestions();
        //testQuestions();
    }

    private void testQuestions() {
        for (Question question : questions) {
            System.out.print("Category: " + question.getCategory().getTitle() + ", question: " + question.getQuestion() + "statements: " );
            for (String statement : question.getStatements()) {
                System.out.print(statement + ", ");
            }
            System.out.print(", feedback: " + question.getFeedback());
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
        try {
            String question1 = question.getQuestion();
            String categoryTitle = question.getCategory().getTitle();
            String feedback = question.getFeedback();
            List<String> statements = question.getStatements();
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            String string = categoryTitle + ": " + question1 + ": " + feedback + ": ";
            for (String statement : statements) {
                string += statement + "; ";
            }
            string = string.substring(0, string.length()-2);
            bufferedWriter.write(string);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }


}
