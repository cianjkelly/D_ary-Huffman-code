 package codes;

import java.util.*;

class TertiaryHuffmanTree implements Comparable<TertiaryHuffmanTree> 
{
    double frequency;
    public TertiaryHuffmanTree(double charFreqs) 
    { 
    	frequency = charFreqs; 
    }
    public int compareTo(TertiaryHuffmanTree tree) 
    {
        return (int) (frequency - tree.frequency);
    }
}

class TertiaryHuffmanLeaf extends TertiaryHuffmanTree 
{
    String value;

    public TertiaryHuffmanLeaf(double charFreqs, String val) 
    {
        super(charFreqs);
        value = val;
    }
}

class TertiaryHuffmanNode extends TertiaryHuffmanTree 
{
		
    public TertiaryHuffmanTree left, right, center; // subtrees
	
    public TertiaryHuffmanNode(TertiaryHuffmanTree l, TertiaryHuffmanTree r, TertiaryHuffmanTree c) 
	{	
        super(l.frequency + r.frequency + c.frequency);
        left = l;
        right = r;
        center = c;
    }
}


public class TertiaryHuffman 
{
	static double total;
	static float exLength;
    public static TertiaryHuffmanTree buildTree(double[] charFreqs, String[] test2)
    {
        PriorityQueue<TertiaryHuffmanTree> trees = new PriorityQueue<TertiaryHuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
        {
            if (charFreqs[i] >= 0.1)
            {
                trees.offer(new TertiaryHuffmanLeaf(charFreqs[i], test2[i]));
            }
        }
        // loop until there is only one tree left
        while (trees.size() > 1) 
        {
            // two trees with least frequency
            TertiaryHuffmanTree a = trees.poll();
            TertiaryHuffmanTree b = trees.poll();
            TertiaryHuffmanTree c = trees.poll();
            // put into new node and re-insert into queue

            trees.offer(new TertiaryHuffmanNode(a, b, c));
           
        }
        return trees.poll();
    }

    public static void printCodes(TertiaryHuffmanTree tree, StringBuffer prefix) 
    {
        if (tree instanceof TertiaryHuffmanLeaf) 
        {
            TertiaryHuffmanLeaf leaf = (TertiaryHuffmanLeaf)tree;
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            total += leaf.frequency;
        }
        else if (tree instanceof TertiaryHuffmanNode) 
        {
            TertiaryHuffmanNode node = (TertiaryHuffmanNode)tree;

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            printCodes(node.center, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            prefix.append('2');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
    public static float expectedLength(TertiaryHuffmanTree tree, StringBuffer prefix)
    {    	
        if (tree instanceof TertiaryHuffmanLeaf) 
        {
        	TertiaryHuffmanLeaf leaf = (TertiaryHuffmanLeaf)tree;
        	exLength += (float) (prefix.length()*(leaf.frequency/TertiaryHuffman.total));
        }
        else if (tree instanceof TertiaryHuffmanNode) 
        {
            TertiaryHuffmanNode node = (TertiaryHuffmanNode)tree;

            prefix.append('0');
            expectedLength(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            expectedLength(node.center, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            prefix.append('2');
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