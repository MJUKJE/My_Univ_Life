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
	
	
	public funcsplit(String strin)				//strin을 받아옴
	{
		this.str = strin;						//받아온 인풋스트링을 str에 넣음
		SplitFunc();
	}
	
	public void SplitFunc()
	{
		for(i = 0; i < str.length(); i++)				//인풋스트링의 길이만큼
		{												//문자열의 길이를 반환
		    char c = str.charAt(i);						//해당 문자열의 해당 자릿수의 문자를 반환
		    System.out.println(c); 								
		    if( (c == 'S' || c <= 'z') && fin==false)	//알파벳일때
		    {
		    	fin=true;							//함수시작을 알리고
		    	funcPosition[funccount] = i;		//시작부분자리를 저장
		    	funccount++;						//카운트 올리고
		    }
		    if( (c == '(' || c == ')') && fin==true)	//알파벳일때
		    {
		    	if(c == '(')
		    	{
		    		funcPosition[funccount] = i;		//시작부분자리를 저장
			    	funccount++;	
		    	}
		    	
		    	else									//괄호가 닫힐때
		    	{
		    		fin=false;							//함수시작을 알리고
			    	funcPosition[funccount] = i;		//시작부분자리를 저장
			    	funccount++;						//카운트 올리고
			    	funcPart[funccount/3-1] = str.substring(funcPosition[funccount-3],funcPosition[funccount-1]+1);
			    	System.out.println(funcPart[funccount/3-1]);
			    	blocksplit bs = new blocksplit();
			    	bs.str = funcPart[funccount/3-1];
			    	bs.SplitBlock();
					bs.CalBlockF();
			    	num=0;
			    	num = Double.parseDouble(bs.strout);	//함수뒤 괄호안 계산한 값
			    	 DecimalFormat df = new DecimalFormat("#.#");	//소숫점 자리로 나오게 e어쩌고 안나오게 하는것
			         df.setMaximumFractionDigits(8);
			         
			    	System.out.println(str.substring(funcPosition[funccount-3], funcPosition[funccount-2])+"이건 함수부분");
			    	switch( str.substring(funcPosition[funccount-3], funcPosition[funccount-2]) )	//알파벳 부터 ( 앞까지 즉 알파벳만
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
			    	if(funcPosition[0]==0)	//첫글자가 함수일땐
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
