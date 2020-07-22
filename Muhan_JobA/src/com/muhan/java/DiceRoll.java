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
			//주사위 이미지 6개를 배열로 지정함
			Image[] images =
				{new Image("file:1.png"),
				new Image("file:2.png"),
				new Image("file:3.png"),
				new Image("file:4.png"),
				new Image("file:5.png"),
				new Image("file:6.png")};
		
			//6개가 번갈아 바뀌는 gif 파일을 따로 만듬
			Image roll = new Image("file:rolldice.gif");
			
			//소리 효과 설정
			MediaPlayer audio = new MediaPlayer(new Media(new File("Rolling_Dice.wav").toURI().toString()));
			
			//창 크기 : 500,500
			StackPane pane = new StackPane();
			Scene scene = new Scene(pane,500,500);
			
			//버튼 3개 만들기
			Button buttonOdd = new Button();
			Button buttonEven = new Button();
			Button buttonRoll = new Button();
			
			buttonOdd.setText("홀수"); 
			buttonEven.setText("짝수"); 
			buttonRoll.setText("Roll"); 

			buttonOdd.setStyle("-fx-font-size:30;-fx-background-color:pink");//폰트 크기,배경색
			buttonEven.setStyle("-fx-font-size:30;-fx-background-color:green");
			buttonRoll.setStyle("-fx-font-size:30");
			
			//처음 안내 텍스트 표시 
			Label text = new Label();
			text.setText("Roll을 누른 다음 홀짝  선택");
			text.setStyle("-fx-font-size:35;-fx-text-fill:blue;");	
			
			//게임머니 표시
			Label moneyText = new Label();
			moneyText.setText(Integer.toString(gameMoney)+"원");
			moneyText.setStyle("-fx-font-size:20;-fx-text-fill:blue;");
	
			//주사위 2개가 나타나는 이미지뷰어 2개
			ImageView iv1 = new ImageView();
			ImageView iv2 = new ImageView();
			
			iv1.setImage(images[5]);//처음은 '6'이 표시된 주사위 표시
            iv2.setImage(images[5]);
 	
			//홀수 버튼 눌렀을 때 판단
			buttonOdd.setOnAction(e->{
				audio.stop();;//소리 멈춘다
				//1~6까지 랜덤값 2개 얻는다
				num1 = (int)(Math.random()*6)+1;
				num2 = (int)(Math.random()*6)+1;
				int sum = num1 + num2;		//2개 합계
								
				if(sum%2==0) {//맞췄는지 판단
					text.setText("You Loose");
					gameMoney-=50000;//게임머니 -
				}
				else {
					text.setText("You Win");
					gameMoney+=50000;
				}	
				iv1.setImage(images[num1-1]);//선택한 랜덤 숫자는 0~6이고, 이미지 배열은 0~5이므로 -1해줌
	            iv2.setImage(images[num2-1]);	  
				moneyText.setText(Integer.toString(gameMoney)+"원");
				pane.getChildren().addAll(iv1, iv2, text, moneyText,buttonOdd, buttonEven, buttonRoll);
			});
			
			//짝수 버튼 눌렀을 때 판단
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
				moneyText.setText(Integer.toString(gameMoney)+"원");
				pane.getChildren().addAll(iv1, iv2, text, moneyText, buttonOdd, buttonEven, buttonRoll);
	
			});
			
			//Roll 버튼 눌렀을 때
			buttonRoll.setOnAction(e->{
				audio.play();//소리 재생
				iv1.setImage(roll);
	            iv2.setImage(roll);	
	            text.setText("홀짝중 하나를 선택하세요");
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

