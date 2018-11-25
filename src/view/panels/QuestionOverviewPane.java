package view.panels;

import controller.QuestionOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.db.QuestionDB;
import model.domain.Category;
import model.domain.Question;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private ObservableList<Question> data;
	private QuestionOverviewController questionOverviewController;
	
	public QuestionOverviewPane(QuestionOverviewController controller) {
		setQuestionOverviewController(controller);
		 data = this.getQuestionOverviewController().getQuestions();

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Question");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("categoryTitle"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		table.setItems(data);
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}

	public QuestionOverviewController getQuestionOverviewController() {
		return questionOverviewController;
	}

	public void setQuestionOverviewController(QuestionOverviewController questionOverviewController) {
		this.questionOverviewController = questionOverviewController;
	}
}
