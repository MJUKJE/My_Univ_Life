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
	private static String strin="";			//���ڿ� �ʱ�ȭ
	private static String strout="";
	private static String moneyEx = "";
	@FXML
	private TextField Inx;					//�Է� �ؽ�Ʈ�� Inx�� ����
	
	@FXML
	private TextField Outx;					//��� �ؽ�Ʈ�� Outx�� ����
	
	private Main main;
	
	public static String getStrin()			//strin�� getter�� setter ����
	{
		return strin;
	}
	
	public static void setStrin(String a)
	{
		strin=a;
	}
	
	public static void setStrout(String a)	//strout�� ���͸� ������ �ȴ�
	{
		strout=a;
	}
	
	/*public CalMainController()
	{
		
	}*/
	
	
	
	/*
	 * ���� ��ư�� �����ų� �Է��ϴ� ���� ���ۿ� �����ϴ� �޼ҵ� �κ�.
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	
	
	
	//���� �Է� ��ư �κ�

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
	
	
	
	//��Ģ���� �Է� ��ư �κ�
	
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
	
	
	
	
	
	//���� �Լ� �� �ﰢ�Լ��� ����, ���丮��� �Է� ��ư �Լ�
	
	
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
		strin="";								//���ڿ� �ʱ�ȭ
		strout="";
		Inx.setText(strin);
		Outx.setText(strout);
	}
	

	
	@FXML
	private void deleteAction()
	{
		strin=Inx.getText();
		if(strin.length()>0)
		strin = strin.substring(0,strin.length()-1);		//���ڿ��� ó������ �ǵ� �������� �ڸ���.
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
		strin=String.format("%S%S",strin, "��");
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
	private void typeAction()			//�Է� �ɶ����� �����Ͽ� strin�� ������ ��
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
		DecimalFormat df = new DecimalFormat("#.#");	//�Ҽ��� ��° �ڸ������� ǥ���Ѵ�.
	    df.setMaximumFractionDigits(2);
		
		moneyEx = combobox.getValue();
		System.out.println(moneyEx);
		money = Double.parseDouble(strin);
		switch(moneyEx)
		{
		
		case "USD":
		{
			money /= 1115;
			strout = String.format(df.format(money)+"��");
			break;
		}
		case "EUR":
		{
			money /= 1294;
			strout = String.format(df.format(money)+"��");
			
			break;
		}
		case "JPY":
		{
			money /= 9.81;
			strout = String.format(df.format(money)+"��");
			
			break;
		}
		default:
		{
			
			break;
		}
		
		}
		strin = String.format(strin+"��");
		Inx.setText(strin);
		Outx.setText(strout);
		
	}
	
	
	
	
	@FXML
	private void calAction()					// '='��ư�� ������������ ����� ���۵ȴ�.
	{
		
		strin=Inx.getText();					//strin�� �Է� ���ڿ� ���� �־��ش�.
		blocksplit bs = new blocksplit(strin);	//��� ���ø� ��ü bs�� ����. �������Լ��� �μ��� strin�� �ش�.
		
		//��� ����� ������..
		
		Inx.setText(strin);						//�Է¹��ڿ��� ��¹��ڿ��� ǥ������
		Outx.setText(bs.strout);				//bs.strout�� Outx�� ǥ�����ش�.

	}
}
