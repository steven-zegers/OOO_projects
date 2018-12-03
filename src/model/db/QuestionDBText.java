package model.db;

import model.domain.Category;
import model.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QuestionDB contains a List of Questions that is used in tests while the program is running.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class QuestionDBText implements Database<Question> {

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

	public QuestionDBText() {
		this.questions = readItems(readFile());
		//testQuestions();
	}

	public String getPath() {
		return this.path;
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
			System.out.print("feedback: " + question.getFeedback() + "\n");
		}
	}

	@Override
	public List<String[]> readFile() {
		String line;
		List<String[]> linesInFile = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(this.getPath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] lineString = line.split(": ");
				linesInFile.add(lineString);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linesInFile;
	}

	@Override
	public List<String> getTitles() {
		List<String> out = new ArrayList<>();
		for (Question question : this.questions) {
			out.add(question.getQuestion());
		}
		return out;
	}

	@Override
	public List<Question> readItems(List<String[]> text) {
		CategoryDBText categoryDB = new CategoryDBText();
		List<Question> questions = new ArrayList<>();
		for(String[] line : text) {
			Category questionCategory = categoryDB.getItem(line[0]);
			String question = line[1];
			String feedback = line[2];
			Question newQuestion = new Question(question, questionCategory, feedback);
			String[] statements = line[3].split("; ");
			for (String statement: statements) {
				newQuestion.addStatement(statement);
			}
			questions.add(newQuestion);
		}
		return questions;
	}

	/**
	 * Adds a new Question to this List and writes it to the text file use at a later date.
	 * @param question
	 * The Question that should be stored.
	 */
	@Override
	public void addItem(Question question) {
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

	public List<Question> getItems() {
		return questions;
	}

	@Override
	public Question getItem(String item) {
		return null;
	}


}
