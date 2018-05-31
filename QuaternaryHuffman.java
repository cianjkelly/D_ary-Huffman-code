 package codes;

import java.util.*;

class QuaternaryHuffmanTree implements Comparable<QuaternaryHuffmanTree> 
{
    double frequency; // the frequency of this tree
    
    public QuaternaryHuffmanTree(double charFreqs) 
    { 
    	frequency = charFreqs; 
    }

    // compares on the frequency
    public int compareTo(QuaternaryHuffmanTree tree) 
    {
        return (int) (frequency - tree.frequency);
    }
}

class QuaternaryHuffmanLeaf extends QuaternaryHuffmanTree 
{
    String value; // the character this leaf represents

    public QuaternaryHuffmanLeaf(double charFreqs, String val) 
    {
        super(charFreqs);
        value = val;
    }
}

class QuaternaryHuffmanNode extends QuaternaryHuffmanTree 
{
		
    public QuaternaryHuffmanTree left, right, left1, right1; // subtrees
	
    public QuaternaryHuffmanNode(QuaternaryHuffmanTree l, QuaternaryHuffmanTree r, QuaternaryHuffmanTree l1, QuaternaryHuffmanTree r1) 
	{	
        super(l.frequency + r.frequency + l1.frequency + r1.frequency);
        left = l;
        right = r;
        left1 = l1;
        right1 = r1;
    }
}


public class QuaternaryHuffman 
{
	static float total;
	static float exLength;
    public static QuaternaryHuffmanTree buildTree(double[] charFreqs, String[] test2)
    {
        PriorityQueue<QuaternaryHuffmanTree> trees = new PriorityQueue<QuaternaryHuffmanTree>();
        for (int i = 0; i < charFreqs.length; i++)
        {
            if (charFreqs[i] > 0)
            {
                trees.offer(new QuaternaryHuffmanLeaf(charFreqs[i], test2[i]));
            }
        }
        while (trees.size() > 1) 
        {
            QuaternaryHuffmanTree a = trees.poll();
            QuaternaryHuffmanTree b = trees.poll();
            QuaternaryHuffmanTree c = trees.poll();
            QuaternaryHuffmanTree d = trees.poll();

            trees.offer(new QuaternaryHuffmanNode(a, b, c, d));
           
        }
        return trees.poll();
    }

    public static void printCodes(QuaternaryHuffmanTree tree, StringBuffer prefix) 
    {
        if (tree instanceof QuaternaryHuffmanLeaf) 
        {
            QuaternaryHuffmanLeaf leaf = (QuaternaryHuffmanLeaf)tree;
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            total += leaf.frequency;

        }
        else if (tree instanceof QuaternaryHuffmanNode) 
        {
            QuaternaryHuffmanNode node = (QuaternaryHuffmanNode)tree;

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            printCodes(node.left1, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            prefix.append('2');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            
            prefix.append('3');
            printCodes(node.right1, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
    public static float expectedLength(QuaternaryHuffmanTree tree, StringBuffer prefix)
    {    	
        if (tree instanceof QuaternaryHuffmanLeaf) 
        {
        	QuaternaryHuffmanLeaf leaf = (QuaternaryHuffmanLeaf)tree;
        	exLength += (float) (prefix.length()*(leaf.frequency/QuaternaryHuffman.total));
        }
        else if (tree instanceof QuaternaryHuffmanNode) 
        {
            QuaternaryHuffmanNode node = (QuaternaryHuffmanNode)tree;

            prefix.append('0');
            expectedLength(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            expectedLength(node.left1, prefix);
            prefix.deleteCharAt(prefix.length()-1);
                        
            prefix.append('2');
            expectedLength(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            prefix.append('3');
            expectedLength(node.right1, prefix);
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