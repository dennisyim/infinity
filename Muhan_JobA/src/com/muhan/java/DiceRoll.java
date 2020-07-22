package com.muhan.java;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class DiceRoll extends Application {
	int num1;
	int num2;
	int gameMoney=1000000;	

	@Override
	public void start(Stage stage) {
		try {
			//�ֻ��� �̹��� 6���� �迭�� ������
			Image[] images =
				{new Image("file:1.png"),
				new Image("file:2.png"),
				new Image("file:3.png"),
				new Image("file:4.png"),
				new Image("file:5.png"),
				new Image("file:6.png")};
		
			//6���� ������ �ٲ�� gif ������ ���� ����
			Image roll = new Image("file:rolldice.gif");
			
			//�Ҹ� ȿ�� ����
			MediaPlayer audio = new MediaPlayer(new Media(new File("Rolling_Dice.wav").toURI().toString()));
			
			//â ũ�� : 500,500
			StackPane pane = new StackPane();
			Scene scene = new Scene(pane,500,500);
			
			//��ư 3�� �����
			Button buttonOdd = new Button();
			Button buttonEven = new Button();
			Button buttonRoll = new Button();
			
			buttonOdd.setText("Ȧ��"); 
			buttonEven.setText("¦��"); 
			buttonRoll.setText("Roll"); 

			buttonOdd.setStyle("-fx-font-size:30;-fx-background-color:pink");//��Ʈ ũ��,����
			buttonEven.setStyle("-fx-font-size:30;-fx-background-color:green");
			buttonRoll.setStyle("-fx-font-size:30");
			
			//ó�� �ȳ� �ؽ�Ʈ ǥ�� 
			Label text = new Label();
			text.setText("Roll�� ���� ���� Ȧ¦  ����");
			text.setStyle("-fx-font-size:35;-fx-text-fill:blue;");	
			
			//���ӸӴ� ǥ��
			Label moneyText = new Label();
			moneyText.setText(Integer.toString(gameMoney)+"��");
			moneyText.setStyle("-fx-font-size:20;-fx-text-fill:blue;");
	
			//�ֻ��� 2���� ��Ÿ���� �̹������ 2��
			ImageView iv1 = new ImageView();
			ImageView iv2 = new ImageView();
			
			iv1.setImage(images[5]);//ó���� '6'�� ǥ�õ� �ֻ��� ǥ��
            iv2.setImage(images[5]);
 	
			//Ȧ�� ��ư ������ �� �Ǵ�
			buttonOdd.setOnAction(e->{
				audio.stop();;//�Ҹ� �����
				//1~6���� ������ 2�� ��´�
				num1 = (int)(Math.random()*6)+1;
				num2 = (int)(Math.random()*6)+1;
				int sum = num1 + num2;		//2�� �հ�
								
				if(sum%2==0) {//������� �Ǵ�
					text.setText("You Loose");
					gameMoney-=50000;//���ӸӴ� -
				}
				else {
					text.setText("You Win");
					gameMoney+=50000;
				}	
				iv1.setImage(images[num1-1]);//������ ���� ���ڴ� 0~6�̰�, �̹��� �迭�� 0~5�̹Ƿ� -1����
	            iv2.setImage(images[num2-1]);	  
				moneyText.setText(Integer.toString(gameMoney)+"��");
				pane.getChildren().addAll(iv1, iv2, text, moneyText,buttonOdd, buttonEven, buttonRoll);
			});
			
			//¦�� ��ư ������ �� �Ǵ�
			buttonEven.setOnAction(e->{
				audio.stop();
				num1 = (int)(Math.random()*6)+1;
				num2 = (int)(Math.random()*6)+1;
				int sum = num1 + num2;		
								
				if(sum%2==0) {
					text.setText("You Win");
					gameMoney+=50000;
				}
				else {
					text.setText("You Loose");
					gameMoney-=50000;
				}	
				iv1.setImage(images[num1-1]);
	            iv2.setImage(images[num2-1]);	  
				moneyText.setText(Integer.toString(gameMoney)+"��");
				pane.getChildren().addAll(iv1, iv2, text, moneyText, buttonOdd, buttonEven, buttonRoll);
	
			});
			
			//Roll ��ư ������ ��
			buttonRoll.setOnAction(e->{
				audio.play();//�Ҹ� ���
				iv1.setImage(roll);
	            iv2.setImage(roll);	
	            text.setText("Ȧ¦�� �ϳ��� �����ϼ���");
				pane.getChildren().addAll(iv1, iv2, text, moneyText, buttonOdd, buttonEven, buttonRoll);
	
			});
			
            pane.getChildren().addAll(iv1, iv2, text, moneyText, buttonOdd, buttonEven, buttonRoll);
            
			StackPane.setAlignment(iv1, Pos.TOP_LEFT);
			StackPane.setAlignment(iv2, Pos.TOP_RIGHT);
			StackPane.setAlignment(buttonOdd, Pos.BOTTOM_LEFT);
			StackPane.setAlignment(buttonEven, Pos.BOTTOM_RIGHT);
			StackPane.setAlignment(buttonRoll, Pos.BOTTOM_CENTER);
			StackPane.setAlignment(moneyText, Pos.TOP_CENTER);
			StackPane.setAlignment(text, Pos.CENTER);
			
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
		
	public static void main(String[] args) {
		launch(args);
	}
}

