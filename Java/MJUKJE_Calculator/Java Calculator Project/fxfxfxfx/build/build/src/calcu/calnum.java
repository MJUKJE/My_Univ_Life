package calcu;

import controller.CalMainController;

public class calnum
{
	static short i = 0;
	static double[] resultPart = new double[31];
	static String b;
	//String a = CalMainController.getStrin();
	//blocksplit bs = new blocksplit(CalMainController.getStrin());
	//sachicsplit ss;// = new sachicsplit(CalMainController.getStrin());
	public calnum()
	{
		//this.ss = ss;
	}
	public static String Calculate(sachicsplit ss)	//오퍼랜드 계산후 두오퍼랜드 모두 결과값으로 바꿔
	{
		if(ss.count==0)
		{
			b = ss.numberPart[0];
		}
		
		else
		{
			for(i=0;i<=ss.count;i++)		//연산자의 갯수 +1번
			{
				System.out.println(ss.numberPart[i]+"뭔데");
				resultPart[i] = Double.parseDouble(ss.numberPart[i]);
			}
			for(i=0;i<ss.count;i++)
			{
				switch(ss.sachicPart[i])
				{
				case '^':
				{
					resultPart[i] = Math.pow(resultPart[i], resultPart[i+1]);
					resultPart[i+1] = resultPart[i];
					break;
				}
				}
			}
			for(i=0;i<ss.count;i++)
			{
				switch(ss.sachicPart[i])
				{
				case '*':
				{
					resultPart[i] = resultPart[i] * resultPart[i+1];
					resultPart[i+1] = resultPart[i];
					break;
				}
				case '/':
				{
					resultPart[i] = resultPart[i] / resultPart[i+1];
					resultPart[i+1] = resultPart[i];
					break;
				}
				case '%':
				{
					resultPart[i] = resultPart[i] % resultPart[i+1];
					resultPart[i+1] = resultPart[i];
					break;
				}
				}
			}
			for(i=(short)(ss.count-1); i>=0; i--)	//어떻게 곱셈한것들 안전히 더할까
			{
				switch(ss.sachicPart[i])
				{
				case '*':
				{
					resultPart[i] = resultPart[i+1];
					break;
				}
				case '/':
				{
					resultPart[i] = resultPart[i+1];
					break;
				}
				case '%':
				{
					resultPart[i] = resultPart[i+1];
					break;
				}
				}
			}
			for(i=0;i<ss.count;i++)
			{
				switch(ss.sachicPart[i])
				{
				case '+':
				{
					resultPart[0] += resultPart[i+1];
					break;
				}
				case '-':
				{
					resultPart[0] -= resultPart[i+1];
					break;
				}
				}
			}
			System.out.println(resultPart[0]);
			b = String.valueOf(resultPart[0]);
			System.out.println("칼넘에서 맨아래 비의 값은" + b);
		}
		return b;
		//CalMainController.setStrout(b);
	}
}
