package hw1.problem3;

import java.util.List;

public class Tuple {
    /**
     * This class represents a tuple of (List<String testCase, int score),
     * where test case is a possible test case and score is the number of
     * uncovered pairs that this test case can cover.
     * */
    public final List<String> testCase;
    public final int score;

    public Tuple(List<String> testCase, int score) {
        this.testCase = testCase;
        this.score = score;
    }
}