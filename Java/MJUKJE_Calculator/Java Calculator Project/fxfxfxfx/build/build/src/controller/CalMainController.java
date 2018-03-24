package controller;

import calcu.blocksplit;
import calcu.calnum;
import calcu.funcsplit;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CalMainController {
	
	private static String strin="";
	private static String strout="";
	@FXML
	private TextField Inx;
	
	@FXML
	private TextField Outx;
	
	private Main main;
	
	public static String getStrin()	//str을 얻기위한 메소드
	{
		return strin;
	}
	
	public static void setStrin(String a)
	{
		strin=a;
	}
	
	public static void setStrout(String a)
	{
		strout=a;
	}
	
	public CalMainController()
	{
		
	}
	@FXML
	private void oneAction()
	{
		strin=String.format("%S%S",strin, "1");
		Inx.setText(strin);
	}
	@FXML
	private void twoAction()
	{
		strin=String.format("%S%S",strin, "2");
		Inx.setText(strin);
	}
	@FXML
	private void threeAction()
	{
		strin=String.format("%S%S",strin, "3");
		Inx.setText(strin);
	}
	@FXML
	private void fourAction()
	{
		strin=String.format("%S%S",strin, "4");
		Inx.setText(strin);
	}
	@FXML
	private void fiveAction()
	{
		strin=String.format("%S%S",strin, "5");
		Inx.setText(strin);
	}
	@FXML
	private void sixAction()
	{
		strin=String.format("%S%S",strin, "6");
		Inx.setText(strin);
	}
	@FXML
	private void sevenAction()
	{
		strin=String.format("%S%S",strin, "7");
		Inx.setText(strin);
	}
	@FXML
	private void eightAction()
	{
		strin=String.format("%S%S",strin, "8");
		Inx.setText(strin);
	}
	@FXML
	private void nineAction()
	{
		strin=String.format("%S%S",strin, "9");
		Inx.setText(strin);
	}
	@FXML
	private void zeroAction()
	{
		strin=String.format("%S%S",strin, "0");
		Inx.setText(strin);
	}
	@FXML
	private void addAction()
	{
		strin=String.format("%S%S",strin, "+");
		Inx.setText(strin);
	}
	@FXML
	private void subAction()
	{
		strin=String.format("%S%S",strin, "-");
		Inx.setText(strin);
	}
	@FXML
	private void mulAction()
	{
		strin=String.format("%S%S",strin, "*");
		Inx.setText(strin);
	}
	@FXML
	private void divAction()
	{
		strin=String.format("%S%S",strin, "/");
		Inx.setText(strin);
	}
	@FXML
	private void remAction()
	{
		strin=String.format("%S%S",strin, "%");
		Inx.setText(strin);
	}
	
	
	@FXML
	private void dotAction()
	{
		strin=String.format("%S%S",strin, ".");
		Inx.setText(strin);
	}
	
	
	
	
	
	
	@FXML
	private void facAction()
	{
		strin=String.format("%S%S",strin, "FAC(");
		Inx.setText(strin);
	}
	
	
	@FXML
	private void sinAction()
	{
		strin=String.format("%S%S",strin, "SIN(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void cosAction()
	{
		strin=String.format("%S%S",strin, "COS(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void tanAction()
	{
		strin=String.format("%S%S",strin, "TAN(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void sinhAction()
	{
		strin=String.format("%S%S",strin, "SINH(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void coshAction()
	{
		strin=String.format("%S%S",strin, "COSH(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void tanhAction()
	{
		strin=String.format("%S%S",strin, "TANH(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void sqrtAction()
	{
		strin=String.format("%S%S",strin, "SQRT(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void logAction()
	{
		strin=String.format("%S%S",strin, "LOG(");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void expAction()
	{
		strin=String.format("%S%S",strin, "*10^");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void ceAction()
	{
		strin="";
		strout="";
		Inx.setText(strin);
		Outx.setText(strout);
	}
	

	
	@FXML
	private void cAction()
	{
		strin="";								//문자열 초기화
		strout="";
		Inx.setText(strin);
		Outx.setText(strout);
	}
	

	
	@FXML
	private void deleteAction()
	{
		if(strin.length()>0)
		strin = strin.substring(0,strin.length()-1);		//문자열을 처음부터 맨뒤 직전까지 자른다.
		Inx.setText(strin);
	}
	

	
	@FXML
	private void tenpowAction()
	{
		strin=String.format("%S%S",strin, "10^");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void powtwoAction()
	{
		strin=String.format("%S%S",strin, "^2");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void powAction()
	{
		strin=String.format("%S%S",strin, "^");
		Inx.setText(strin);
	}
	

	
	@FXML
	private void piAction()
	{
		strin=String.format("%S%S",strin, "3.141592");
		Inx.setText(strin);
	}
	
	
	
	@FXML
	private void leftbAction()
	{
		strin=String.format("%S%S",strin, "(");
		Inx.setText(strin);
	}
	
	
	@FXML
	private void rightbAction()
	{
		strin=String.format("%S%S",strin, ")");
		Inx.setText(strin);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	private void calAction()
	{
		funcsplit fs = new funcsplit(strin);
		blocksplit bs = new blocksplit(strin);
		//bs.str = strin;
		//bs.SplitBlock();
		//bs.CalBlock();
		Inx.setText(strin);
		Outx.setText(strout);
		//System.out.println(Math.exp(2));
	}
}
