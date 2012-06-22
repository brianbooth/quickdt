package com.uprizer.quickdt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.annotations.Sets;

import quickdt.Attributes;
import quickdt.Forest;
import quickdt.ForestBuilder;
import quickdt.Instance;
import quickdt.Misc;
import quickdt.Scorer;
import quickdt.scorers.Scorer1;

import com.google.common.collect.Lists;

public class ForestBuilderTest {
	@Test
	public void largeAttributeTest() throws Exception {
		final boolean FOREST_MODE_TRUE = true;
		final BufferedReader br = new BufferedReader(new InputStreamReader((new GZIPInputStream(new FileInputStream(
				new File(new File(System.getProperty("user.dir")), "testdata/mobo1.txt.gz"))))));

		final List<Instance> instances = Lists.newLinkedList();

		int count = 0;
		while (true) {
			count++;
			final String line = br.readLine();
			if (line == null) {
				break;
			}
			final JSONObject jo = (JSONObject) JSONValue.parse(line);
			final Attributes a = new Attributes();
			a.putAll((JSONObject) jo.get("attributes"));
			instances.add(new Instance(a, (String) jo.get("output")));
		}

		System.out.println("Read " + instances.size() + " instances");

		final ForestBuilder fb = new ForestBuilder();

		final long startTime = System.currentTimeMillis();
		final Forest forest = fb.buildForest(instances, 100, 1.0);
		final long finishTime = System.currentTimeMillis();
		final long timeElapsed = finishTime - startTime;
		

		System.out.println(" build time "
				+ timeElapsed + ", meanSize: " + forest.meanSize() + " mean depth: "
				+ forest.meanDepth());
		
		System.out.println("ForestMeanSize: "+forest.meanSize());
		System.out.println("ForestMeanDepth: "+forest.meanDepth());

		Assert.assertTrue(forest.fullRecall(), "Confirm that the tree achieves full recall on the training set");
		Assert.assertTrue(forest.meanSize() < 800, "Avg tree size should be less than 800 nodes");
		Assert.assertTrue(forest.meanDepth() < 8, "Mean depth should be less than 8");
		Assert.assertTrue(timeElapsed < 20000,
				"Building this forest should take less than 20 seconds");
	}
}
