package hw1.problem1;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * This class contains 4 JUnit tests for the Sets class. The tests
 * are derived from IDM (input domain modeling).
 * The method tested is intersection(Set s1, Set s2)
 * The following characteristics have been identified and are used to generate tests for the methods:
     Characteristic 1: Type of s1
     B1: s1 = null
     B2: s1 = {}
     B3: s1 has at least one element

     Characteristic 2: Type of s2
     C1: s2 = null
     C2: s2 = {}
     C3: s2 has at least one element

     Characteristic 3: Relation between s1 and s2
     R1: s1 and s2 share no common elements, including if s1/s2 is null
     or the empty set.
     R2: s1 and s2 represent the same non-empty set.
     R3: s1 and s2 share some common elements but not all.

 To ensure Each Choice coverage while satisfying the constraint of our
 partitions, we consider the following test cases, whereby one value from
 each block for each characteristic must be used in at least one test case:
    B1, C1, R1
    B2, C2, R2
    B3, C3, R2
    B3, C3, R3
 */

//    ------------------------------|-------------|-------------|----------------|
//            (h)    Each Choice, not Pairwise  | Value of s1 | Value of s2 | Expected Output|
//            ------------------------------|-------------|-------------|----------------|
//            B1 C1 R1                | null        | null        |       {}       |
//            ------------------------------|-------------|-------------|----------------|
//            B2 C2 R1                | {}          | {}          |       {}       |
//            ------------------------------|-------------|-------------|----------------|
//            B3 C3 R2                | {23,67,99}  | {23,67,99}  |   {23,67,99}   |
//            ------------------------------|-------------|-------------|----------------|
//            B3 C3 R3                | {8,23,67}   | {3,23,67}   |   {23,67}      |
//            ------------------------------|-------------|-------------|----------------|

public class TestEachChoice {

    private Set s1;       // test fixture
    private Set s2;       // test fixture
    private Set res;      // test fixture
    private Sets classToTest = new Sets();

    @Before
    public void setUp() // set up test fixture
    {
        s1 = new HashSet<Integer>();
        s2 = new HashSet<Integer>();
        res = new HashSet<Integer>();
    }

    @Test
    public void test_B1C1R1() {
        s1 = null;
        s2 = null;
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B2C2R1() {
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B3C3R2() {
        s1.add(23);
        s1.add(67);
        s1.add(99);
        s2.add(23);
        s2.add(67);
        s2.add(99);
        res.add(23);
        res.add(67);
        res.add(99);
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B3C3R3() {
        s1.add(8);
        s1.add(23);
        s1.add(67);
        s2.add(3);
        s2.add(23);
        s2.add(67);
        res.add(23);
        res.add(67);
        assertEquals(res, classToTest.intersection(s1, s2));
    }

}


