package calcu;

import java.text.DecimalFormat;

public class blocksplit
{
	boolean d = true, bin=false, f=false;
	double num = 0;
	String[] blockPart = new String[10];
	short[] blockPosition = new short[20];
	short i,  blockcount = 0, ic = 0;
	String[] iPart = new String[2];
	String[] rPart = new String[2];
	short[] isachicPosition = new short[3];	
	public char[] isachicPart = new char[3];
	
	private String strin = "";
	public String strout = "";
	
	public blocksplit(String strin)									//strin을 받아옴
	{
		f=false;	//허수
		for(i = 0; i < strin.length(); i++)							//인풋스트링의 길이만큼
		{															//문자열의 길이를 반환
		    char c = strin.charAt(i);								//해당 문자열의 해당 자릿수의 문자를 반환	
		    if(i!=0 && (c == 'I' &&  strin.charAt(i-1) != 'S'))		//I가 있는데 SIN꺼가 아닐떄
		    {
		    	f=true;
		    	this.strin = strin;
		    }
		}
		
		if(f==false)	//문자열에 허수가 있을때
		{
			funcsplit fs = new funcsplit(strin);
			this.strin = fs.strout;						//받아온 인풋스트링을 str에 넣음
		}
		
		
		SplitBlock();
	}
	
	public void SplitBlock()
	{
		
		DecimalFormat df = new DecimalFormat("#.#");	//소숫점 자리로 나오게 e어쩌고 안나오게 하는것
        df.setMaximumFractionDigits(40);
		
		d=true;
		while(d==true)
		{
			blockcount=0;
			d=false;
			for(i = 0; i < strin.length(); i++)				//인풋스트링의 길이만큼
			{												//문자열의 길이를 반환
			    char c = strin.charAt(i);						//해당 문자열의 해당 자릿수의 문자를 반환						
			    if(c == '(')	//알파벳일때
			    {
			    	d=true;
			    	
			    	blockPosition[blockcount] = i;		//시작부분자리를 저장
			    	blockcount++;						//카운트 올리고
			    }
			    if(c == ')')
			    {
			    	 if(strin.charAt(i-1) == 'I')
						{
			    		 	blockPosition[blockcount] = i;		
				    		blockcount++;
			    		 	//iPart[blockcount/2-1] = strin.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]);
					    	sachicsplit isp = new sachicsplit(strin.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]-1));
					    	for(ic=0;ic<3;ic++)
					    	{
					    		isachicPart[ic] = isp.sachicPart[ic];
					    		isachicPosition[ic] = isp.sachicPosition[ic];
					    
					    	}
					    	
					    		rPart[0] = strin.substring(1,isachicPosition[0]+1);
					    		rPart[1] = strin.substring(isachicPosition[1]+2,isachicPosition[2]+1);
					    		iPart[0] = strin.substring(isachicPosition[0]+2,isachicPosition[1]);
					    		iPart[1] = strin.substring(isachicPosition[2]+2,strin.length()-2);
					    		//System.out.printf("%s\n%s\n%s\n%s\n",rPart[0],iPart[0],rPart[1],iPart[1]);
					    		switch(isachicPart[1])
					    		{
					    		case '+':
					    		{
					    			strout = String.format("%s+%si", Double.parseDouble(rPart[0])+Double.parseDouble(rPart[1]), Double.parseDouble(iPart[0])+Double.parseDouble(iPart[1]));
					    			break;
					    		}
					    		case '-':
					    		{
					    			strout = String.format("%s+%si", Double.parseDouble(rPart[0])-Double.parseDouble(rPart[1]), Double.parseDouble(iPart[0])-Double.parseDouble(iPart[1]));
					    			break;
					    		}
					    		case '*':
					    		{
					    			strout = String.format("%s+%si", (  Double.parseDouble(rPart[0])*Double.parseDouble(rPart[1])- Double.parseDouble(iPart[0])*Double.parseDouble(iPart[1]) )
					    					, Double.parseDouble(rPart[0])*Double.parseDouble(iPart[1]) + Double.parseDouble(rPart[1])*Double.parseDouble(iPart[0]));
					    			break;
					    		}
					    		case '/':
					    		{
					    			strout = String.format("%s+%si",(( Double.parseDouble(rPart[0])*Double.parseDouble(rPart[1])+
					    					Double.parseDouble(iPart[0])*Double.parseDouble(iPart[1]))/
					    					( Math.pow(Double.parseDouble(rPart[1]),2)+Math.pow(Double.parseDouble(iPart[1]),2))),
					    					((Double.parseDouble(rPart[1])*Double.parseDouble(iPart[0])-
					    							Double.parseDouble(rPart[0])*Double.parseDouble(iPart[1])))/
					    							(( Math.pow(Double.parseDouble(rPart[1]),2)+Math.pow(Double.parseDouble(iPart[1]),2))));
					    			break;
					    		}
					    		}
					    		
								//(12+34I+56+78I)
					    	
					    	d=false;
						}   
				    
			    	 else
			    	 {
			    		 blockPosition[blockcount] = i;		
				    		blockcount++;		//카운트 올리고
					    	
				    		blockPart[blockcount/2-1] = strin.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]);	//함수 뒤 괄호안자리를 넣음
					    	//System.out.println(blockPart[blockcount/2-1]+"block 어케 저장되는지좀 보자!");				//
					    	calnum cn = new calnum(blockPart[blockcount/2-1]);
					    	num=0;
					    	num = Double.parseDouble(cn.strout);
					    	 
					        
					    	
					    	strin = String.format("%s%s%s", strin.substring(0,blockPosition[blockcount-2]) , df.format(num) , strin.substring(blockPosition[blockcount-1]+1));
					    	//System.out.println(strin);
					    	//i=(short) strin.length();
					    	break;
			    	 }
		    	}
			   
			}
				
		}
		
		if(f==false)
		{
			calnum cn = new calnum(strin);
	    	strout = cn.strout;
		}
		
		
	
		
	}
}
	/*
	public void CalBlock()
	{
		String b = "";
		strout = "";
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
*/