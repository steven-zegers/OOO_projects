package model.domain;

public interface Evaluation {

	String getEvaluationText();
	void setEvaluationText(String evaluationText);
	void addEvaluationText(String evaluationText);
	void addCategory(String category, int maxScore);
	void questionOfCategoryCorrect(String category);
	int getScoreOfCategory(String category);

}
