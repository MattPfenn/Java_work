package ch.collector.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.collections.FXCollections;

@SuppressWarnings("restriction")
public class CollectorGui extends Application {

	private final ComboBox<String> cbStations = new ComboBox<String>();
	private final ComboBox<String> cbQuantity = new ComboBox<String>();

	private ScrollPane sp = new ScrollPane();

	private Button btOpen = new Button("Open");
	private Button btShow = new Button("Show Graph");

	private GridPane paneTop = new GridPane();
	private GridPane paneBot = new GridPane();
	private GridPane paneCenter = new GridPane();

	private BorderPane bp = new BorderPane();
	
	private Scene scene = new Scene(bp, 400, 300);
	private Scene graphic = null;

	private ObservableList<XYChart.Series<Long, Number>> temperatureSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Long, Number>> humiditySerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Long, Number>> rainfallSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Long, Number>> pressureSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Long, Number>> windSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Data<Long, Number>> humidityData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Long, Number>> rainfallData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Long, Number>> temperatureData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Long, Number>> pressureData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Long, Number>> windData = FXCollections.observableArrayList(); 
	

	public void addStationsToCB() throws IOException {
		File f = new File("config");
		
		FileReader fr1 = new FileReader(f);
		LineNumberReader ln = new LineNumberReader(fr1);
		int count = 0;
		while (ln.readLine() != null){
			count++;
		}
		ln.close();
		fr1.close(); 
		
		FileReader fr2 = new FileReader(f);
		BufferedReader br = new BufferedReader(fr2);
		for(int i=0; i<count;i++){
			cbStations.getItems().add(br.readLine()); 
		}
		br.close();
		fr2.close(); 
	}

	public void addQuantityToCB() throws IOException {
		
		temperatureSerie.removeAll();
		pressureSerie.removeAll();
		windSerie.removeAll(); 
		rainfallSerie.removeAll();
		humiditySerie.removeAll();
		
		cbQuantity.getItems().clear(); 
		 
		String station = (String) cbStations.getValue(); 
		File f = new File("database\\"+station+"\\data.txt");
		
		FileReader fr1 = new FileReader(f);
		LineNumberReader ln = new LineNumberReader(fr1);
		int count = 0;
		while (ln.readLine() != null){
			count++;
		}
		ln.close();
		fr1.close(); 
		
		FileReader fr2 = new FileReader(f);
		BufferedReader br = new BufferedReader(fr2);
		
		boolean wsFlag = false; 
		boolean prFlag = false; 
		boolean teFlag = false; 
		boolean huFlag = false; 
		boolean rrFlag = false; 
		
		for(int i=0; i<count;i++){
			
			String line = br.readLine();
			String[] words = line.split(" ");
			
			for(int j=0; j<words.length;j++){
			
				if(words[0].equals("")==false){
					
					long timeValue=Long.parseLong(words[0]);

					if(words[j].equals("WS")){
						windData.add(new XYChart.Data<Long, Number>(timeValue,Integer.parseInt(words[j+2])));
						if(wsFlag == false){
							cbQuantity.getItems().add("Wind Speed");
							
							wsFlag = true; 
						}
					}
					else if(words[j].equals("PR")){
						pressureData.add(new XYChart.Data<Long, Number>(timeValue,Integer.parseInt(words[j+2])));
						if(prFlag == false){
							cbQuantity.getItems().add("Pressure");
							prFlag = true; 
						}
					}
					else if(words[j].equals("TE")){
						temperatureData.add(new XYChart.Data<Long, Number>(timeValue,Integer.parseInt(words[j+2])));
						if(teFlag == false){
							cbQuantity.getItems().add("Temperature");
							teFlag = true; 
						}
					}
					else if(words[j].equals("HU")){
						humidityData.add(new XYChart.Data<Long, Number>(timeValue,Integer.parseInt(words[j+2])));
						if(huFlag == false){
							cbQuantity.getItems().add("Humidity");
							huFlag = true; 
						}
					}
					else if(words[j].equals("RR")){
						rainfallData.add(new XYChart.Data<Long, Number>(timeValue,Integer.parseInt(words[j+2])));
						if(rrFlag == false){
							cbQuantity.getItems().add("Rainfall Rate");
							rrFlag = true; 
						}
					}
				}	
			}
		}
		br.close();
		fr2.close();
	}

	public void showGraph() {
		Stage secondaryStage = new Stage();
		
		String quantity = (String) cbQuantity.getValue().toString();
		secondaryStage.setTitle(quantity);
		
		 LineChart<Long, Number> lc = null; 
		
		switch(quantity){
			case "Wind Speed" : 	 windSerie.add(new XYChart.Series<>("Wind Speed", windData));
									 lc = new LineChart<>(new DateAxis(), new NumberAxis("Value (m/s)",0,130, 10),windSerie);
									 break; 
			
		    case "Temperature" : 	 temperatureSerie.add(new XYChart.Series<>("Temperature", temperatureData));
		    						 lc = new LineChart<>(new DateAxis(), new NumberAxis("Value (°C)",0,38, 10),temperatureSerie);
									 break; 
								 
			case "Rainfall Rate" :   rainfallSerie.add(new XYChart.Series<>("Rainfall Rate", rainfallData));
									 lc = new LineChart<>(new DateAxis(), new NumberAxis("Value (%)",0,100, 10),rainfallSerie);
			 					     break; 
			
			case "Humidity" : 		 humiditySerie.add(new XYChart.Series<>("Humidity", humidityData));
									 lc = new LineChart<>(new DateAxis(), new NumberAxis("Value (%)",0,100, 10),humiditySerie);
								     break; 
								     
			case "Pressure" :		 pressureSerie.add(new XYChart.Series<>("Pressure", pressureData));
			 						 lc = new LineChart<>(new DateAxis(), new NumberAxis("Value (Pa)",0,20, 10),pressureSerie);
			 						 break; 
		} 
		
		graphic = new Scene(lc, 500, 250);
		
		secondaryStage.setScene(graphic);
		secondaryStage.show();
	}

	public void open() throws IOException {
		
		paneCenter.getChildren().clear(); 
		
		String station = (String) cbStations.getValue(); 
		
		File f = new File("database\\"+station+"\\status.txt");
		FileReader fr1 = new FileReader(f);
		LineNumberReader ln = new LineNumberReader(fr1);
		int count = 0;
		while (ln.readLine() != null){
			count++;
		}
		ln.close();
		fr1.close(); 
		
		FileReader fr2 = new FileReader(f);
		BufferedReader br = new BufferedReader(fr2);
		
		TextField tf;
		
		paneCenter.add(new Label("Last seen : "), 0, 0);
		paneCenter.add(tf = new TextField(br.readLine()),1,0);
		tf.setEditable(false);
		
		for(int i=1; i<count;i++){
			String line = br.readLine(); 
			String[] words = line.split("=");
			
			paneCenter.add(new Label(words[0]+" : "),0,i);
			paneCenter.add(tf = new TextField(words[1]),1,i);
			tf.setEditable(false);
		}
		br.close();
		fr2.close();
		
		addQuantityToCB();
	}

	public void start(Stage primaryStage) throws IOException {

		primaryStage.setTitle("Collector");
		primaryStage.setScene(scene); 

		displayPane();

		primaryStage.show(); 
	}

	public void displayPane() throws IOException {

		addStationsToCB();

		paneTop.getColumnConstraints().add(new ColumnConstraints(60));
		paneTop.getColumnConstraints().add(new ColumnConstraints(200));
		paneTop.getColumnConstraints().add(new ColumnConstraints(100));

		paneBot.getColumnConstraints().add(new ColumnConstraints(60));
		paneBot.getColumnConstraints().add(new ColumnConstraints(200));
		paneBot.getColumnConstraints().add(new ColumnConstraints(100));

		paneCenter.getColumnConstraints().add(new ColumnConstraints(60));
		paneCenter.getColumnConstraints().add(new ColumnConstraints(200));

		paneTop.setPadding(new Insets(10, 10, 10, 10));
		paneTop.setAlignment(Pos.CENTER);
		paneTop.setHgap(5);
		paneTop.setVgap(5);

		paneBot.setPadding(new Insets(10, 10, 10, 10));
		paneBot.setAlignment(Pos.CENTER);
		paneBot.setHgap(5);
		paneBot.setVgap(5);

		paneTop.add(new Label("Station :"), 0, 0);
		paneTop.add(cbStations, 1, 0);
		paneTop.add(btOpen, 2, 0);

		paneBot.add(new Label("Quantity :"), 0, 0);
		paneBot.add(cbQuantity, 1, 0);
		paneBot.add(btShow, 2, 0);

		paneTop.setHalignment(btOpen, HPos.RIGHT);
		paneBot.setHalignment(btShow, HPos.RIGHT);

		paneCenter.setPadding(new Insets(10, 10, 10, 10));
		paneCenter.setHgap(10);
		paneCenter.setVgap(10);
		paneCenter.setAlignment(Pos.CENTER);
		sp.setContent(paneCenter);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		bp.setMargin(sp, new Insets(20, 50, 40, 50));
		bp.setTop(paneTop);
		bp.setCenter(sp);
		bp.setBottom(paneBot);
		
		btOpen.setOnAction(e -> { 
			try{
				open();
			}
			catch(IOException er){
				er.printStackTrace();
			}
		});
		
		btShow.setOnAction(e -> showGraph());
	}

	public static void main(String[] args) throws IOException {
		Application.launch();
	}
}
