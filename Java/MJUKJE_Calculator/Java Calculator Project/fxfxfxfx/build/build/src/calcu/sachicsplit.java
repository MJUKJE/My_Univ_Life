package calcu;

public class sachicsplit
{
	
	short[] sachicPosition = new short[30];			//문자열의 몇번째 자리에 연산기호가 있는지 저장
	public short i, ib, count=0;					//count는 연산기호의 갯수를 세주는 변수
	public String[] numberPart = new String[31];	//연산기호로 나눠진 수들을 담는 배열
	public char[] sachicPart = new char[30];		//연산기호를 저장한다
	
	
	public sachicsplit(String a)
	{
		Splitsachic(a);
	}
	
	
	
	public void Splitsachic(String a)
	{
		for(i = 0; i < a.length(); i++)
		{												//문자열의 길이를 반환
		    char c = a.charAt(i);						//해당 문자열의 해당 자릿수의 문자를 반환
		    if( '+' == c || '-' == c || '*' == c || '/' == c || '%' == c || '^' == c)	//괄호는 따로 하자
		    {
		    	if('-'==c && i==0)
	    		{
	    			i+=1;
	    		}
		    	else
		    	{
		    		sachicPosition[count] = i;				//연산자의 위치저장
			    	sachicPart[count] = c;					//연산자를 저장
			    	
			    	
			    		if(count==0)						//첫 연산자일때
					    {
					    	numberPart[count] = a.substring(0,(sachicPosition[count]));		//숫자부분은 처음부터 현재i까지 //띄어쓰기 없을때
					    	numberPart[count+1] = a.substring((sachicPosition[count]+1));						//마지막을 위한것
					    }
					    else
					    {
					  
					    	numberPart[count] = a.substring(sachicPosition[count-1]+1,sachicPosition[count]);	//띄어쓰기 없으면 +1
					    	
					    		numberPart[count+1] = a.substring((sachicPosition[count]+1));						//마지막을 위한것
					    	
					    }
			    	 count++;
			    	 
			    	if(i<a.length()-2)
			    	{
			    		if(a.charAt(i+1)=='-')			//음수를 뜻하는 -를 보호 사칙뒤에 마이너스가 바로오면
			    		{
			    			i=(short) (i+2);			//마이너스 스킵
			    		}
			    	}
		    	}
		    	
		    	
		    }
		}
		if(count == 0)			//연산자 암것도 없을때
		{
			numberPart[0] = a;	//문자열 그대로
		}
		else
		{
			for(i=0;i<=count;i++)
			{
				System.out.println(numberPart[i]+"줄바꿈");	//넘버파트
				if(i!=count)
				System.out.println(sachicPart[i]+"줄바꿈");	//사칙은 마지막은 찍지않아
			}
		}
	}
}
