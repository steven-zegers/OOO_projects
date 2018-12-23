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
    private Evaluation evaluation;

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

    //Evaluation

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
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
        return this.test.getEvaluationText();
    }

    public String getScoreString() {
        return this.evaluation.getEvaluationText();
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
        /*
        We will store the properties on the home directory of the user of the program in a directory called
        ZelfEvaluatieApp. The first time the user executes the program we will have to make a copy of the standard
        property file (which will have mode=score and finished=false). Afterwards we will always update the local
        property file.
         */
        this.properties = new Properties();
        InputStream input;
        File localFile = new File(File.separator + "ZelfEvaluatieApp" + File.separator + "evaluation.properties");
        try
        {
            boolean succes = localFile.createNewFile();
            if (succes) {
                input =  this.getClass().getResourceAsStream("evaluation.properties");
                InputStreamReader fileReader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(localFile.getPath());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String line;
                int numberOfLine = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    if (numberOfLine != 0) {
                        bufferedWriter.newLine();
                    }
                    numberOfLine++;
                    bufferedWriter.write(line);
                }
                bufferedReader.close();
                fileReader.close();
                bufferedWriter.close();
                fileWriter.close();
            }
            input = new FileInputStream(File.separator + "ZelfEvaluatieApp" + File.separator + "evaluation.properties");


            //input = new FileInputStream("evaluation.properties");
            this.properties.load(input);

            input.close();
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }

    }

    public String getEvaluationType()
    {
        return properties.getProperty("mode");
    }

    public boolean isFinishedBefore()
    {
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

    public void setPropertyFinishedBefore()
    {
        properties.setProperty("finished", "true");
        saveProperty();
    }

    public List<String> getEvaluationTypes() {
        ArrayList<String> feedbackTypes = new ArrayList<>();
        feedbackTypes.add("Score");
        feedbackTypes.add("Feedback");
        return feedbackTypes;
    }

    public void updateEvaluationType(String evaluationType) {
        //TODO check if it is a valid evaluation type and update test
        properties.setProperty("mode", evaluationType);
        saveProperty();
    }

    public void saveProperty() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(File.separator + "ZelfEvaluatieApp" + File.separator + "evaluation.properties");
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