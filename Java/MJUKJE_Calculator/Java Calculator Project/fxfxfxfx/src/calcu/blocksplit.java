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
	
	public blocksplit(String strin)									//strin�� �޾ƿ�
	{
		f=false;	//���
		for(i = 0; i < strin.length(); i++)							//��ǲ��Ʈ���� ���̸�ŭ
		{															//���ڿ��� ���̸� ��ȯ
		    char c = strin.charAt(i);								//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ	
		    if(i!=0 && (c == 'I' &&  strin.charAt(i-1) != 'S'))		//I�� �ִµ� SIN���� �ƴҋ�
		    {
		    	f=true;
		    	this.strin = strin;
		    }
		}
		
		if(f==false)	//���ڿ��� ����� ������
		{
			funcsplit fs = new funcsplit(strin);
			this.strin = fs.strout;						//�޾ƿ� ��ǲ��Ʈ���� str�� ����
		}
		
		
		SplitBlock();
	}
	
	public void SplitBlock()
	{
		
		DecimalFormat df = new DecimalFormat("#.#");	//�Ҽ��� �ڸ��� ������ e��¼�� �ȳ����� �ϴ°�
        df.setMaximumFractionDigits(40);
		
		d=true;
		while(d==true)
		{
			blockcount=0;
			d=false;
			for(i = 0; i < strin.length(); i++)				//��ǲ��Ʈ���� ���̸�ŭ
			{												//���ڿ��� ���̸� ��ȯ
			    char c = strin.charAt(i);						//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ						
			    if(c == '(')	//���ĺ��϶�
			    {
			    	d=true;
			    	
			    	blockPosition[blockcount] = i;		//���ۺκ��ڸ��� ����
			    	blockcount++;						//ī��Ʈ �ø���
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
				    		blockcount++;		//ī��Ʈ �ø���
					    	
				    		blockPart[blockcount/2-1] = strin.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]);	//�Լ� �� ��ȣ���ڸ��� ����
					    	//System.out.println(blockPart[blockcount/2-1]+"block ���� ����Ǵ����� ����!");				//
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
		if(blockcount>0)					//����� ������
		{
			for(i=0;i<blockcount/2;i++)		//����� ������ŭ
			{
				b="";
				sachicsplit bss = new sachicsplit(blockPart[i]);	//��Ͼ��� �����ڿ� �ǿ����ڷ� ����
				
				b = calnum.Calculate(bss);							//���� ���
				
				if(i<blockcount/2-1)
					strout = String.format("%S%S%S",strout, b,blockSachicPart[i]);
				else
					strout = String.format("%S%S",strout, b);
				System.out.println(strout+"�ٹٲ�2");
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
		if(blockcount>0)					//����� ������
		{
			for(i=0;i<blockcount/2;i++)		//����� ������ŭ
			{
				b="";
				sachicsplit bss = new sachicsplit(blockPart[i]);	//��Ͼ��� �����ڿ� �ǿ����ڷ� ����
				
				b = calnum.Calculate(bss);							//���� ���
				
				if(i<blockcount/2-1)
					strout = String.format("%S%S%S",strout, b,blockSachicPart[i]);
				else
					strout = String.format("%S%S",strout, b);
				System.out.println(strout+"�ٹٲ�2");
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