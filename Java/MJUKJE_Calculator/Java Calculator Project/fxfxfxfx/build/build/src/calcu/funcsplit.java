package calcu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.text.*;

import controller.CalMainController;

public class funcsplit {
	String str;
	
	boolean fin = false;
	short[] funcPosition = new short[30];
	String[] funcPart = new String[10];
	short i, funccount=0;
	int len;
	double num;
	
	
	public funcsplit(String strin)				//strin�� �޾ƿ�
	{
		this.str = strin;						//�޾ƿ� ��ǲ��Ʈ���� str�� ����
		SplitFunc();
	}
	
	public void SplitFunc()
	{
		for(i = 0; i < str.length(); i++)				//��ǲ��Ʈ���� ���̸�ŭ
		{												//���ڿ��� ���̸� ��ȯ
		    char c = str.charAt(i);						//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ
		    System.out.println(c); 								
		    if( (c == 'S' || c <= 'z') && fin==false)	//���ĺ��϶�
		    {
		    	fin=true;							//�Լ������� �˸���
		    	funcPosition[funccount] = i;		//���ۺκ��ڸ��� ����
		    	funccount++;						//ī��Ʈ �ø���
		    }
		    if( (c == '(' || c == ')') && fin==true)	//���ĺ��϶�
		    {
		    	if(c == '(')
		    	{
		    		funcPosition[funccount] = i;		//���ۺκ��ڸ��� ����
			    	funccount++;	
		    	}
		    	
		    	else									//��ȣ�� ������
		    	{
		    		fin=false;							//�Լ������� �˸���
			    	funcPosition[funccount] = i;		//���ۺκ��ڸ��� ����
			    	funccount++;						//ī��Ʈ �ø���
			    	funcPart[funccount/3-1] = str.substring(funcPosition[funccount-3],funcPosition[funccount-1]+1);
			    	System.out.println(funcPart[funccount/3-1]);
			    	blocksplit bs = new blocksplit();
			    	bs.str = funcPart[funccount/3-1];
			    	bs.SplitBlock();
					bs.CalBlockF();
			    	num=0;
			    	num = Double.parseDouble(bs.strout);	//�Լ��� ��ȣ�� ����� ��
			    	 DecimalFormat df = new DecimalFormat("#.#");	//�Ҽ��� �ڸ��� ������ e��¼�� �ȳ����� �ϴ°�
			         df.setMaximumFractionDigits(8);
			         
			    	System.out.println(str.substring(funcPosition[funccount-3], funcPosition[funccount-2])+"�̰� �Լ��κ�");
			    	switch( str.substring(funcPosition[funccount-3], funcPosition[funccount-2]) )	//���ĺ� ���� ( �ձ��� �� ���ĺ���
			    	{
			    	case "SIN":
			    	{
			    		num = Math.sin(num);
			    		break;
			    	}
			    	case "COS":
			    	{
			    		num = Math.cos(num);
			    		break;
			    	}
			    	case "TAN":
			    	{
			    		num = Math.tan(num);
			    		break;
			    	}
			    	
			    	case "SINH":
			    	{
			    		num = Math.sinh(num);
			    		break;
			    	}
			    	case "COSH":
			    	{
			    		num = Math.cosh(num);
			    		break;
			    	}
			    	case "TANH":
			    	{
			    		num = Math.tanh(num);
			    		break;
			    	}
			    	
			    	case "SQRT":
			    	{
			    		num = Math.sqrt(num);
			    		break;
			    	}
			    	case "LOG":
			    	{
			    		num = Math.log(num);
			    		break;
			    	}
			    	case "FAC":
			    	{
			    		double temp=1;
			    		int i;
			    		for(i=1;i<=num;i++)
			    		{
			    			temp*=i;
			    		}
			    		num = temp;
			    		break;
			    	}
			    	
			    	}
			    	
			    	len = str.length();
			    	if(funcPosition[0]==0)	//ù���ڰ� �Լ��϶�
			    	{
			    		
			    		str = String.format("%S%S", df.format(num) ,  str.substring(funcPosition[funccount-1]+1) );
			    		i = (short) (i - (len - str.length()));
			    	}
			    	else
			    	{
				    	str = String.format("%S%S%S",str.substring(0,funcPosition[funccount-3]-1)   ,df.format(num) ,  str.substring(funcPosition[funccount-1]+1) );
				    	i = (short) (i - (len - str.length()));
			    	}
			    	
			    	CalMainController.setStrin(str);
					
		    	}
				
		    }
		    
		    
		}
		
	}
}
