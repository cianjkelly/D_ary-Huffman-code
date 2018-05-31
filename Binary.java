 package codes;

import java.util.*;

class HuffmanTree implements Comparable<HuffmanTree> 
{
    double frequency;
    public HuffmanTree(double charFreqs) 
    { 
    	frequency = charFreqs; 
    }
    public int compareTo(HuffmanTree tree) 
    {
        return (int) (frequency - tree.frequency);
    }
}

class HuffmanLeaf extends HuffmanTree 
{
    String value;

    public HuffmanLeaf(double charFreqs, String val) 
    {
        super(charFreqs);
        value = val;
    }
}

class HuffmanNode extends HuffmanTree 
{
		
    public HuffmanTree left, right; // subtrees
	
    public HuffmanNode(HuffmanTree l, HuffmanTree r) 
	{
	    super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}


public class Binary 
{
	static double total;
	static float exLength;
    public static HuffmanTree buildTree(double[] charFreqs, String[] test2)
    {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        for (int i = 0; i < charFreqs.length; i++)
        {
            if (charFreqs[i] >= 0.1)
            {
                trees.offer(new HuffmanLeaf(charFreqs[i], test2[i]));
            }
        }
        while (trees.size() > 1) 
        {
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
            trees.offer(new HuffmanNode(a, b));           
        }
        return trees.poll();
    }

    public static void printCodes(HuffmanTree tree, StringBuffer prefix) 
    {
        if (tree instanceof HuffmanLeaf) 
        {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            total += leaf.frequency;
        }
        else if (tree instanceof HuffmanNode) 
        {
            HuffmanNode node = (HuffmanNode)tree;

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
    public static float expectedLength(HuffmanTree tree, StringBuffer prefix)
    {    	
        if (tree instanceof HuffmanLeaf) 
        {
        	HuffmanLeaf leaf = (HuffmanLeaf)tree;
        	exLength += (float) (prefix.length()*(leaf.frequency/Binary.total));

        }
        else if (tree instanceof HuffmanNode) 
        {
            HuffmanNode node = (HuffmanNode)tree;

            prefix.append('0');
            expectedLength(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            expectedLength(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    	return exLength;
    }
    
    public static double Entropy(double probability, double probability2, double probability3, double probability4)
    {
    	
    	double sum = (probability*Math.log(probability)/Math.log(2))-(probability2*Math.log(probability2)/Math.log(2))
    			- (probability3*Math.log(probability3)/Math.log(2))- (probability4*Math.log(probability4)/Math.log(2));
    	return sum;
    }
}
