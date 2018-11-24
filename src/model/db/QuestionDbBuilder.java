package model.db;

import model.domain.Category;
import model.domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDbBuilder {
    private String path;

    public QuestionDbBuilder(String path) {
        setPath(path);
    }

    public List<Question> readQuestions() {
        List<Question> questions = new ArrayList<>();
        String line;
        try {
            CategoryDB categoryDB = new CategoryDB();
            FileReader fileReader = new FileReader(this.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
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

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
