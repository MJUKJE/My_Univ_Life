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
	
	
	public static void Calculate()	//오퍼랜드 계산후 두오퍼랜드 모두 결과값으로 바꿔
	{
		
		sachicsplit ss = new sachicsplit(strin);		//연산자 나눠주기 객체를 선언
		
		if(ss.count==0)								//사칙연산이 없을땐
		{
			strout = ss.numberPart[0];				//숫자 하나 전부
		}
		
		else										//연산자 있을때
		{
			for(i=0;i<=ss.count;i++)		//연산자의 갯수 +1개가 숫자의 갯수
			{
				resultPart[i] = Double.parseDouble(ss.numberPart[i]);		//계산을 위해 더블형 배열에 복사해줌
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
								//System.out.printf("옮겨주기 resultPart[%d] = %f\n",iex,resultPart[iex]);
							}
							for(iex=i;iex<ss.count;iex++)
							{
								ss.sachicPart[iex] = ss.sachicPart[iex+1];
								//System.out.printf("옮겨주기 sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
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
							//System.out.printf("옮겨주기 resultPart[%d] = %f\n",iex,resultPart[iex]);
						}
						for(iex=i;iex<ss.count;iex++)
						{
							ss.sachicPart[iex] = ss.sachicPart[iex+1];
							//System.out.printf("옮겨주기 sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
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
							//System.out.printf("옮겨주기 resultPart[%d] = %f\n",iex,resultPart[iex]);
						}
						for(iex=i;iex<ss.count;iex++)
						{
							ss.sachicPart[iex] = ss.sachicPart[iex+1];
							//System.out.printf("옮겨주기 sachicPart[%d] = %c\n",iex,ss.sachicPart[iex]);
						}
						i=ss.count;
					}
					
					
				}
			}
			
			//System.out.println(resultPart[0]+"리절트파트");
			DecimalFormat df = new DecimalFormat("#.#");	//소숫점 자리로 나오게 e어쩌고 안나오게 하는것
	        df.setMaximumFractionDigits(40);
			strout = df.format(resultPart[0]);
			
		}
		
		//System.out.println("칼넘에서 맨아래 비의 값은" + strout);
		//CalMainController.setStrout(b);
	}
}
