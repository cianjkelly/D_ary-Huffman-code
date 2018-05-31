package codes;

public class Main 
{
    public static void main(String[] args)
    {
    	// please enter the probability variables here
    	int neg = 0;
    	String[] input = new String[6];
        input[0] = "x1";
        input[1] = "x2";
        // enter the variables corresponding probability here is form is occurs 1/2 times in 8 = 4 and so on
        double[] charFreqs = new double[6];
        charFreqs[0] = 4;
        charFreqs[1] = 2;
        int d_ary = 4;
        
        if(d_ary == 2)
        {
        	HuffmanTree tree = Binary.buildTree(charFreqs, input);
    	    System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
    	    Binary.printCodes(tree, new StringBuffer());
    	    System.out.println("\n");
    	    Binary.expectedLength(tree, new StringBuffer());
    	    System.out.println("Expected codeword length is: " + Binary.exLength + " bits");
	    }
        if(d_ary == 3)
        {
        	for(int p = 0; p < input.length; p++)
        	{
        		if(input[p] == null)
        		{
        			neg++;
        		}
        	}
        	int nums = input.length-neg;
        	
        	while((nums-1)%(d_ary-1) != 0)
        	{
        		input[nums] = "k";
        		charFreqs[nums] = 0.1;
        		nums++;
        	}
        	TertiaryHuffmanTree t_tree = TertiaryHuffman.buildTree(charFreqs, input);
        	 System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
     	    TertiaryHuffman.printCodes(t_tree, new StringBuffer());
     	    System.out.println("\n");
     	    TertiaryHuffman.expectedLength(t_tree, new StringBuffer());
    	    System.out.println("Expected codeword length is: " + TertiaryHuffman.exLength + " bits");
        }
        if(d_ary == 4)
        {
        	for(int p = 0; p < input.length; p++)
        	{
        		if(input[p] == null)
        		{
        			neg++;
        		}
        	}
        	int nums = input.length-neg;
        	
        	while((nums-1)%(d_ary-1) != 0)
        	{
        		input[nums] = "k";
        		charFreqs[nums] = 0.1;
        		nums++;
        	}
			 QuaternaryHuffmanTree tree = QuaternaryHuffman.buildTree(charFreqs, input);
			 System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
			 QuaternaryHuffman.printCodes(tree, new StringBuffer());
			 System.out.println("\n");
			 QuaternaryHuffman.expectedLength(tree, new StringBuffer());
			 System.out.println("Expected codeword length is: " + QuaternaryHuffman.exLength + " bits");
        }
        
        
        
    }
}
