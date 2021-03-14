package hw1.problem1;
import java.util.HashSet;
import java.util.Set;
public class Sets {
    public static Set intersection(Set s1, Set s2) {
    // Effects: Return a (non null) Set equal to the intersection of sets s1 and s2
    // A null argument is treated as an empty set
        if (s1 == null || s2 == null) {
            return new HashSet();
        }

        // You do NOT need to write this method body, but it could start from this:
         Set r = new java.util.HashSet(s1 == null ? java.util.Collections.EMPTY_SET : s1);
         r.retainAll(s2 == null ? java.util.Collections.EMPTY_SET : s2);
         return r;
    }
}
