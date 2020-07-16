package ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Controller {

	@FXML
	private BorderPane borderPane;

	@FXML
	private Label rows;

	@FXML
	private Label columns;

	@FXML
	private GridPane grid;

	private int columnSize;
	private int rowSize;

	public Controller() {

	}

	@FXML
	public void initialize() {

		columnSize = grid.getColumnConstraints().size();
		rowSize = grid.getRowConstraints().size();

		columns.setText(columnSize + "");
		rows.setText(rowSize + "");

	}

	@FXML
	void addColumn(ActionEvent event) {

		for (int i = 0; i < rowSize; i++) {
			grid.add(new TextField(), columnSize, i);
		}

		columnSize++;
		columns.setText(columnSize + "");
	}

	@FXML
	void deleteColumn(ActionEvent event) {

		if (columnSize > 0) {

			ObservableList<Node> nodes = grid.getChildren();
			ArrayList<Node> nodesToRemove = new ArrayList<Node>();

			for (int i = 0; i < nodes.size(); i++) {

				Node n = nodes.get(i);

				if (columnSize > 1) {
					if (GridPane.getColumnIndex(n) != null && GridPane.getColumnIndex(n) == columnSize - 1) {
						nodesToRemove.add(n);
					}
				} else {
					if (GridPane.getColumnIndex(n) == null || GridPane.getColumnIndex(n) == columnSize - 1) {
						nodesToRemove.add(n);
					}
				}
			}

			grid.getChildren().removeAll(nodesToRemove);

			columnSize--;
			columns.setText(columnSize + "");

		}

	}

	@FXML
	void addRow(ActionEvent event) {

		for (int i = 0; i < columnSize; i++) {
			grid.add(new TextField(), i, rowSize);
		}

		rowSize++;
		rows.setText(rowSize + "");
	}

	@FXML
	void calculate(ActionEvent event) {
		Clipboard cb = Clipboard.getSystemClipboard();
		System.out.println(cb.getString());

		ObservableList<Node> nodes = grid.getChildren();

		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			System.out.println("Row: " + GridPane.getRowIndex(n) + " COL: " + GridPane.getColumnIndex(n) + " Txt: "
					+ ((TextField) n).getText());
		}

	}

	@FXML
	void deleteRow(ActionEvent event) {

		if (rowSize > 0) {

			ObservableList<Node> nodes = grid.getChildren();
			ArrayList<Node> nodesToRemove = new ArrayList<Node>();

			for (int i = 0; i < nodes.size(); i++) {

				Node n = nodes.get(i);

				if (rowSize > 1) {
					if (GridPane.getRowIndex(n) != null && GridPane.getRowIndex(n) == rowSize - 1) {
						nodesToRemove.add(n);
					}
				} else {
					if (GridPane.getRowIndex(n) == null || GridPane.getRowIndex(n) == rowSize - 1) {
						nodesToRemove.add(n);
					}
				}
			}

			grid.getChildren().removeAll(nodesToRemove);

			rowSize--;
			rows.setText(rowSize + "");
		}

	}

}
