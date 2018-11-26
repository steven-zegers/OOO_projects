package model.db;

import model.domain.Category;
import model.domain.Question;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * QuestionDB contains a List of Questions that is used in tests while the program is running.
 * It is created using a QuestionDbBuilder to load in all existing Questions from a text file.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class QuestionDB {

    /**
     * A List of Questions to be used in tests while running the program.
     */

    private List<Question> questions;

    /**
     * The path where the file containing the Questions is located.
     */

    private String path = "src/model/db/vraag.txt";

    /**
     * Creates a new QuestionDB via the Builder.
     * No parameters are to be given, since the path is statically determined.
     */

    public QuestionDB() {
        QuestionDbBuilder builder = new QuestionDbBuilder(path);
        this.questions = builder.readQuestions();
        //testQuestions();
    }

    /**
     * Prints an easily readable version of each existing Question object contained within the List.
     */

    private void testQuestions() {
        for (Question question : questions) {
            System.out.print("Category: " + question.getCategory().getTitle() + ", question: " + question.getQuestion() + "statements: " );
            for (String statement : question.getStatements()) {
                System.out.print(statement + ", ");
            }
            System.out.print(", feedback: " + question.getFeedback());
        }
    }

    /**
     * Adds a new Question to this List and writes it to the text file use at a later date.
     * @param question
     * The Question that should be stored.
     */

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

    /**
     * Returns a List of Question-objects stored in this database.
     * @return
     * questions as a List of Questions
     */

    public List<Question> getQuestions() {
        return questions;
    }


}
