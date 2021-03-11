package hw1.problem3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

public class Pairwise {
    // Write a method that computes a (small, but not necessarily
    // minimal) list of tuples that satisfy "Pair-wise" coverage for a
    // given list of partitions.
    public static Set<List<String>> pairwise(Set<List<String>> partitions) {
        if (partitions.size() <= 1) {
            return new HashSet<>();
        }

        // [TODO] You should write your code starting from here.
        HashSet<List<String>> res = new HashSet<List<String>>();
        Set<List<String>> allPossiblePairs =
                new HashSet<List<String>>();

        List<List<String>> pairwiseLst =
                new ArrayList<List<String>>(partitions);

        // Generate all possible pairs
        // {[charA, blockA, charB, blockB], ...}
        for (int i = 0; i < pairwiseLst.size() - 1; i++) {
            for (int j = i+1; j < pairwiseLst.size(); j++) {
                for (String charA : pairwiseLst.get(i)) {
                    for (String charB :
                            pairwiseLst.get(j)) {
                        List<String> temp =
                                new ArrayList<String>();
                        temp.add(charA);
                        temp.add(Integer.toString(i));
                        temp.add(charB);
                        temp.add(Integer.toString(j));
                        allPossiblePairs.add(temp);
                    }
                }
            }
        }

        // Build pairwise tests
        while (allPossiblePairs.size() > 0) {
            // Get an uncovered pair
            Iterator<List<String>> iterator =
                    allPossiblePairs.iterator();
            List<String> uncoveredPair = iterator.next();

            // Get best possible test case with widest test coverage
            String charA = uncoveredPair.get(0);
            int blockA =
                    Integer.valueOf(uncoveredPair.get(1));
            String charB = uncoveredPair.get(2);
            int blockB =
                    Integer.valueOf(uncoveredPair.get(3));
//            System.out.println(Integer.toString(allPossiblePairs.size()) + " " + charA + " "+ charB);

            TestSet testset = new TestSet(charA, blockA, charB, blockB,
                    allPossiblePairs, pairwiseLst);
            List<String> bestTestCase = testset.getBestTest();

            // add to result array
            res.add(bestTestCase);

            // Remove covered pairs from allPossiblePairs
            for (int i = 0; i < bestTestCase.size() - 1; i++) {
                for (int j = i+1; j < bestTestCase.size(); j++) {
                    List<String> temp = new ArrayList<String>();
                    temp.add(bestTestCase.get(i));
                    temp.add(Integer.toString(i));
                    temp.add(bestTestCase.get(j));
                    temp.add(Integer.toString(j));
                    if (allPossiblePairs.contains(temp)) {
                        allPossiblePairs.remove(temp);
                    }
                }
            }
        }
        return res;
    }

    // The next method is provided to help you illustrate
    // how you could traverse the partitions to compute some tuples.

    // Compute a list of tuples that satisfy "All Combinations" coverage
    // for a given list of partitions.
    public static Set<List<String>> allCombinations(Set<List<String>> partitions) {
        Set<List<String>> result = new HashSet<List<String>>();
        int numPartitions = partitions.size();
        // Create an array with sizes of all partitions.
        int[] sizes = new int[numPartitions];
        int i = 0;
        for (List<String> partition : partitions) {
            sizes[i] = partition.size();
            if (sizes[i] == 0) return result; // Empty list of tuples.
            i++;
        }
        // Create an array of indexes into the partitions.
        // This array will range from [0,0...0] to [s1,s2...sn],
        // where "si" is the size of the "i"-th partition.
        int[] indexes = new int[numPartitions]; // all zeroes
        int last = 0; // index of the last position to change
        do {
            // Create a tuple for the given values of indexes.
            List<String> tuple = new LinkedList<String>();
            i = 0;
            for (List<String> partition : partitions) {
                tuple.add(partition.get(indexes[i++]));
            }
            result.add(tuple);
            // Move to the next value of indexes.
            last = numPartitions - 1;
            while (last >= 0) {
                indexes[last]++;
                if (indexes[last] < sizes[last]) break;
                indexes[last] = 0;
                last--;
            }
        } while (last >= 0);
        return result;
    }

    // Returns true if testCases fulfills pairwise coverage over partitions
    public static boolean isPairwiseCoverage(Set<List<String>> partitions,
                                     Set<List<String>> testCases) {
        // base cases
        if (partitions.size() <= 1) {
            return true;
        }
        if (testCases.size() == 0) {
            return false;
        }

        // Generate all possible pairs
        // {[charA, charB], ...}
        Set<List<String>> allPossiblePairs =
                new HashSet<List<String>>();
        List<List<String>> pairwiseLst =
                new ArrayList<List<String>>(partitions);
        for (int i = 0; i < pairwiseLst.size() - 1; i++) {
            for (int j = i+1; j < pairwiseLst.size(); j++) {
                for (String charA : pairwiseLst.get(i)) {
                    for (String charB :
                            pairwiseLst.get(j)) {
                        List<String> temp =
                                new ArrayList<String>();
                        temp.add(charA);
                        temp.add(charB);
                        allPossiblePairs.add(temp);
                    }
                }
            }
        }

        // NP-Complete -> remove pairs found in testCases
        for (List<String> testCase : testCases) {
            for (int i = 0; i < testCase.size() - 1; i++) {
                for (int j = i+1; j < testCase.size(); j++) {
                    List<String> temp =
                            new ArrayList<String>();
                    temp.add(testCase.get(i));
                    temp.add(testCase.get(j));
                    if (allPossiblePairs.contains(temp)) {
                        allPossiblePairs.remove(temp);
                    }
                }
            }
        }

        return allPossiblePairs.size() == 0;
    }

    // A main function for running Pairwise.
    // Partitions are separated with commas, and each block has one string.
    // Example calls:
    //   java Pairwise A B C , 1 2 3 , x y
    //   java Pairwise -1 0 1 , -1 0 1
    //   java Pairwise + - , low high , MIN 0 MAX
    //   java Pairwise Buy Sell , Delhi Mumbai , BMW Audi Mercedes , Valid
    //   Invalid , E-booking In-store , Wh Nwh
    public static void main(String[] args) {
        Set<List<String>> partitions = new HashSet<List<String>>();
        List<String> blocks = new LinkedList<String>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(",")) {
                partitions.add(blocks);
                blocks = new LinkedList<String>();
            } else {
                blocks.add(args[i]);
            }
        }
        partitions.add(blocks);
        System.out.println(partitions);
        Set<List<String>> result = pairwise(partitions);
        // Uncomment the next line to see how "allCombinations" work.
        Set<List<String>> allCombiResult = allCombinations(partitions);
        System.out.println(result.size());
        System.out.println(allCombiResult.size());
        for (List<String> tuple : result) {
            System.out.println(tuple);
        }
    }
}
