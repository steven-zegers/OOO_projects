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

public class QuestionDBText extends TextDatabase<Question> {

	private static QuestionDBText uniqueInstance = new QuestionDBText();

	/**
	 * A List of Questions to be used in tests while running the program.
	 */

	//private List<Question> questions;

	/**
	 * Creates a new QuestionDB via the Builder.
	 * No parameters are to be given, since the path is statically determined.
	 */

	private QuestionDBText() {
		super("vraag.txt");
		//this.questions = readItems(readFile());
		this.uniqueInstance = this;
		//testQuestions();
	}

	/**
	 * Prints an easily readable version of each existing Question object contained within the List.
	 */

	private void testQuestions() {
		for (Question question : this.getItems()) {
			System.out.print("Category: " + question.getCategory().getTitle() + ", question: " + question.getQuestion() + "statements: " );
			for (String statement : question.getStatements()) {
				System.out.print(statement + ", ");
			}
			System.out.print("feedback: " + question.getFeedback() + "\n");
		}
	}

	@Override
	public List<String> getTitles() {
		List<String> out = new ArrayList<>();
		for (Question question : this.getItems()) {
			out.add(question.getQuestion());
		}
		return out;
	}

	@Override
	public List<Question> readItems(List<String[]> text) {
		CategoryDBText categoryDB = CategoryDBText.getInstance();
		List<Question> questions = new ArrayList<>();
		for(String[] line : text) {
			System.out.println(line[1]);
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

	@Override
	public Question getItem(String title) {
		for (Question question : this.getItems()) {
			if (question.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return question;
			}
		}
		throw new DbException("This title is not recognized as a saved question");
	}

	@Override
	protected boolean titleAlreadyExists(Question item) {
		return this.getTitles().contains(item.getTitle());
	}

	public static QuestionDBText getInstance() {
		return uniqueInstance;
	}

}
