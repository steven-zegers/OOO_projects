package model.domain;

import java.util.HashMap;
import java.util.Map;

public class ScoreEvaluation implements Evaluation {

	private Map<String, Integer> scoresOfCategories = new HashMap<>();
	private Map<String, Integer> maxScoresOfCategories = new HashMap<>();
	private String evaluationText;
	private int totaleScore;
	private int maxScore = 0;

	@Override
	public String getEvaluationText() {
		String scoreString = "";
		scoreString += "Your score: " + this.totaleScore + "/" + this.maxScore + "\n";
		for (String category : scoresOfCategories.keySet()) {
			scoreString += "Category " + category + ": " + Integer.toString(scoresOfCategories.get(category)) + "/" + maxScoresOfCategories.get(category) + "\n";
		}
		return scoreString;
	}

	@Override
	public void setEvaluationText(String evaluationText) {
		this.evaluationText = evaluationText;
	}

	@Override
	public void addEvaluationText(String evaluationText) {
		this.evaluationText += "\n" + evaluationText;
	}

	@Override
	public void addCategory(String category, int maxScore) {
		this.maxScore += maxScore;
		maxScoresOfCategories.put(category, maxScore);
		scoresOfCategories.put(category, 0);
	}

	@Override
	public void questionOfCategoryCorrect(String category) {
		this.totaleScore += 1;
		scoresOfCategories.put(category, scoresOfCategories.get(category) + 1);
	}

	@Override
	public int getScoreOfCategory(String category) {
		return scoresOfCategories.get(category);
	}

}
