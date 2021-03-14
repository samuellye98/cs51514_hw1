package hw1.problem1;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * This class contains 11 JUnit tests for the Sets class. The tests
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

 To ensure 3-wise coverage while satisfying the constraint of our partitions,
 we consider the following test cases:
    B1, C1, R1
    B2, C1, R1
    B3, C1, R1
    B1, C2, R1
    B2, C2, R1
    B3, C2, R1
    B1, C3, R1
    B2, C3, R1
    B3, C3, R1
    B3, C3, R2
    B3, C3, R3
 */

public class Test3Wise {

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
    public void test_B2C1R1() {
        s2 = null;
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B3C1R1() {
        s1.add(1);
        s1.add(2);
        s1.add(3);
        s2 = null;
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B1C2R1() {
        s1 = null;
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B2C2R1() {
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B3C2R1() {
        s1.add(1);
        s1.add(2);
        s1.add(3);
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B1C3R1() {
        s1 = null;
        s2.add(1);
        s2.add(2);
        s2.add(3);
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B2C3R1() {
        s2.add(5);
        s2.add(7);
        s2.add(8);
        assertEquals(res, classToTest.intersection(s1, s2));
    }

    @Test
    public void test_B3C3R1() {
        s1.add(8);
        s1.add(9);
        s1.add(10);
        s2.add(2);
        s2.add(6);
        s2.add(11);
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
