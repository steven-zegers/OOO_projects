package model.db;

import model.domain.Category;
import model.domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QuestionDbBuilder is a tool for creating Questions from information stored within a given text file.
 * The file is read and Question-objects are created and stored in a List for use within the program.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class QuestionDbBuilder {

    /**
     * The path to the file containing the Questions.
     */

    private String path;

    /**
     * Creates a new Builder using the given String as a path to the text file to be read.
     * @param path
     * The path to be used as a String
     */

    public QuestionDbBuilder(String path) {
        setPath(path);
    }

    /**
     * Reads in all Questions from the specified text file and turns them into Question-objects. Afterwards a List containing the new objects is returned.
     * @return
     * A List of Question-objects
     */

    public List<Question> readQuestions() {
        List<Question> questions = new ArrayList<>();
        String line;
        try {
            CategoryDB categoryDB = new CategoryDB();
            FileReader fileReader = new FileReader(this.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] lineString = line.split(": ");
                Category questionCategory = categoryDB.getCategory(lineString[0]);
                String question = lineString[1];
                String feedback = lineString[2];
                Question question1 = new Question(question, questionCategory, feedback);
                String[] statements = lineString[3].split("; ");
                for (String statement : statements) {
                    question1.addStatement(statement);
                }
                questions.add(question1);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Returns the path to the text file containing the Questions.
     * @return
     * path as a String
     */

    public String getPath() {
        return path;
    }

    /**
     * Sets the path to be used.
     * @param path
     * Path to be used as a String
     */

    public void setPath(String path) {
        this.path = path;
    }
}
