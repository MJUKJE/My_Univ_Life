package calcu;

public class sachicsplit
{
	
	short[] sachicPosition = new short[30];			//���ڿ��� ���° �ڸ��� �����ȣ�� �ִ��� ����
	public short i, ib, count=0;					//count�� �����ȣ�� ������ ���ִ� ����
	public String[] numberPart = new String[31];	//�����ȣ�� ������ ������ ��� �迭
	public char[] sachicPart = new char[30];		//�����ȣ�� �����Ѵ�
	
	
	public sachicsplit(String a)
	{
		Splitsachic(a);
	}
	
	
	
	public void Splitsachic(String a)
	{
		for(i = 0; i < a.length(); i++)
		{												//���ڿ��� ���̸� ��ȯ
		    char c = a.charAt(i);						//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ
		    if( '+' == c || '-' == c || '*' == c || '/' == c || '%' == c || '^' == c)	//��ȣ�� ���� ����
		    {
		    	if('-'==c && i==0)
	    		{
	    			i+=1;
	    		}
		    	else
		    	{
		    		sachicPosition[count] = i;				//�������� ��ġ����
			    	sachicPart[count] = c;					//�����ڸ� ����
			    	
			    	
			    		if(count==0)						//ù �������϶�
					    {
					    	numberPart[count] = a.substring(0,(sachicPosition[count]));		//���ںκ��� ó������ ����i���� //���� ������
					    	numberPart[count+1] = a.substring((sachicPosition[count]+1));						//�������� ���Ѱ�
					    }
					    else
					    {
					  
					    	numberPart[count] = a.substring(sachicPosition[count-1]+1,sachicPosition[count]);	//���� ������ +1
					    	
					    		numberPart[count+1] = a.substring((sachicPosition[count]+1));						//�������� ���Ѱ�
					    	
					    }
			    	 count++;
			    	 
			    	if(i<a.length()-2)
			    	{
			    		if(a.charAt(i+1)=='-')			//������ ���ϴ� -�� ��ȣ ��Ģ�ڿ� ���̳ʽ��� �ٷο���
			    		{
			    			i=(short) (i+2);			//���̳ʽ� ��ŵ
			    		}
			    	}
		    	}
		    	
		    	
		    }
		}
		if(count == 0)			//������ �ϰ͵� ������
		{
			numberPart[0] = a;	//���ڿ� �״��
		}
		else
		{
			for(i=0;i<=count;i++)
			{
				System.out.println(numberPart[i]+"�ٹٲ�");	//�ѹ���Ʈ
				if(i!=count)
				System.out.println(sachicPart[i]+"�ٹٲ�");	//��Ģ�� �������� �����ʾ�
			}
		}
	}
}
