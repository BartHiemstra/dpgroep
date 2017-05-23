


import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JPanel;
import javafx.stage.Stage;
import javafx.application.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.io.Serializable;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;

//vakid-Klasid-DocentID-Datum
public class visual extends Application {
	
	
	// hoe je labels maakt
	Label intro = new Label("Welkom bij de roosterapp");
	Label text = new Label("Voer een docent in:");
	TextField docentInput = new TextField("");
	Label datum = new Label("Voer een datum in:");
	DatePicker datumvoorles = new DatePicker();
	Label klas = new Label("Voer een klas in:");
	TextField klasInput = new TextField("");
	Label vak = new Label("Voer een vak:");
	TextField vakInput = new TextField("");
	Label roosterVlak=new Label();

	
	// het invoeren van het scrollbar rooster deLeerlingen.add("hellos");

	@Override
	public void start(Stage primaryStage) {
		// hoe je de grid maakt
		FlowPane grid = new FlowPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(0);
		// set the size of buttons and labels
		intro.setPrefSize(350, 3);
		text.setPrefSize(350, 0);
		docentInput.setPrefSize(190, 2);
		datum.setPrefSize(350, 3);
		datumvoorles.setPrefSize(190, 3);
		datumvoorles.setValue(LocalDate.now());
		klas.setPrefSize(350, 3);
		klasInput.setPrefSize(190, 3);
		vak.setPrefSize(350, 3);
		vakInput.setPrefSize(190, 3);
		roosterVlak.setPrefSize(700, 150);
		// buttons and boxes
		HBox box = new HBox(10);
		Button but1 = new Button("Bevestig invoering");
		Button but2 = new Button("Rooster inladen");

		// buttons implementeren
		but1.setOnAction(e -> voeglestoe());
		but2.setOnAction(e -> laadrooster());
		box.getChildren().addAll(but1,but2);
		box.setPrefWidth(330);
		box.setPrefHeight(1);
		box.setAlignment(Pos.BOTTOM_RIGHT);
		grid.getChildren().addAll(intro, text, docentInput, datum, datumvoorles, klas, klasInput, vak, vakInput,box,roosterVlak);
		Scene scene = new Scene(grid, 700, 700);
		primaryStage.setTitle("Roosterapp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void voeglestoe() {
		Colleges s1= new Colleges();
		ArrayList<String> deLes = s1.getDatumsOpVolgorde();
		Utils u1 = new Utils();

		
		
		String docent = docentInput.getText();
		String vak = vakInput.getText();
		String klas = klasInput.getText();
		LocalDate rawdata = datumvoorles.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String lesDatum = rawdata.format(formatter);
		lesDatum = lesDatum.replace(" ", "-");
		
		if (docent.equals("") || vak.equals("") || klas.equals("")) {
			intro.setText("Velden Zijn Leeggebleven");
			return;

		}
		if (u1.exists(docent)==false || u1.exists2(klas)==false || u1.exists3(vak)==false) {
			
			//hier wordt gecontroleerd of deze lessen uberhoubt bestaan
			intro.setText("deze velden zijn fout");
			return;
		}
		
		String s= vak+" "+docent+" "+klas+" "+lesDatum+" ";
		String s3= vak+" "+klas+" "+docent+" "+lesDatum;
		for(String f:deLes){
			System.out.println(f+"f");
			System.out.println(s3+"f");
			if(f.equals(s3)){
			
				
				intro.setText("Deze velden bestaan al");
				return;
			}
		}

		intro.setText("succesvol Les toegevoegd");
		voegLesToe(s);
		

	}
	void laadrooster(){
		Colleges s1= new Colleges();
		ArrayList<String> deLes = s1.getDatumsOpVolgorde();
		//vakid-Klasid-DocentID-Datum
		String s = "";
		for(String f:deLes){
			String z="";
			String[] spit=f.split(" ");
			int i =0;
			for(String y: spit){
				if(i==0){
					z=z+"vakID: "+y;
					
				}
				if (i==1){
					z=z+"\t \t \t klasID:"+y;
				}
				if (i==2){
					z=z+"\t \t \t docentID: "+y;
				}
				if (i==3){
					z=z+"\t \t \t datum: "+y;
				}
				
				i++;
				
			}
			
			
			s=s+z+"\n";
			
		}
		roosterVlak.setText(s);
		
	}
	

	public void voegLesToe(String s){
		System.out.println("bever");
		String[] list = s.split(" ");
		String datum="";
		String docent="";
		String vak="";
		String klas="";
		int i=0;
		for(String x:list){
			System.out.println(i+x);
			if(i==0){vak=x;}
			if(i==1){docent=x;}
			if(i==2){klas=x;}
			if(i==3){datum=x;}
			
			i++;
			
			
		}
		Colleges s2= new Colleges();
		s2.createLes(docent, datum, klas, vak);
	}
	// hier komt een check of docentnummer in lijst zit

	public static void main(String[] args) {
		Application.launch(args);
	
		
		

	}
}