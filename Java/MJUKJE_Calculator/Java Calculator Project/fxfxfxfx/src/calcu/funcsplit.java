package calcu;

import java.text.DecimalFormat;

public class funcsplit {
	private String strin = "";
	public String strout = "";
	
	boolean fin = false;		//함수 속인지
	
	short[] funcPosition = new short[30];
	String[] funcPart = new String[10];
	short i, funccount=0;
	int len;
	double num;
	boolean d = true;
	
	
	public funcsplit(String strin)				//strin을 받아옴
	{
		this.strin = strin;						//받아온 인풋스트링을 str에 넣음
		SplitFunc();
	}
	
	public void SplitFunc()
	{
		DecimalFormat df = new DecimalFormat("#.#");	//소숫점 자리로 나오게 e어쩌고 안나오게 하는것
        df.setMaximumFractionDigits(40);
        
		d=true;
		while(d==true)
		{
			fin = false;
			d=false;
			for(i = 0; i < strin.length(); i++)				//인풋스트링의 길이만큼
			{												//문자열의 길이를 반환
			    char c = strin.charAt(i);						//해당 문자열의 해당 자릿수의 문자를 반환						
			    if( fin==false && (c == 'S' || c == 'C' || c == 'T' || c == 'L') )	//알파벳일때
			    {
			    	d=true;
			    	fin=true;							//함수시작을 알리고
			    	funcPosition[funccount] = i;		//시작부분자리를 저장
			    	funccount++;						//카운트 올리고
			    }
			    if(fin==true && (c == '(' || c == ')') )	//알파벳일때
			    {
			    	if(c == '(')
			    	{
			    		funcPosition[funccount] = i;		//시작부분자리를 저장
				    	funccount++;	
			    	}
			    	
			    	else									//괄호가 닫힐때
			    	{
			    		fin=false;							//함수 끝
				    	funcPosition[funccount] = i;		
				    	funccount++;		//카운트 올리고
				    	
				    	funcPart[funccount/3-1] = strin.substring(funcPosition[funccount-2]+1,funcPosition[funccount-1]);	//함수 뒤 괄호안자리를 넣음
				    	//System.out.println(funcPart[funccount/3-1]+"펑션 어케 저장되는지좀 보자!");				//
				    	calnum cn = new calnum(funcPart[funccount/3-1]);
				    	//num=0;
				    	num = Double.parseDouble(cn.strout);
				    	 
				         
				    	//System.out.println(strin.substring(funcPosition[funccount-3], funcPosition[funccount-2])+"이건 함수부분");
				    	//System.out.println("tlqkf");
				    	
				    	switch( strin.substring(funcPosition[funccount-3], funcPosition[funccount-2]) )	//알파벳 부터 ( 앞까지 즉 알파벳만
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
				    	strin = String.format("%s%s%s", strin.substring(0,funcPosition[funccount-3]) , df.format(num) , strin.substring(funcPosition[funccount-1]+1));
				    	//System.out.println(strin);
				    	i=(short) strin.length();
				    	//break;
				    	
			    	}
			    }
			}
			
		}
		
    	strout = strin;
		
	}
}
