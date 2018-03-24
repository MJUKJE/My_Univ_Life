package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import calcu.blocksplit;
import calcu.calnum;
import calcu.funcsplit;
import calcu.sachicsplit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CalMainController implements Initializable{
	
	
	private static double money = 0;
	private static String strin="";			//문자열 초기화
	private static String strout="";
	private static String moneyEx = "";
	@FXML
	private TextField Inx;					//입력 텍스트를 Inx로 정의
	
	@FXML
	private TextField Outx;					//출력 텍스트를 Outx로 정의
	
	private Main main;
	
	public static String getStrin()			//strin의 getter와 setter 정의
	{
		return strin;
	}
	
	public static void setStrin(String a)
	{
		strin=a;
	}
	
	public static void setStrout(String a)	//strout은 세터만 있으면 된다
	{
		strout=a;
	}
	
	/*public CalMainController()
	{
		
	}*/
	
	
	
	/*
	 * 각종 버튼을 누르거나 입력하는 등의 조작에 대응하는 메소드 부분.
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	
	
	
	//숫자 입력 버튼 부분

	@FXML
	private void oneAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "1");
		Inx.setText(strin);
	}
	@FXML
	private void twoAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "2");
		Inx.setText(strin);
	}
	@FXML
	private void threeAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "3");
		Inx.setText(strin);
	}
	@FXML
	private void fourAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "4");
		Inx.setText(strin);
	}
	@FXML
	private void fiveAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "5");
		Inx.setText(strin);
	}
	@FXML
	private void sixAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "6");
		Inx.setText(strin);
	}
	@FXML
	private void sevenAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "7");
		Inx.setText(strin);
	}
	@FXML
	private void eightAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "8");
		Inx.setText(strin);
	}
	@FXML
	private void nineAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "9");
		Inx.setText(strin);
	}
	@FXML
	private void zeroAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "0");
		Inx.setText(strin);
	}
	@FXML
	private void dotAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, ".");
		Inx.setText(strin);
	}
	
	
	
	//사칙연산 입력 버튼 부분
	
	@FXML
	private void addAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "+");
		Inx.setText(strin);
	}
	@FXML
	private void subAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "-");
		Inx.setText(strin);
	}
	@FXML
	private void mulAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "*");
		Inx.setText(strin);
	}
	@FXML
	private void divAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "/");
		Inx.setText(strin);
	}
	@FXML
	private void remAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "%");
		Inx.setText(strin);
	}
	
	
	
	
	
	//각종 함수 즉 삼각함수나 지수, 팩토리얼등 입력 버튼 함수
	
	
	@FXML
	private void sinAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "SIN(");
		Inx.setText(strin);
	}
	@FXML
	private void cosAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "COS(");
		Inx.setText(strin);
	}
	@FXML
	private void tanAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "TAN(");
		Inx.setText(strin);
	}
	@FXML
	private void sinhAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "SINH(");
		Inx.setText(strin);
	}
	@FXML
	private void coshAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "COSH(");
		Inx.setText(strin);
	}
	@FXML
	private void tanhAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "TANH(");
		Inx.setText(strin);
	}
	
	@FXML
	private void sqrtAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "SQRT(");
		Inx.setText(strin);
	}
	@FXML
	private void logAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "LOG(");
		Inx.setText(strin);
	}
	@FXML
	private void expAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "*10^");
		Inx.setText(strin);
	}
	@FXML
	private void facAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "FAC(");
		Inx.setText(strin);
	}

	
	@FXML
	private void ceAction()
	{
		strin=Inx.getText();
		strin="";
		strout="";
		Inx.setText(strin);
		Outx.setText(strout);
	}
	@FXML
	private void cAction()
	{
		strin=Inx.getText();
		strin="";								//문자열 초기화
		strout="";
		Inx.setText(strin);
		Outx.setText(strout);
	}
	

	
	@FXML
	private void deleteAction()
	{
		strin=Inx.getText();
		if(strin.length()>0)
		strin = strin.substring(0,strin.length()-1);		//문자열을 처음부터 맨뒤 직전까지 자른다.
		Inx.setText(strin);
	}
	

	
	@FXML
	private void tenpowAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "10^");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void powtwoAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "^2");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void powAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "^");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void piAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "Ø");
		Inx.setText(strin);
	}
	
	
	
	@FXML
	private void leftbAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, "(");
		Inx.setText(strin);
	}
	
	
	@FXML
	private void rightbAction()
	{
		strin=Inx.getText();
		strin=String.format("%S%S",strin, ")");
		Inx.setText(strin);
	}
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	private void typeAction()			//입력 될때마다 갱신하여 strin에 들어가도록 함
	{
		strin=Inx.getText();
	}
	
	
	 @FXML
	 public ComboBox<String> combobox;
	    
	    ObservableList<String> list = FXCollections.observableArrayList("None", "USD", "EUR", "JPY");
	    
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        combobox.setItems(list);    
	    }

	    public void comboChange(ActionEvent event) {
	        //Inx.setText(combobox.getValue());
	        //moneyEx = String.format("%s",combobox.getValue());
	    }
   


	@FXML
	private void currencyAction(ActionEvent event) {
		//  combobox.getItems();
		DecimalFormat df = new DecimalFormat("#.#");	//소숫점 둘째 자리까지만 표현한다.
	    df.setMaximumFractionDigits(2);
		
		moneyEx = combobox.getValue();
		System.out.println(moneyEx);
		money = Double.parseDouble(strin);
		switch(moneyEx)
		{
		
		case "USD":
		{
			money /= 1115;
			strout = String.format(df.format(money)+"＄");
			break;
		}
		case "EUR":
		{
			money /= 1294;
			strout = String.format(df.format(money)+"€");
			
			break;
		}
		case "JPY":
		{
			money /= 9.81;
			strout = String.format(df.format(money)+"￥");
			
			break;
		}
		default:
		{
			
			break;
		}
		
		}
		strin = String.format(strin+"￦");
		Inx.setText(strin);
		Outx.setText(strout);
		
	}
	
	
	
	
	@FXML
	private void calAction()					// '='버튼을 눌렀을때부터 계산이 시작된다.
	{
		
		strin=Inx.getText();					//strin에 입력 문자열 값을 넣어준다.
		blocksplit bs = new blocksplit(strin);	//블록 스플릿 객체 bs를 생성. 생성자함수의 인수로 strin을 준다.
		
		//모든 계산이 끝난뒤..
		
		Inx.setText(strin);						//입력문자열과 출력문자열을 표시해줌
		Outx.setText(bs.strout);				//bs.strout을 Outx에 표시해준다.

	}
}
