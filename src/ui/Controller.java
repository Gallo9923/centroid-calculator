package ui;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Arrays;

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
	private void addColumn(ActionEvent event) {

		for (int i = 0; i < rowSize; i++) {
			grid.add(new TextField(), columnSize, i);
		}

		columnSize++;
		columns.setText(columnSize + "");
	}

	@FXML
	private void deleteColumn(ActionEvent event) {

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
	private void addRow(ActionEvent event) {

		for (int i = 0; i < columnSize; i++) {
			grid.add(new TextField(), i, rowSize);
		}

		rowSize++;
		rows.setText(rowSize + "");
	}

	@FXML
	private void calculate(ActionEvent event) {
		

	}

	@FXML
	private void paste(ActionEvent event) {
		
		Clipboard cb = Clipboard.getSystemClipboard();

		try {
		
			String[] rowLines = cb.getString().split("\n");
			
			int[][] matrix = new int[rowLines.length][rowLines[0].split("	").length];
			
			for(int i=0; i<matrix.length; i++) {
				String[] row = rowLines[i].split("	");
				for(int j=0; j<matrix[0].length; j++) {
					matrix[i][j] = Integer.parseInt(row[j].trim());
				}
			}
			
			resizeGridPane(matrix.length, matrix[0].length);
			insertValues(matrix);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	private void insertValues(int[][] matrix) {
		
		ObservableList<Node> nodes = grid.getChildren();
		
		for(Node node : nodes) {
			
			Integer rowIndex = GridPane.getRowIndex(node);
			Integer columnIndex = GridPane.getColumnIndex(node);
			
			if(rowIndex == null) {
				
				if(columnIndex == null) {
					((TextField)node).setText(matrix[0][0] + "");
				}else {
					((TextField)node).setText(matrix[0][columnIndex] + "");
				}
			}else {
				if(columnIndex == null) {
					((TextField)node).setText(matrix[rowIndex][0] + "");
				}else {
					((TextField)node).setText(matrix[rowIndex][columnIndex] + "");
				}
			}
			
			
		}
		
		
	}
	
	private void resizeGridPane(int rows, int columns) {
		
		grid.getChildren().clear();
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				grid.add(new TextField(), j, i);
			}
		}

		this.rows.setText(rows + "");
		this.columns.setText(columns + "");
		
	}
	
	@FXML
	private void deleteRow(ActionEvent event) {

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
