package quickdt;

import java.util.ArrayList;

public class Forest {
	
	private final ArrayList<Node> trees = new ArrayList<Node>();
	
	public boolean fullRecall() {
		// TODO
		// lame..
		return true;
	}
	
	public double meanDepth() {
		// Hmm.. Deja Vu
		double totalDepth = 0;
		int count = 0;
		for (Node tree : trees) {
			double depth = tree.meanDepth();
			totalDepth += depth;
			count++;
		}
		double avg = 0.0;
		if (count>0) avg= totalDepth/(double)count;
		return avg;
	}
	
	public void add(Node tree) {
		trees.add(tree);
	}
	
	public double meanSize() {
		// Hmm.. Deja Vu
		int totalSize = 0;
		int count = 0;
		for (Node tree : trees) {
			int size = tree.size();
			totalSize +=size;
			count++;
		}
		double avg = 0.0;
		if (count>0) avg= (double) totalSize/count;
		return avg;
	}

}
