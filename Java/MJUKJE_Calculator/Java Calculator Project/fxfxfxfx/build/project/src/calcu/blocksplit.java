package calcu;

import controller.CalMainController;

public class blocksplit
{
	String[] blockPart = new String[10];
	short[] blockPosition = new short[20];
	short[] blockSachicPosition = new short[19];
	String[] blockSachicPart = new String[19];
	short i, ib,  blockcount = 0, blockscount = 0;
	
	public String str, strout="";
	
	public blocksplit(String a)				//strin을 받아옴
	{
		this.str = a;						//받아온 인풋스트링을 str에 넣음
		SplitBlock();
		CalBlock();
	}
	
	public blocksplit(String a, boolean fun)				//strin을 받아옴
	{
		this.str = a;						//받아온 인풋스트링을 str에 넣음
		SplitBlock();
		CalBlockF();
	}
	
	public blocksplit()				//strin을 받아옴
	{
		
	}
	public void SplitBlock()
	{
		boolean b = false;					//괄호안인지 판단
		for(i = 0; i < str.length(); i++)	//인풋스트링의 길이만큼
		{												//문자열의 길이를 반환
		    char c = str.charAt(i);						//해당 문자열의 해당 자릿수의 문자를 반환
		    if(b==false)								//블록끼리의 연산자를 저장하기 위해
		    {
		    		if('-'==c && i==0)
		    		{
		    			i+=1;
		    		}
		    		else
		    		{
		    			if( '+' == c || '-' == c || '*' == c || '/' == c || '%' == c || '^' == c )	//블록끼리의 연산자
					    {
					    	
					    	
					    	if(str.charAt(i-1)==')')									//직전이 괄호였을때
					    	{
					    		blockSachicPosition[blockscount] = i;
						    	blockSachicPart[blockscount] = String.format("%c",c);
						    	blockscount++;
					    	}
					    	else														//직전이 괄호가 아닐때
					    	{
					    		if(blockcount==0)										//첫 오퍼랜드  라면
					    		{
					    			blockSachicPosition[blockscount] = i;					//연산자 저장
							    	blockSachicPart[blockscount] = String.format("%c",c);	
							    	blockscount++;
							    	
							    	blockPart[blockcount/2] = str.substring(0,i);				//블록파트에 수를 넣어줌	여기의문 왜 -1 아닌지
					    			blockcount++;												//열고닫고
					    			blockcount++;
					    			
					    		}
					    		else													//첫 오퍼랜드가 아니면
					    		{
					    			blockSachicPart[blockscount] = String.format("%c",c);
					    			blockSachicPosition[blockscount] = i;
							    	blockscount++;
							    	
							    	blockPart[blockcount/2] = str.substring(blockSachicPosition[blockscount-2]+1, i);				//이전 연산자와 현재연산자 사이를 블록개념으로 생각해줌
					    			blockcount++;												//열고닫고
					    			blockcount++;
					    		}
					    	}
					    	if(i<str.length()-2)
					    	{
					    		if(str.charAt(i+1)=='-')			//음수를 뜻하는 -를 보호 사칙뒤에 마이너스가 바로오면
					    		{
					    			i=(short) (i+2);			//마이너스 스킵
					    		}
					    	}
					    }	//사칙연산인 경우 끝
					  // if(i==0) blockcount = (short) (blockcount+2);	이건 숫자하나만 일때
					    	//사칙연산이 아닐경우
					    if(i==str.length()-1 && str.charAt(str.length()-1) != ')' && blockscount>0)	//마지막이 괄호로 끝나지 않을때
				    	{
					    	
				    		blockPart[blockcount/2] = str.substring(blockSachicPosition[blockscount-1]+1,i+1);	//여긴왜 +1인지
				    		blockcount = (short) (blockcount + 2);


				    		
				    				
				    		
				    			
				    		
				    	}
		    		}
				    
				    	
				    
				
		    }
		    
		    //괄호 안의 상황
		    if( '(' == c)
		    {
		    	b=true;
		    	blockPosition[blockcount] = i;
		    	blockcount++;
		    }
		    if(b==true)		//열려있을때
		    {
		    	 if( ')' == c )	//닫히면
				    {
		    		 	blockPosition[blockcount] = i;		//블록위치 저장
		    		 	b=false;							//열려있지 않다
		    		 	blockcount++;
		    		 	blockPart[blockcount/2-1] = 
		    		 			str.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]);	//블록안의것
		    		 	
				    }
		    }
		}
		if(blockcount==0)	//괄호가 하나도 없을경우
		{
			blockPart[0] = str;	//첨부터 끝까지
		}
		
		
		
	}
	
	public void CalBlock()
	{
		String b = "";
		if(blockcount>0)					//블록이 있을때
		{
			for(i=0;i<blockcount/2;i++)		//블록의 갯수만큼
			{
				b="";
				sachicsplit bss = new sachicsplit(blockPart[i]);	//블록안을 연산자와 피연산자로 나눔
				
				b = calnum.Calculate(bss);							//그후 계산
				
				if(i<blockcount/2-1)
					strout = String.format("%S%S%S",strout, b,blockSachicPart[i]);
				else
					strout = String.format("%S%S",strout, b);
				System.out.println(strout+"줄바꿈2");
				bss = null;
			}
		}
		else
		{
			strout = blockPart[0];
		}
		sachicsplit bbss = new sachicsplit(strout);
		
		strout = calnum.Calculate(bbss);
		CalMainController.setStrout(strout);
		
	}
	
	public void CalBlockF()
	{
		String b = "";
		if(blockcount>0)					//블록이 있을때
		{
			for(i=0;i<blockcount/2;i++)		//블록의 갯수만큼
			{
				b="";
				sachicsplit bss = new sachicsplit(blockPart[i]);	//블록안을 연산자와 피연산자로 나눔
				
				b = calnum.Calculate(bss);							//그후 계산
				
				if(i<blockcount/2-1)
					strout = String.format("%S%S%S",strout, b,blockSachicPart[i]);
				else
					strout = String.format("%S%S",strout, b);
				System.out.println(strout+"줄바꿈2");
				bss = null;
			}
		}
		else
		{
			strout = blockPart[0];
		}
		sachicsplit bbss = new sachicsplit(strout);
		
		strout = calnum.Calculate(bbss);
		//CalMainController.setStrout(strout);
		
	}
}
