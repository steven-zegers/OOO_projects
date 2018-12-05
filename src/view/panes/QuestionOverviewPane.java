package view.panes;

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
import model.domain.Facade;
import model.domain.Observer;
import model.domain.Question;

import javax.swing.*;

public class QuestionOverviewPane extends GridPane implements Observer {

	private TableView table;
	private Button btnNew;
	private ObservableList<Question> data;
	private Facade facade;

	public QuestionOverviewPane(Facade facade) {
		this.setFacade(facade);

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
		this.update();
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}

    public void setEditAction(EventHandler<MouseEvent> editAction) {
        table.setOnMouseClicked(editAction);
    }

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	public ObservableList<Question> getData() {
		return data;
	}

	public void setData(ObservableList<Question> data) {
		this.data = data;
	}

	@Override
	public void update() {
		this.data = this.getFacade().getQuestions();
		this.table.setItems(data);
	}

    public TableView getTable() {
	    return this.table;
    }
}
