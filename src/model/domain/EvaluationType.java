package model.domain;

public enum EvaluationType {

	FEEDBACK("Feedback"),
	SCORE("Score");

	public String getType() {
		return this.type;
	}

	private String type;
	EvaluationType(String type) {
		this.type = type;
	}
}
