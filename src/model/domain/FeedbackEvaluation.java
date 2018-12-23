package model.domain;

import javax.swing.*;

public class FeedbackEvaluation implements Evaluation{

	private String evaluationText = "";

	@Override
	public String getEvaluationText() {
		return this.evaluationText;
	}

	@Override
	public void setEvaluationText(String evaluationText) {
		this.evaluationText = evaluationText;
	}

	@Override
	public void addEvaluationText(String evaluationText) {
		if (this.evaluationText == null || this.evaluationText.isEmpty()) {
			this.evaluationText += evaluationText;
		} else {
			this.evaluationText += "\n" + evaluationText;
		}

	}

	@Override
	public void addCategory(String category, int maxScore) {

	}

	@Override
	public void questionOfCategoryCorrect(String category) {

	}

	@Override
	public int getScoreOfCategory(String category) {
		return 0;
	}

}
