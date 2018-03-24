package controller;

import java.io.File;
import java.net.URL;

import calcu.calnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("계산기 김재은 민순욱 조준석");
		setRootLayout();
		setCalMainView();
	}
	
	public void setRootLayout()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			URL fxmlpath = new File("src/view/RootLayout.fxml").toURL();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void setCalMainView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			URL fxmlpath = new File("src/view/CalMainView.fxml").toURL();
			loader.setLocation(Main.class.getResource("../view/CalMainView.fxml"));
			AnchorPane calMainView = (AnchorPane) loader.load();
			rootLayout.setCenter(calMainView);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	public static void main(String[] args)
	{
		launch(args);
		
	}
}
