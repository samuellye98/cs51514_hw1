package hw1.problem3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;

public class TestSet {
    private String charA; // characteristic A
    private int blockA; // block A
    private String charB; // characteristic B
    private int blockB; // block B
    private Set<List<String>> allPossiblePairs; // uncovered pairs remaining
    private List<List<String>> pairwiseLst; // [block A, block B, ...]
    public List<Tuple> possibleTestCases = new ArrayList<Tuple>();

    public TestSet(String charA, int blockA, String charB, int blockB,
                   Set<List<String>> allPossiblePairs,
                   List<List<String>> pairwiseLst) {
        this.charA = charA;
        this.blockA = blockA;
        this.charB = charB;
        this.blockB = blockB;
        this.allPossiblePairs = allPossiblePairs;
        this.pairwiseLst = pairwiseLst;
    }

    public int getAdditionalScore(List<String> currList, String newItem) {
        /**
         * This function returns the number of pairs the test case will cover
         * by adding the newItem.
         *
         * Inputs: List<String> currList - list of current test case
         *         String newItem - new characteristic to be added
         * Returns: int score - number of uncovered pairs that can be now
         * covered by adding the new characteristic.
         * */
        // List is empty -> no pairs covered
        if (currList.size() == 0) {
            return 0;
        }
        // List contains only one characteristic -> only 1 pair covered
        if (currList.size() == 1) {
            return 1;
        }

        int additionalScore = 0;
        for (int i = 0; i < currList.size(); i++) {
            List<String> temp = new ArrayList<String>();
            temp.add(currList.get(i));
            temp.add(Integer.toString(i));
            temp.add(newItem);
            temp.add(Integer.toString(currList.size()));
            if (this.allPossiblePairs.contains(temp)) {
                additionalScore++;
            }
        }
        return additionalScore;
    }

    public void getTestcases(int index, LinkedList<String> res, int score) {
        /**
         * This function returns all the possible testcases that can be
         * created while charA and charB remains fixed. This is done by
         * iterating each characteristic in each block to get the possible
         * combinations of test cases that can be formed.
         *
         * Inputs: int index - index of current block in pairwiseLst
         *         LinkedList<String> res - current test case
         *         int score - number of uncovered pairs that can be covered
         *         by this test case.
         * */
        int additionalScore = 0;
        if (index >= pairwiseLst.size()) {
            LinkedList<String> toAdd = new LinkedList<String>(res);
             possibleTestCases.add(new Tuple(toAdd, score));
             return;
        } else if (index == blockA) {
            additionalScore = getAdditionalScore(res, charA);
            res.add(charA);
            getTestcases(index+1, res, score+additionalScore);
            res.removeLast();
        } else if (index == blockB) {
            additionalScore = getAdditionalScore(res, charB);
            res.add(charB);
            getTestcases(index+1, res, score+additionalScore);
            res.removeLast();
        } else {
            for (String newItem : pairwiseLst.get(index)) {
                additionalScore = getAdditionalScore(res, newItem);
                res.add(newItem);
                getTestcases(index+1, res, score+additionalScore);
                res.removeLast();
            }
        }
    }

    public List<String> getBestTest() {
        /**
         * This function returns the best possible test case, i.e. the one
         * that can cover the most number of uncovered pairs.
         *
         * Returns: List<String> bestCase - The best possible test case.
         * */
        LinkedList<String> arr = new LinkedList<String>();
        getTestcases(0, arr, 0);

        possibleTestCases.sort((a,b) -> b.score - a.score); // sort in
         // descending order of scores
        return possibleTestCases.get(0).testCase;
    }
}