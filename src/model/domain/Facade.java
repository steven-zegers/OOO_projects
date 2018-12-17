package model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Facade implements Subject {

    private Database categoryDB;
    private Database questionDB;
    private List<Observer> observers;
    private Test test;
    private DbFactory dbFactory;
    private Properties properties;

    public Facade() {
        this.dbFactory = new DbFactory();
        this.categoryDB = dbFactory.getDatabase(DbType.CATEGORYDBTEXT); // change String to change db class used
        this.questionDB = dbFactory.getDatabase(DbType.QUESTIONDBTEXT); // change String to change db class used
        this.observers = new ArrayList<>();

        loadProperties();
    }

    //DB
    public Database<Category> getCategoryDB() {
        return categoryDB;
    }

    public Database<Question> getQuestionDB() {
        return questionDB;
    }

    public void deleteCategory(String categoryTitle) {
        getCategoryDB().deleteItem(categoryTitle);
        this.notifyObservers();
    }

    public void deleteQuestion(String questionTitle) {
        getQuestionDB().deleteItem(questionTitle);
        this.notifyObservers();
    }

    public void updateCategory(String oldTitle, Category newCategory) throws CloneNotSupportedException {
        for (Question question : getQuestions()) {
            if (question.getCategoryTitle().equals(oldTitle)) {
                Question questionCopy = new Question(question);
                questionCopy.setCategory(newCategory);
                updateQuestion(question.getTitle(), questionCopy);
            }
        }
        deleteCategory(oldTitle);
        addCategory(newCategory);
    }

    public void updateQuestion(String oldTitle, Question newQuestion) {
        deleteQuestion(oldTitle);
        addQuestion(newQuestion);
    }

    //Category
    public Category getCategory(String categoryTitle) {
        return this.getCategoryDB().getItem(categoryTitle);
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
        return getCategoryDB().getTitles();
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

    public void handleCorrectAnswer() {
        getCurrentTest().setScore(getCurrentTest().getScore() + 1);
    }

    public int getAmountOfQuestionsOfCategory(String categoryTitle) {
        return getCurrentTest().getAmountOfQuestionOfCategory(categoryTitle);
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

    /*
    public String getFeedbackType() {
        return properties.getFeedbackType();
    }
    */


    //Properties

    private void loadProperties()
    {
        this.properties = new Properties();
        InputStream input;

        try
        {
            input =  this.getClass().getResourceAsStream("evaluation.properties");
            //input = new FileInputStream("evaluation.properties");
            this.properties.load(input);

            input.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }

    }

    public String getFeedbackType()
    {
        return properties.getProperty("mode");
    }

    public boolean isFinishedBefore()
    {
        System.out.println("hier" + properties.getProperty("finished"));

        String finished = properties.getProperty("finished");

        if(finished.equals("true"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void updateProperties()
    {
        properties.setProperty("finished", "true");

        OutputStream out = null;

        try
        {
            out = new FileOutputStream("src/model/domain/evaluation.properties");

            properties.store(out, null);

            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String readOldScore() {
        String line;
        List<String> linesInFile = new ArrayList<>();
        String oldScore = "";
        try {
            //InputStream is = getClass().getResourceAsStream(this.path);
            //InputStreamReader fileReader = new InputStreamReader(is);
            FileReader fileReader = new FileReader(File.separator + "ZelfEvaluatieApp" + File.separator + "score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                linesInFile.add(line);
            }
            for (String score: linesInFile) {
                oldScore += score + "\n";
            }
            bufferedReader.close();
            fileReader.close();
            //is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldScore;
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