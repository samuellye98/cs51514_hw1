package hw1.problem3;

import org.junit.Test;
import org.junit.Assert;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class PairwiseTest {

    @Test public void testTwoPartition() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("A");
        b1.add("B");
        List<String> b2 = new ArrayList<>();
        b2.add("X");
        b2.add("Y");
        partitions.add(b1);
        partitions.add(b2);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
        Assert.assertEquals(testCases.size(),
                pairwiseInstance.allCombinations(partitions).size());
    }

    @Test public void testEqualPartition() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("A");
        b1.add("B");
        b1.add("C");
        List<String> b2 = new ArrayList<>();
        b2.add("X");
        b2.add("Y");
        b2.add("Z");
        partitions.add(b1);
        partitions.add(b2);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
        Assert.assertEquals(testCases.size(),
                pairwiseInstance.allCombinations(partitions).size());
    }


    @Test public void testNoPartition() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Assert.assertEquals(0, testCases.size());
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
    }

    @Test public void testOnePartition() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("A");
        b1.add("B");
        b1.add("C");
        partitions.add(b1);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Assert.assertEquals(0, testCases.size());
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
    }

    @Test public void testBasicPartition() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("A");
        b1.add("B");
        b1.add("C");
        List<String> b2 = new ArrayList<>();
        b2.add("1");
        b2.add("2");
        b2.add("3");
        List<String> b3 = new ArrayList<>();
        b3.add("x");
        b3.add("y");
        partitions.add(b1);
        partitions.add(b2);
        partitions.add(b3);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Set<List<String>> allTestCases =
                pairwiseInstance.allCombinations(partitions);
        Assert.assertTrue(allTestCases.size() > testCases.size());
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
    }

    @Test public void testBasicPartition2() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("+");
        b1.add("-");
        List<String> b2 = new ArrayList<>();
        b2.add("low");
        b2.add("high");
        List<String> b3 = new ArrayList<>();
        b3.add("MIN");
        b3.add("O");
        b3.add("MAX");
        partitions.add(b1);
        partitions.add(b2);
        partitions.add(b3);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Set<List<String>> allTestCases =
                pairwiseInstance.allCombinations(partitions);
        Assert.assertTrue(allTestCases.size() > testCases.size());
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
    }

    @Test public void testBasicPartition3() {
        Pairwise pairwiseInstance = new Pairwise();
        Set<List<String>> partitions = new HashSet<>();
        List<String> b1 = new ArrayList<>();
        b1.add("Buy");
        b1.add("Sell");
        List<String> b2 = new ArrayList<>();
        b2.add("NYSE");
        b2.add("CME");
        List<String> b3 = new ArrayList<>();
        b3.add("Facebook");
        b3.add("Amazon");
        b3.add("Microsoft");
        List<String> b4 = new ArrayList<>();
        b4.add("Success");
        b4.add("Failure");
        List<String> b5 = new ArrayList<>();
        b5.add("Robinhood");
        b5.add("SAXO");
        List<String> b6 = new ArrayList<>();
        b6.add("Afterhours");
        b6.add("Premarket");
        b6.add("Normal");
        partitions.add(b1);
        partitions.add(b2);
        partitions.add(b3);
        partitions.add(b4);
        partitions.add(b5);
        partitions.add(b6);

        Set<List<String>> testCases = pairwiseInstance.pairwise(partitions);
        Set<List<String>> allTestCases =
                pairwiseInstance.allCombinations(partitions);
        Assert.assertTrue(allTestCases.size() > testCases.size());
        Assert.assertTrue(pairwiseInstance.isPairwiseCoverage(partitions, testCases));
    }
}
