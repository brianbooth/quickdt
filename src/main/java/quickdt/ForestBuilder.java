package quickdt;

import quickdt.scorers.Scorer1;


public class ForestBuilder {
	// From http://en.wikipedia.org/wiki/Random_forest
	//	Each tree is constructed using the following algorithm:
	//
	//	Let the number of training cases be N, and the number of variables in the classifier be M.
	//	We are told the number m of input variables to be used to determine the decision at a node of the tree; m should be much less than M.
	//	Choose a training set for this tree by choosing n times with replacement from all N available training cases (i.e. take a bootstrap sample). Use the rest of the cases to estimate the error of the tree, by predicting their classes.
	//	For each node of the tree, randomly choose m variables on which to base the decision at that node. Calculate the best split based on these m variables in the training set.
	//	Each tree is fully grown and not pruned (as may be done in constructing a normal tree classifier).
	//	
	
	// THOUGHTS..
	// Need to specify number of trees.  Optimal number dependent on complexity of data?  Rule of thumb?
    // Could a tree be the logical equivalent of another tree.  Good way to detect and discard?
	// Should forest consist of different kinds of trees? Variable maxDepth, Variable minProb?

	private Scorer scorer;

	public ForestBuilder() {
		this(new Scorer1());
	}
	
	public ForestBuilder(Scorer sc) {
		this.scorer = sc;
	}
	
	// Forest of ten trees?  What's a good number?  How to do dynamically?
	public static final int FOREST_SIZE = 10;
	
	public static int attributeSubsetCount(final int attributeCount) {
		// Need a better way to do this.  
		// subsetCount should be much less than attributeCount.  Just guessing with 1/3rd
		int subsetCount=attributeCount/3;
		if (attributeCount<30) subsetCount=Math.min(10, attributeCount);
		return subsetCount;
	}
	
	public Forest buildForest(final Iterable<Instance> trainingData) {
		return buildForest(trainingData, Integer.MAX_VALUE, 1.0);
	}

	public Forest buildForest(final Iterable<Instance> trainingData, final int maxDepth, final double minProbability) {
		// There should be partitioning of trainingData
		final boolean FOREST_MODE_TRUE = true;

		final Forest forest = new Forest();		
		final TreeBuilder tb = new TreeBuilder(scorer, FOREST_MODE_TRUE);
		for (int i=0; i<FOREST_SIZE;i++) {
			final Node tree = tb.buildTree(trainingData, maxDepth, minProbability);
			forest.add(tree);
		}
		return forest;
	}
}
