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
	
	public blocksplit(String a)				//strin�� �޾ƿ�
	{
		this.str = a;						//�޾ƿ� ��ǲ��Ʈ���� str�� ����
		SplitBlock();
		CalBlock();
	}
	
	public blocksplit(String a, boolean fun)				//strin�� �޾ƿ�
	{
		this.str = a;						//�޾ƿ� ��ǲ��Ʈ���� str�� ����
		SplitBlock();
		CalBlockF();
	}
	
	public blocksplit()				//strin�� �޾ƿ�
	{
		
	}
	public void SplitBlock()
	{
		boolean b = false;					//��ȣ������ �Ǵ�
		for(i = 0; i < str.length(); i++)	//��ǲ��Ʈ���� ���̸�ŭ
		{												//���ڿ��� ���̸� ��ȯ
		    char c = str.charAt(i);						//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ
		    if(b==false)								//��ϳ����� �����ڸ� �����ϱ� ����
		    {
		    		if('-'==c && i==0)
		    		{
		    			i+=1;
		    		}
		    		else
		    		{
		    			if( '+' == c || '-' == c || '*' == c || '/' == c || '%' == c || '^' == c )	//��ϳ����� ������
					    {
					    	
					    	
					    	if(str.charAt(i-1)==')')									//������ ��ȣ������
					    	{
					    		blockSachicPosition[blockscount] = i;
						    	blockSachicPart[blockscount] = String.format("%c",c);
						    	blockscount++;
					    	}
					    	else														//������ ��ȣ�� �ƴҶ�
					    	{
					    		if(blockcount==0)										//ù ���۷���  ���
					    		{
					    			blockSachicPosition[blockscount] = i;					//������ ����
							    	blockSachicPart[blockscount] = String.format("%c",c);	
							    	blockscount++;
							    	
							    	blockPart[blockcount/2] = str.substring(0,i);				//�����Ʈ�� ���� �־���	�����ǹ� �� -1 �ƴ���
					    			blockcount++;												//����ݰ�
					    			blockcount++;
					    			
					    		}
					    		else													//ù ���۷��尡 �ƴϸ�
					    		{
					    			blockSachicPart[blockscount] = String.format("%c",c);
					    			blockSachicPosition[blockscount] = i;
							    	blockscount++;
							    	
							    	blockPart[blockcount/2] = str.substring(blockSachicPosition[blockscount-2]+1, i);				//���� �����ڿ� ���翬���� ���̸� ��ϰ������� ��������
					    			blockcount++;												//����ݰ�
					    			blockcount++;
					    		}
					    	}
					    	if(i<str.length()-2)
					    	{
					    		if(str.charAt(i+1)=='-')			//������ ���ϴ� -�� ��ȣ ��Ģ�ڿ� ���̳ʽ��� �ٷο���
					    		{
					    			i=(short) (i+2);			//���̳ʽ� ��ŵ
					    		}
					    	}
					    }	//��Ģ������ ��� ��
					  // if(i==0) blockcount = (short) (blockcount+2);	�̰� �����ϳ��� �϶�
					    	//��Ģ������ �ƴҰ��
					    if(i==str.length()-1 && str.charAt(str.length()-1) != ')' && blockscount>0)	//�������� ��ȣ�� ������ ������
				    	{
					    	
				    		blockPart[blockcount/2] = str.substring(blockSachicPosition[blockscount-1]+1,i+1);	//����� +1����
				    		blockcount = (short) (blockcount + 2);


				    		
				    				
				    		
				    			
				    		
				    	}
		    		}
				    
				    	
				    
				
		    }
		    
		    //��ȣ ���� ��Ȳ
		    if( '(' == c)
		    {
		    	b=true;
		    	blockPosition[blockcount] = i;
		    	blockcount++;
		    }
		    if(b==true)		//����������
		    {
		    	 if( ')' == c )	//������
				    {
		    		 	blockPosition[blockcount] = i;		//�����ġ ����
		    		 	b=false;							//�������� �ʴ�
		    		 	blockcount++;
		    		 	blockPart[blockcount/2-1] = 
		    		 			str.substring(blockPosition[blockcount-2]+1,blockPosition[blockcount-1]);	//��Ͼ��ǰ�
		    		 	
				    }
		    }
		}
		if(blockcount==0)	//��ȣ�� �ϳ��� �������
		{
			blockPart[0] = str;	//÷���� ������
		}
		
		
		
	}
	
	public void CalBlock()
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
