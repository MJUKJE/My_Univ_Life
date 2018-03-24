package calcu;

import java.text.DecimalFormat;

public class funcsplit {
	private String strin = "";
	public String strout = "";
	
	boolean fin = false;		//�Լ� ������
	
	short[] funcPosition = new short[30];
	String[] funcPart = new String[10];
	short i, funccount=0;
	int len;
	double num;
	boolean d = true;
	
	
	public funcsplit(String strin)				//strin�� �޾ƿ�
	{
		this.strin = strin;						//�޾ƿ� ��ǲ��Ʈ���� str�� ����
		SplitFunc();
	}
	
	public void SplitFunc()
	{
		DecimalFormat df = new DecimalFormat("#.#");	//�Ҽ��� �ڸ��� ������ e��¼�� �ȳ����� �ϴ°�
        df.setMaximumFractionDigits(40);
        
		d=true;
		while(d==true)
		{
			fin = false;
			d=false;
			for(i = 0; i < strin.length(); i++)				//��ǲ��Ʈ���� ���̸�ŭ
			{												//���ڿ��� ���̸� ��ȯ
			    char c = strin.charAt(i);						//�ش� ���ڿ��� �ش� �ڸ����� ���ڸ� ��ȯ						
			    if( fin==false && (c == 'S' || c == 'C' || c == 'T' || c == 'L') )	//���ĺ��϶�
			    {
			    	d=true;
			    	fin=true;							//�Լ������� �˸���
			    	funcPosition[funccount] = i;		//���ۺκ��ڸ��� ����
			    	funccount++;						//ī��Ʈ �ø���
			    }
			    if(fin==true && (c == '(' || c == ')') )	//���ĺ��϶�
			    {
			    	if(c == '(')
			    	{
			    		funcPosition[funccount] = i;		//���ۺκ��ڸ��� ����
				    	funccount++;	
			    	}
			    	
			    	else									//��ȣ�� ������
			    	{
			    		fin=false;							//�Լ� ��
				    	funcPosition[funccount] = i;		
				    	funccount++;		//ī��Ʈ �ø���
				    	
				    	funcPart[funccount/3-1] = strin.substring(funcPosition[funccount-2]+1,funcPosition[funccount-1]);	//�Լ� �� ��ȣ���ڸ��� ����
				    	//System.out.println(funcPart[funccount/3-1]+"��� ���� ����Ǵ����� ����!");				//
				    	calnum cn = new calnum(funcPart[funccount/3-1]);
				    	//num=0;
				    	num = Double.parseDouble(cn.strout);
				    	 
				         
				    	//System.out.println(strin.substring(funcPosition[funccount-3], funcPosition[funccount-2])+"�̰� �Լ��κ�");
				    	//System.out.println("tlqkf");
				    	
				    	switch( strin.substring(funcPosition[funccount-3], funcPosition[funccount-2]) )	//���ĺ� ���� ( �ձ��� �� ���ĺ���
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
