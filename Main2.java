package codes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main2 
{
	public static void main(String[] args) throws IOException
	{
		double n = 10000000;
    	String[] DNA = new String[(int) (n+10)];
		int neg = 0;
		DNA[0] = "x1";
		DNA[1] = "x2";
		DNA[2] = "x3";
		DNA[3] = "x4";
		double[] probability = new double[(int) (n+10)];
		probability[0] = 0.2141;
		probability[1] = 0.2648;
		probability[2] = 0.3207;
		probability[3] = 0.2004;
		int d_ary = 3;
		
		HashMap<Double,String> sequence = new HashMap<Double, String>();
		for(int i = 0; i < DNA.length; i++)
		{
			sequence.put(probability[i], DNA[i]);
		}
		String[] total = SequenceGenerator.SequenceGenerated(n,probability);
		double v1 = SequenceGenerator.valuex1;
		double v2 = SequenceGenerator.valuex2;
		double v3 = SequenceGenerator.valuex3;
		double v4 = SequenceGenerator.valuex4;	
		
		double[] charFreqs = new double[(int) (n+10)];
		charFreqs[0] = v1;
		charFreqs[1] = v2;
		charFreqs[2] = v3;
		charFreqs[3] = v4;
		
		if(d_ary == 2)
		{
			HuffmanTree tree = Binary.buildTree(charFreqs, total);
		    System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
		    Binary.printCodes(tree, new StringBuffer());
		    System.out.println("\n");
		    Binary.expectedLength(tree, new StringBuffer());
		    System.out.println("Expected codeword length is: " + Binary.exLength + " bits");
		    System.out.println("Entropy of x is: " + Binary.Entropy(probability[0], probability[1], probability[2], probability[3]));
		    
		}
		if(d_ary == 3)
		{
			for(int p = 0; p < total.length; p++)
        	{
        		if(total[p] == null)
        		{
        			neg ++;
        		}
        	}
        	int nums = total.length-neg;
        	
        	while((nums-1)%(d_ary-1) != 0)
        	{
        		total[nums] = "k";
        		charFreqs[nums] = 0.1;
        		nums++;
        	}
	     	TertiaryHuffmanTree tree = TertiaryHuffman.buildTree(charFreqs, total);
			 System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
			 TertiaryHuffman.printCodes(tree, new StringBuffer());
			 System.out.println("\n");
			 TertiaryHuffman.expectedLength(tree, new StringBuffer());
		  	 System.out.println("Expected codeword length is: " + TertiaryHuffman.exLength + " bits");
			 System.out.println("Entropy of x is: " + TertiaryHuffman.Entropy(probability[0], probability[1], probability[2], probability[3]));
		}
		if(d_ary == 4)
		{
			for(int p = 0; p < total.length; p++)
        	{
        		if(total[p] == null)
        		{
        			neg ++;
        		}
        	}
        	int nums = total.length-neg;
        	
        	while((nums-1)%(d_ary-1) != 0)
        	{
        		total[nums] = "k";
        		charFreqs[nums] = 0.1;
        		nums++;
        	}
			 QuaternaryHuffmanTree tree = QuaternaryHuffman.buildTree(charFreqs, total);
			 System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
			 QuaternaryHuffman.printCodes(tree, new StringBuffer());
			 System.out.println("\n");
			 QuaternaryHuffman.expectedLength(tree, new StringBuffer());
			 System.out.println("Expected codeword length is: " + QuaternaryHuffman.exLength + " bits");
			 System.out.println("Entropy of x is: " + QuaternaryHuffman.Entropy(probability[0], probability[1], probability[2], probability[3]));
		}

		PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Cian\\Desktop\\15386256.txt.txt"));
		
        for(int j = 0; j < total.length; j++)
        {
	        out.print(total[j]);
        }
		out.close();
	
		
	}
}
