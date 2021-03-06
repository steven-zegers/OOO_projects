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
import model.domain.Category;
import model.domain.Facade;
import model.domain.Observer;


public class CategoryOverviewPane extends GridPane implements Observer {
	private TableView table;
	private Button btnNew;
	private ObservableList<Category> data;

	private Facade facade;

	public CategoryOverviewPane(Facade facade) {
		setFacade(facade);
		data = facade.getCategories();
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("title"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().add(descriptionCol);
        table.setItems(data);
		this.add(table, 0, 1, 2, 6);
		
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

	public TableView getTable() {
		return this.table;
	}
	@Override
	public void update() {
		this.data = this.getFacade().getCategories();
		this.table.setItems(data);
	}
}
