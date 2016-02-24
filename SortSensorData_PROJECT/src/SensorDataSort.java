import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.chart.Axis;
import javafx.scene.chart.ValueAxis; 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SensorDataSort extends Application{
	
	private ObservableList<XYChart.Series<Number, Number>> gyroXSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> gyroYSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> gyroZSerie = FXCollections.observableArrayList();
	
	private ObservableList<XYChart.Data<Number, Number>> gyroXData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> gyroYData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> gyroZData = FXCollections.observableArrayList(); 
	
	private ObservableList<XYChart.Series<Number, Number>> magXSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> magYSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> magZSerie = FXCollections.observableArrayList();
	
	private ObservableList<XYChart.Data<Number, Number>> magXData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> magYData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> magZData = FXCollections.observableArrayList(); 
	
	private ObservableList<XYChart.Series<Number, Number>> accXSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> accYSerie = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> accZSerie = FXCollections.observableArrayList();
	
	private ObservableList<XYChart.Data<Number, Number>> accXData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> accYData = FXCollections.observableArrayList(); 
	private ObservableList<XYChart.Data<Number, Number>> accZData = FXCollections.observableArrayList(); 
	
	
	private StackPane spGyroXYZ = new StackPane();
	private StackPane spMagXYZ = new StackPane();
	private StackPane spAccXYZ = new StackPane();
	
	private Scene sceneGyroXYZ = new Scene(spGyroXYZ,1300,700);
	private Scene sceneMagXYZ = new Scene(spMagXYZ,1300,700);
	private Scene sceneAccXYZ = new Scene(spAccXYZ,1300,700);
	
	
	private ArrayList<Integer> integrValGyroX = new ArrayList<Integer>();
	private ArrayList<Integer> integrValGyroY = new ArrayList<Integer>();
	private ArrayList<Integer> integrValGyroZ = new ArrayList<Integer>();
	
	private ArrayList<Integer> integrValAccX = new ArrayList<Integer>();
	private ArrayList<Integer> integrValAccY = new ArrayList<Integer>();
	private ArrayList<Integer> integrValAccZ = new ArrayList<Integer>();

	private long timeValueX = 0; 
	private long timeValueY = 0;
	private long timeValueZ = 0; 
	
	private long timeValueMagX = 0; 
	private long timeValueMagY = 0;
	private long timeValueMagZ = 0;
	
	private long timeValueAccX = 0; 
	private long timeValueAccY = 0;
	private long timeValueAccZ = 0; 
	
	private int valueGyroXMin; 
	private int valueGyroXMax;
	private int valueGyroYMin; 
	private int valueGyroYMax; 
	private int valueGyroZMin; 
	private int valueGyroZMax; 
	
	private int valueMagXMin; 
	private int valueMagXMax; 
	private int valueMagYMin; 
	private int valueMagYMax; 
	private int valueMagZMin; 
	private int valueMagZMax; 
	
	private int valueAccXMin; 
	private int valueAccXMax; 
	private int valueAccYMin; 
	private int valueAccYMax;
	private int valueAccZMin; 
	private int valueAccZMax;
	
	private int valueGyroMin;
	private int valueGyroMax; 
	
	private int valueMagMin;
	private int valueMagMax; 
	
	private int valueAccMin;
	private int valueAccMax;
	
	private int offsetGyroX; 
	private int offsetGyroY; 
	private int offsetGyroZ; 
	private int offsetAccX; 
	private int offsetAccY; 
	private int offsetAccZ; 
	private int offsetMagX; 
	private int offsetMagY; 
	private int offsetMagZ; 
	
	public void sortFile() throws IOException{
		File f = new File("teraterm.log");
		
		FileReader fr1 = new FileReader(f);
		LineNumberReader ln = new LineNumberReader(fr1);
		int count = 0;
		while (ln.readLine() != null){
			count++;
		}
		ln.close();
		fr1.close(); 
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		int sizeGyroX = 0; 
		int sizeGyroY = 0; 
		int sizeGyroZ = 0; 
		
		int sizeMagX = 0; 
		int sizeMagY = 0; 
		int sizeMagZ = 0; 
		
		int sizeAccX = 0; 
		int sizeAccY = 0; 
		int sizeAccZ = 0; 
		
		String[] gyroXArray = null;
		String[] gyroYArray = null;
		String[] gyroZArray = null;
		
		String[] magXArray = null;
		String[] magYArray = null;
		String[] magZArray = null;
		
		String[] accXArray = null;
		String[] accYArray = null;
		String[] accZArray = null;
	
		for(int i=0; i<count;i++){
			
			String line = br.readLine();
			String[] words = line.split(" ");
			
			for(int j=0; j<words.length;j++){
				
				if(words[0].equals("")==false){
					
					//long timeValue=Long.parseLong(words[0]);

					if(words[j].equals("GyroX")){
						sizeGyroX++;
					}
					else if(words[j].equals("GyroY")){
						sizeGyroY++;
					}
					else if(words[j].equals("GyroZ")){
						sizeGyroZ++;
					}
					else if(words[j].equals("MagX")){
						sizeMagX++;
					}
					else if(words[j].equals("MagY")){
						sizeMagY++;
					}
					else if(words[j].equals("MagZ")){
						sizeMagZ++;
					}
					else if(words[j].equals("AccX")){
						sizeAccX++;
					}
					else if(words[j].equals("AccY")){
						sizeAccY++;
					}
					else if(words[j].equals("AccZ")){
						sizeAccZ++;
					}
				}
			}
		}	
		
		fr.close(); 
		br.close(); 
		
		FileReader fr2 = new FileReader(f);
		BufferedReader br2 = new BufferedReader(fr2);
		
		gyroXArray = new String[sizeGyroX];
		gyroYArray = new String[sizeGyroY];
		gyroZArray = new String[sizeGyroZ];
		
		magXArray = new String[sizeMagX];
		magYArray = new String[sizeMagY];
		magZArray = new String[sizeMagZ];
		
		accXArray = new String[sizeAccX];
		accYArray = new String[sizeAccY];
		accZArray = new String[sizeAccZ];
		
		int xGyroCount = 0; 
		int yGyroCount = 0;
		int zGyroCount = 0;
		
		int xMagCount = 0; 
		int yMagCount = 0;
		int zMagCount = 0;
		
		int xAccCount = 0; 
		int yAccCount = 0;
		int zAccCount = 0;
		
		for(int i=0; i<count;i++){
			
			String line = br2.readLine();
			String[] words = line.split(" ");
		
			for(int j=0; j<words.length;j++){
			
				if(words[0].equals("")==false){				

					if(words[j].equals("GyroX")){
						words[j++] = "foo";
						gyroXArray[xGyroCount]=words[j++];
						xGyroCount++;
						
					}
					else if(words[j].equals("GyroY")){
						words[j++] = "foo";
						gyroYArray[yGyroCount]=words[j++];
						yGyroCount++;
					}
					else if(words[j].equals("GyroZ")){
						words[j++] = "foo";
						gyroZArray[zGyroCount]=words[j++];
						zGyroCount++;
					}
					else if(words[j].equals("MagX")){
						words[j++] = "foo";
						magXArray[xMagCount]=words[j++];			
						xMagCount++;
					}
					else if(words[j].equals("MagY")){
						words[j++] = "foo";
						magYArray[yMagCount]=words[j++];
						yMagCount++;
					}
					else if(words[j].equals("MagZ")){
						words[j++] = "foo";
						magZArray[zMagCount]=words[j++];
						zMagCount++;
					}
					else if(words[j].equals("AccX")){
						words[j++] = "foo";
						accXArray[xAccCount]=words[j++];
						xAccCount++;
					}
					else if(words[j].equals("AccY")){
						words[j++] = "foo";
						accYArray[yAccCount]=words[j++];
						yAccCount++;
					}
					else if(words[j].equals("AccZ")){
						words[j++] = "foo";
						accZArray[zAccCount]=words[j++];
						zAccCount++;
					}
					else if(words[j].equals("OFFSETGYROX")){
						words[j++] = "foo";
						offsetGyroX+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETGYROY")){
						words[j++] = "foo";
						offsetGyroY+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETGYROZ")){
						words[j++] = "foo";
						offsetGyroZ+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETACCX")){
						words[j++] = "foo";
						offsetAccX+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETACCY")){
						words[j++] = "foo";
						offsetAccY+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETACCZ")){
						words[j++] = "foo";
						offsetAccZ+=Integer.parseInt(words[j++]);
					}	
					else if(words[j].equals("OFFSETMAGX")){
						words[j++] = "foo";
						offsetMagX+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETMAGY")){
						words[j++] = "foo";
						offsetMagY+=Integer.parseInt(words[j++]);
					}
					else if(words[j].equals("OFFSETMAGZ")){
						words[j++] = "foo";
						offsetMagZ+=Integer.parseInt(words[j++]);
					}	
				}
			}
		}
		
		offsetGyroX /= 60; 
		offsetGyroY /= 60; 
		offsetGyroZ /= 60; 
		/*
		System.out.println(+offsetGyroX);
		System.out.println(+offsetGyroY);
		System.out.println(+offsetGyroZ);
		*/
		offsetAccX /= 60; 
		offsetAccY /= 60; 
		offsetAccZ /= 60; 
		/*
		System.out.println(+offsetAccX);
		System.out.println(+offsetAccY);
		System.out.println(+offsetAccZ);
		*/
		offsetMagX /= 100; 
		offsetMagY /= 100; 
		offsetMagZ /= 100; 
		/*
		System.out.println(+offsetMagX);
		System.out.println(+offsetMagY);
		System.out.println(+offsetMagZ);
		*/ 
		
		int c = 0;
		for(int k=0; k<gyroXArray.length;k++){
			timeValueX+=15;
			if(k==0){
				integrValGyroX.add(0); 
			}
			else{
				c = Integer.parseInt(gyroXArray[k-1])-offsetGyroX;
				//System.out.println(c);
				integrValGyroX.add((integrValGyroX.get(k-1)+c)); 
				System.out.println(+(integrValGyroX.get(k-1)+c));
			}	
			gyroXData.add(new XYChart.Data<Number, Number>(timeValueX,(integrValGyroX.get(k))));
		}
		
		for(int k=0; k<gyroYArray.length;k++){
			timeValueY+=15;
			if(k==0){
				integrValGyroY.add(0); 
			}
			else{
				c = Integer.parseInt(gyroYArray[k-1])-offsetGyroY;
				integrValGyroY.add((integrValGyroY.get(k-1)+ c)); 
			}
			
			gyroYData.add(new XYChart.Data<Number, Number>(timeValueY,integrValGyroY.get(k)));
		}
		
		for(int k=0; k<gyroZArray.length;k++){
			timeValueZ+=15;
			if(k==0){
				integrValGyroZ.add(0); 
			}
			else{
				c = Integer.parseInt(gyroZArray[k-1])-offsetGyroZ;
				integrValGyroZ.add((integrValGyroZ.get(k-1)+c)); 
	
			}
			gyroZData.add(new XYChart.Data<Number, Number>(timeValueZ,integrValGyroZ.get(k)));
		}
		
		for(int k=0; k<accXArray.length;k++){
			timeValueAccX+=15;
			if(k==0){
				integrValAccX.add(0); 
			}
			else{
				integrValAccX.add((integrValAccX.get(k-1)+Integer.parseInt(accXArray[k-1]))-offsetAccX); 
			}	
			accXData.add(new XYChart.Data<Number, Number>(timeValueAccX,integrValAccX.get(k)));
		}
		
		for(int k=0; k<accYArray.length;k++){
			timeValueAccY+=15;
			if(k==0){
				integrValAccY.add(0); 
			}
			else{
				integrValAccY.add((integrValAccY.get(k-1)+Integer.parseInt(accYArray[k-1]))-offsetAccY); 
			}	
			accYData.add(new XYChart.Data<Number, Number>(timeValueAccY,integrValAccY.get(k)));
		}
		
		for(int k=0; k<accZArray.length;k++){
			timeValueAccZ+=15;
			if(k==0){
				integrValAccZ.add(0); 
			}
			else{
				integrValAccZ.add((integrValAccZ.get(k-1)+Integer.parseInt(accZArray[k-1]))-offsetAccZ); 
			}	
			accZData.add(new XYChart.Data<Number, Number>(timeValueAccZ,integrValAccZ.get(k)));
		}
		
		for(int k=0; k<magXArray.length;k++){
			timeValueMagX+=15;
			magXData.add(new XYChart.Data<Number, Number>(timeValueMagX,(Integer.parseInt(magXArray[k]))));
		}
		
		for(int k=0; k<magYArray.length;k++){
			timeValueMagY+=15;
			magYData.add(new XYChart.Data<Number, Number>(timeValueMagY,(Integer.parseInt(magYArray[k]))));
		}
		
		for(int k=0; k<magZArray.length;k++){
			timeValueMagZ+=15;
			magZData.add(new XYChart.Data<Number, Number>(timeValueMagZ,(Integer.parseInt(magZArray[k]))));
		}

		fr2.close(); 
		br2.close(); 
		
		File f2 = new File("SortedData");
		if(f2.exists()){}
		else{
			f2.createNewFile();
		}
		
		FileWriter fw = new FileWriter(f2,false);
		BufferedWriter bw = new BufferedWriter(fw);			
		
		valueGyroXMin = integrValGyroX.get(0); 
		valueGyroXMax = integrValGyroX.get(0); 
		valueGyroYMin = integrValGyroY.get(0);  
		valueGyroYMax = integrValGyroY.get(0); 
		valueGyroZMin = integrValGyroZ.get(0);  
		valueGyroZMax = integrValGyroZ.get(0);   
		
		valueMagXMin = Integer.parseInt(magXArray[0]); 
		valueMagXMax = Integer.parseInt(magXArray[0]);  
		valueMagYMin = Integer.parseInt(magYArray[0]); 
		valueMagYMax = Integer.parseInt(magYArray[0]);
		valueMagZMin = Integer.parseInt(magZArray[0]); 
		valueMagZMax = Integer.parseInt(magZArray[0]);
		
		valueAccXMin = integrValAccX.get(0);  
		valueAccXMax = integrValAccX.get(0); 
		valueAccYMin = integrValAccY.get(0);  
		valueAccYMax = integrValAccY.get(0); 
		valueAccZMin = integrValAccZ.get(0);   
		valueAccZMax = integrValAccZ.get(0); 
		
		for(int k=1; k<integrValGyroX.size();k++){
			
			if( integrValGyroX.get(k)>valueGyroXMax){
				valueGyroXMax = integrValGyroX.get(k);
			}
			else if( integrValGyroX.get(k)<valueGyroXMin){
				valueGyroXMin = integrValGyroX.get(k);
			}
		}
	
		
		for(int k=1; k<integrValGyroY.size();k++){
			if( integrValGyroY.get(k)>valueGyroYMax){
				valueGyroYMax = integrValGyroY.get(k);
			}
			else if(integrValGyroY.get(k)<valueGyroXMin){
				valueGyroYMin = integrValGyroY.get(k);
			}
		}
		
		for(int k=1; k<integrValGyroZ.size();k++){
			if(integrValGyroZ.get(k)>valueGyroZMax){
				valueGyroZMax = integrValGyroZ.get(k);
			}
			else if( integrValGyroZ.get(k)<valueGyroZMin){
				valueGyroZMin = integrValGyroZ.get(k);;
			}
		}
		
		for(int k=1; k<magXArray.length;k++){
			if(Integer.parseInt(magXArray[k])>valueMagXMax){
				valueMagXMax = Integer.parseInt(magXArray[k]);
			}
			else if(Integer.parseInt(magXArray[k])<valueMagXMin){
				valueMagXMin = Integer.parseInt(magXArray[k]);
			}
		}
		
		for(int k=1; k<magYArray.length;k++){
			if(Integer.parseInt(magYArray[k])>valueMagYMax){
				valueMagYMax = Integer.parseInt(magYArray[k]);
			}
			else if(Integer.parseInt(magYArray[k])<valueMagYMin){
				valueMagYMin = Integer.parseInt(magYArray[k]);
			}
		}
		
		for(int k=1; k<magZArray.length;k++){
			if(Integer.parseInt(magZArray[k])>valueMagZMax){
				valueMagZMax = Integer.parseInt(magZArray[k]);
			}
			else if(Integer.parseInt(magZArray[k])<valueMagZMin){
				valueMagZMin = Integer.parseInt(magZArray[k]);
			}
		}
		
		for(int k=1; k<integrValAccX.size();k++){
			if(integrValAccX.get(k)>valueAccXMax){
				valueAccXMax = integrValAccX.get(k);
			}
			else if(integrValAccX.get(k)<valueAccXMin){
				valueAccXMin = integrValAccX.get(k);
			}
		}
		
		for(int k=1; k<integrValAccY.size();k++){
			if(integrValAccY.get(k)>valueAccYMax){
				valueAccYMax = integrValAccY.get(k);
			}
			else if(integrValAccY.get(k)<valueAccYMin){
				valueAccYMin = integrValAccY.get(k);
			}
		}
		
		for(int k=1; k<integrValAccZ.size();k++){
			if(integrValAccZ.get(k)>valueAccZMax){
				valueAccZMax = integrValAccZ.get(k);
			}
			else if(integrValAccZ.get(k)<valueAccZMin){
				valueAccZMin = integrValAccZ.get(k);
			}
		}
		
		if(valueGyroXMax>valueGyroYMax){
			if(valueGyroXMax>valueGyroZMax){
				valueGyroMax = valueGyroXMax;
			}
			else{
				valueGyroMax = valueGyroZMax;
			}
		}
		else{
			if(valueGyroYMax>valueGyroZMax){
				valueGyroMax = valueGyroYMax;
			}
			else{
				valueGyroMax = valueGyroZMax;
			}
		}
		
		if(valueGyroXMin<valueGyroYMin){
			if(valueGyroXMin<valueGyroZMin){
				valueGyroMin = valueGyroXMin;
			}
			else{
				valueGyroMin = valueGyroZMin;
			}
		}
		else{
			if(valueGyroYMin<valueGyroZMin){
				valueGyroMin = valueGyroYMin;
			}
			else{
				valueGyroMin = valueGyroZMin;
			}
		}
		
		if(valueMagXMax>valueMagYMax){
			if(valueMagXMax>valueMagZMax){
				valueMagMax = valueMagXMax;
			}
			else{
				valueMagMax = valueMagZMax;
			}
		}
		else{
			if(valueMagYMax>valueMagZMax){
				valueMagMax = valueMagYMax;
			}
			else{
				valueMagMax = valueMagZMax;
			}
		}
		
		if(valueMagXMin<valueMagYMin){
			if(valueMagXMin<valueMagZMin){
				valueMagMin = valueMagXMin;
			}
			else{
				valueMagMin = valueMagZMin;
			}
		}
		else{
			if(valueMagYMin<valueMagZMin){
				valueMagMin = valueMagYMin;
			}
			else{
				valueMagMin = valueMagZMin;
			}
		}
		
		if(valueAccXMax>valueAccYMax){
			if(valueAccXMax>valueAccZMax){
				valueAccMax = valueAccXMax;
			}
			else{
				valueAccMax = valueAccZMax;
			}
		}
		else{
			if(valueAccYMax>valueAccZMax){
				valueAccMax = valueAccYMax;
			}
			else{
				valueAccMax = valueAccZMax;
			}
		}
		
		if(valueAccXMin<valueAccYMin){
			if(valueAccXMin<valueAccZMin){
				valueAccMin = valueAccXMin;
			}
			else{
				valueAccMin = valueAccZMin;
			}
		}
		else{
			if(valueAccYMin<valueAccZMin){
				valueAccMin = valueAccYMin;
			}
			else{
				valueAccMin = valueAccZMin;
			}
		}
	
		bw.write("GyroX\r\n");
		bw.flush();
		for(int k=0; k<gyroXArray.length;k++){
			bw.write(gyroXArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("GyroY\r\n");
		bw.flush();
		for(int k=0; k<gyroYArray.length;k++){
			bw.write(gyroYArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("GyroZ\r\n");
		bw.flush();
		for(int k=0; k<gyroZArray.length;k++){
			bw.write(gyroZArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("MagX\r\n");
		bw.flush();
		for(int k=0; k<magXArray.length;k++){
			bw.write(magXArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("MagY\r\n");
		bw.flush();
		for(int k=0; k<magYArray.length;k++){
			bw.write(magYArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("MagZ\r\n");
		bw.flush();
		for(int k=0; k<magZArray.length;k++){
			bw.write(magZArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("AccX\r\n");
		bw.flush();
		for(int k=0; k<accXArray.length;k++){
			bw.write(accXArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("AccY\r\n");
		bw.flush();
		for(int k=0; k<accYArray.length;k++){
			bw.write(accYArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		bw.write("\r\n");
		bw.write("\r\n");
		bw.write("AccZ\r\n");
		bw.flush();
		for(int k=0; k<accZArray.length;k++){
			bw.write(accZArray[k]);
			bw.flush();
			bw.write("\r\n");
		}
		
		fw.close(); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		sortFile();
		
		displayGraph();
		primaryStage.setTitle("Gyroscope Data X Y Z");
		primaryStage.setScene(sceneGyroXYZ); 
		primaryStage.show();	
		
		Stage secondaryStage = new Stage();
		secondaryStage.setTitle("Mag Data X Y Z");
		secondaryStage.setScene(sceneMagXYZ);
		secondaryStage.show(); 
		
		Stage thirdStage = new Stage();
		thirdStage.setTitle("Acc Data X Y Z");
		thirdStage.setScene(sceneAccXYZ);
		thirdStage.show(); 
	}

	private void displayGraph() {
		
		LineChart<Number, Number> lc = null; 
		LineChart<Number, Number> lc2 = null;
		LineChart<Number, Number> lc3 = null;
		
		gyroXSerie.add(new XYChart.Series<>("Gyro X Pos", gyroXData));
		gyroYSerie.add(new XYChart.Series<>("Gyro Y Pos", gyroYData));
		gyroZSerie.add(new XYChart.Series<>("Gyro Z Pos", gyroZData));
		lc = new LineChart<>(new NumberAxis("Time(ms)",0,timeValueZ+(timeValueZ%10), timeValueZ/10), new NumberAxis("Acceleration angulaire",valueGyroMin-(valueGyroMin%10),valueGyroMax+(valueGyroMax%10),valueGyroMax/10));
		
		lc.getData().addAll(gyroXSerie);
		lc.getData().addAll(gyroYSerie);
		lc.getData().addAll(gyroZSerie);
		
		magXSerie.add(new XYChart.Series<>("Mag X", magXData));
		magYSerie.add(new XYChart.Series<>("Mag Y", magYData));
		magZSerie.add(new XYChart.Series<>("Mag Z", magZData));
		lc2 = new LineChart<>(new NumberAxis("Time(ms)",0,timeValueMagZ+(timeValueZ%10), timeValueZ/10), new NumberAxis("Champ Magnetique",valueMagMin-(valueMagMin%10),valueMagMax+(valueMagMax%10), valueMagMax/10));
		
		lc2.getData().addAll(magXSerie);
		lc2.getData().addAll(magYSerie);
		lc2.getData().addAll(magZSerie);
		
		accXSerie.add(new XYChart.Series<>("Acc X", accXData));
		accYSerie.add(new XYChart.Series<>("Acc Y", accYData));
		accZSerie.add(new XYChart.Series<>("Acc Z", accZData));
		lc3 = new LineChart<>(new NumberAxis("Time(ms)",0,timeValueAccZ+(timeValueZ%10), timeValueZ/10), new NumberAxis("Vitesse",valueAccMin-(valueAccMin%10),valueAccMax+(valueAccMax%10),valueAccMax/10));
		lc3.getData().addAll(accXSerie);
		lc3.getData().addAll(accYSerie);
		lc3.getData().addAll(accZSerie);
		
		lc.setCreateSymbols(false);
		lc2.setCreateSymbols(false);
		lc3.setCreateSymbols(false);
		
		spGyroXYZ.getChildren().add(lc);
		spMagXYZ.getChildren().add(lc2);
		spAccXYZ.getChildren().add(lc3);
		
	}
	
	public static void main(String[] args) throws IOException {	
		Application.launch(); 
	}
	
}

