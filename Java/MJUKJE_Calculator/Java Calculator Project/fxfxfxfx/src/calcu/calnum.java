package calcu;

import java.text.DecimalFormat;

public class calnum
{
	
	static boolean d = true;
	static short i = 0, iex = 0;
	static double[] resultPart = new double[31];
	private static String strin = "";
	public static String strout = "";
	
	
	public calnum(String strin)
	{
		this.strin = strin;
		Calculate();
	}
	
	
	public static void Calculate()	//���۷��� ����� �ο��۷��� ��� ��������� �ٲ�
	{
		
		sachicsplit ss = new sachicsplit(strin);		//������ �����ֱ� ��ü�� ����
		
		if(ss.count==0)								//��Ģ������ ������
		{
			strout = ss.numberPart[0];				//���� �ϳ� ����
		}
		
		else										//������ ������
		{
			for(i=0;i<=ss.count;i++)		//�������� ���� +1���� ������ ����
			{
				resultPart[i] = Double.parseDouble(ss.numberPart[i]);		//����� ���� ������ �迭�� ��������
				//System.out.println(resultPart[i]);
			}
			
			System.out.println("");
			
			
			d=true;
			while(d==true)
			{
				d=false;
				for(i=0;i<ss.count;i++)
				{
					if( ss.sachicPart[i] == '^')
					{
						d=true;
						resultPart[i] = Math.pow(resultPart[i], resultPart[i+1]);
						if(d==true)
						{
							resultPart[i+1] = resultPart[i];
							//System.out.printf("resultPart[%d] = %f\n",i,resultPart[i]);
							//System.out.println("");
							for(iex=i;iex<=ss.count;iex++)
							{
								resultPart[iex] = resultPart[iex+1];
								//System.out.printf("�Ű��ֱ� resultPart[%d] = %f\n",iex,resultPart[iex]);
							}
							for(iex=i;iex<ss.count;iex++)
							{
								ss.sachicPart[iex] = ss.sachicPart[iex+1];
								//System.out.printf("�Ű��ֱ� sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
							}
							i=ss.count;
						}
					}
				}
			}
			
			
			d=true;
			while(d==true)
			{
				d=false;
				for(i=0;i<ss.count;i++)
				{
					switch(ss.sachicPart[i])
					{
					case '*':
					{
						d=true;
						resultPart[i] = resultPart[i] * resultPart[i+1];
						
						break;
					}
					case '/':
					{
						d=true;
						resultPart[i] = resultPart[i] / resultPart[i+1];
						
						break;
					}
					case '%':
					{
						d=true;
						resultPart[i] = resultPart[i] % resultPart[i+1];
						
						break;
					}
					}
					if(d==true)
					{
						resultPart[i+1] = resultPart[i];
						//System.out.printf("resultPart[%d] = %f\n",i,resultPart[i]);
						//System.out.println("");
						for(iex=i;iex<=ss.count;iex++)
						{
							resultPart[iex] = resultPart[iex+1];
							//System.out.printf("�Ű��ֱ� resultPart[%d] = %f\n",iex,resultPart[iex]);
						}
						for(iex=i;iex<ss.count;iex++)
						{
							ss.sachicPart[iex] = ss.sachicPart[iex+1];
							//System.out.printf("�Ű��ֱ� sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
						}
						i=ss.count;
					}
					
				}
			}
			
			
			d=true;
			while(d==true)
			{
				d=false;
				for(i=0;i<ss.count;i++)
				{
					
					switch(ss.sachicPart[i])
					{
					case '+':
					{
						d=true;
						resultPart[i] = resultPart[i] + resultPart[i+1];
						break;
					}
					case '-':
					{
						d=true;
						resultPart[i] = resultPart[i] - resultPart[i+1];
						break;
					}
					}
					
					if(d==true)
					{
						resultPart[i+1] = resultPart[i];
						//System.out.printf("resultPart[%d] = %f\n",i,resultPart[i]);
						//System.out.println("");
						for(iex=i;iex<=ss.count;iex++)
						{
							resultPart[iex] = resultPart[iex+1];
							//System.out.printf("�Ű��ֱ� resultPart[%d] = %f\n",iex,resultPart[iex]);
						}
						for(iex=i;iex<ss.count;iex++)
						{
							ss.sachicPart[iex] = ss.sachicPart[iex+1];
							//System.out.printf("�Ű��ֱ� sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
						}
						i=ss.count;
					}
					
					
				}
			}
			
			//System.out.println(resultPart[0]+"����Ʈ��Ʈ");
			DecimalFormat df = new DecimalFormat("#.#");	//�Ҽ��� �ڸ��� ������ e��¼�� �ȳ����� �ϴ°�
	        df.setMaximumFractionDigits(40);
			strout = df.format(resultPart[0]);
			
		}
		
		//System.out.println("Į�ѿ��� �ǾƷ� ���� ����" + strout);
		//CalMainController.setStrout(b);
	}
}
