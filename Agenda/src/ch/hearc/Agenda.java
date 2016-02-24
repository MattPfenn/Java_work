package ch.hearc;

/*
 * Exercice:
 * 
 * 1) Utiliser un Map pour insérer / chercher des personnes
 * 
 * 2) Enregistrer le Map en disque lors de la sortie du programme
 * 
 * 3) Lire le Map du disque lors de l'entrée du programme
 * 
 * 4) Concrétiser la recherche de noms partiels
 * 
 * 		Si le nom donné n'est pas trouvé (complet), le programme
 *      doit ouvrir une combo box avec tous les noms qui contiennent
 *      le texte donné comme nom. L'utilisateur choisit la personne
 *      qu'il veut, qui est alors affichée.
 *      
 *      En deuxième étape, si une seule personne a un nom qui
 *      contient le texte donné, ne pas proposer de combo box,
 *      et choisir la personne en question directement.
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Agenda extends Application {
	private TextField name = new TextField();
	private TextField address = new TextField();
	private TextField postalCode = new TextField();
	private TextField city = new TextField();
	private TextField phone = new TextField();
	private Button leftButton = new Button();
	private Button rightButton = new Button();
	private Map<String, AgendaPerson> database = new HashMap<String, AgendaPerson>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Name:"), 0, 0);
		gridPane.add(name, 1, 0);
		gridPane.add(new Label("Address"), 0, 1);
		gridPane.add(address, 1, 1);
		gridPane.add(new Label("Postal Code:"), 0, 2);
		gridPane.add(postalCode, 1, 2);
		gridPane.add(new Label("City:"), 0, 3);
		gridPane.add(city, 1, 3);
		gridPane.add(new Label("Phone:"), 0, 4);
		gridPane.add(phone, 1, 4);
		gridPane.add(leftButton, 0, 5);
		gridPane.add(rightButton, 1, 5);

		gridPane.setAlignment(Pos.CENTER);
		name.setAlignment(Pos.BOTTOM_LEFT);
		address.setAlignment(Pos.BOTTOM_LEFT);
		postalCode.setAlignment(Pos.BOTTOM_LEFT);
		city.setAlignment(Pos.BOTTOM_LEFT);
		phone.setAlignment(Pos.BOTTOM_LEFT);

		clickClear();

		GridPane.setHalignment(leftButton, HPos.RIGHT);

		Scene scene = new Scene(gridPane, 400, 250);
		primaryStage.setTitle("Agenda");
		primaryStage.setScene(scene);
		primaryStage.show();

		File file = new File ("/tmp/database");
		try (Scanner scan = new Scanner(file)) {
			AgendaPerson person = null;
			while (scan.hasNext()) {
				person = AgendaPerson.scan(scan);
				if (person != null)
					database.put(person.getName(), person);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Cannot open database");
		}

	}

	@Override
	public void stop() {
		File file = new File ("/tmp/database");
		try (PrintStream ps = new PrintStream(file)) {
			for (AgendaPerson person : database.values()) {
				person.write(ps);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Cannot open database");
		}
	}

	private void setFieldsVisible(boolean vi)
	{
		address.setVisible(vi);
		postalCode.setVisible(vi);
		city.setVisible(vi);
		phone.setVisible(vi);

	}

	private void setFieldsEditable(boolean ed)
	{
		address.setEditable(ed);
		postalCode.setEditable(ed);
		city.setEditable(ed);
		phone.setEditable(ed);

	}

	private void clickSearch() {
		// do not search if name is empty
		if (name.getText().equals("")) {
			name.requestFocus();
			return;
		}
		// TODO: search here
		AgendaPerson person = database.get(name.getText());
		if (person != null) {
			name.setEditable(false);
			address.setText(person.getAddress());
			postalCode.setText(person.getPostalCode());
			city.setText(person.getCity());
			phone.setText(person.getPhone());
			setFieldsEditable(false);
			leftButton.setOnAction(e -> clickClear());
			leftButton.setVisible(true);
			leftButton.setText("Clear");
			leftButton.requestFocus();
			rightButton.setOnAction(e -> clickEdit());
			rightButton.setVisible(true);
			rightButton.setText("Edit");
			setFieldsVisible(true);
		} else {
			// not found. Allow to create ane one.
			address.setText("");
			postalCode.setText("");
			city.setText("");
			phone.setText("");
			clickEdit();
		}
	}

	private void clickClear() {
		name.setEditable(true);
		name.requestFocus();
		setFieldsVisible(false);
		leftButton.setVisible(false);
		rightButton.setText("Search");
		rightButton.setOnAction(e -> clickSearch());
	}

	private void clickEdit() {
		setFieldsEditable(true);
		setFieldsVisible(true);
		address.requestFocus();
		leftButton.setOnAction(e -> clickClear());
		leftButton.setVisible(true);
		leftButton.setText("Clear");
		rightButton.setOnAction(e -> clickInsert());
		rightButton.setVisible(true);
		rightButton.setText("Insert");
	}

	private void clickInsert() {
		AgendaPerson person = new AgendaPerson(name.getText(),
				address.getText(), city.getText(),
				postalCode.getText(), phone.getText());
		database.put(person.getName(), person);
		clickClear();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
