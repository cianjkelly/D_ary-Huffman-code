package codes;

import java.io.FileWriter;
import java.io.IOException;

public class SequenceGenerator
{
	static int valuex1 = 0; static int valuex2 = 0; static int valuex3 = 0; static int valuex4 = 0;
	public static String[] SequenceGenerated(double n, double[] probability)
	{
		String[] total = new String[(int) (n+10)];
		double x2 = probability[0] + probability[1];
		double x3 = x2 + probability[3];
		
		for(int i = 0; i < n; i++)
		{
			double r = Math.random();
			if(r <= 0.2141)
			{
				valuex1++;
				total[i] = "x1";
				//System.out.("x1");
			}
			else if(r > 0.2141 && r <= x2)
			{
				valuex2++;
				total[i] = "x2";
			}
			else if (r > x2 && r <= x3)
			{
				valuex3++;
				total[i] = "x3";
			}
			else
			{
				valuex4++;
				total[i] = "x4";
			}
			
		}
		
		for(int z = 0; z < n; z++)
		{
			System.out.println(total[z]);
		}
		System.out.println("value of x1 is " + valuex1);
		System.out.println("value of x2 is " + valuex2);
		System.out.println("value of x3 is " + valuex3);
		System.out.println("value of x4 is " + valuex4);
		System.out.println("Empirical frequency of x1 = " + valuex1/n + " & probability is: 0.2141" + " difference is " + (valuex1/n - 0.2141));
		System.out.println("Empirical frequency of x2 = " + valuex2/n + " & probability is: 0.2648" + " difference is " + (valuex1/n - 0.2648));
		System.out.println("Empirical frequency of x3 = " + valuex3/n + " & probability is: 0.3207" + " difference is " + (valuex1/n - 0.3207));
		System.out.println("Empirical frequency of x4 = " + valuex4/n + " & probability is: 0.2004" + " difference is " + (valuex1/n - 0.2004));
		System.out.println("\n");
		return total;
		
	}
}
