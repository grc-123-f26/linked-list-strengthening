import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

/**
 * JUnit tests for Exercises linked-list methods.
 */
public class ExercisesTest {

    // ------ length() tests ------

    @Test
    public void testLengthOnNull() {
        assertEquals(0, Exercises.length(null),
            "length(null) should be 0");
    }

    @Test
    public void testLengthOnSingle() {
        ListNode single = buildList(5);
        assertEquals(1, Exercises.length(single),
            "length of " + listToString(single) + " should be 1");
    }

    @Test
    public void testLengthOnExample() {
        ListNode example = buildList(9, 2, 10, 1);
        assertEquals(4, Exercises.length(example),
            "length of " + listToString(example) + " should be 4");
    }

    // ------ prepend() tests ------

    @Test
    public void testPrependOnNull() {
        ListNode result = Exercises.prepend(null, 7);
        String expected = "7";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "prepend on null should produce \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testPrependOnNonNull() {
        ListNode head = buildList(7, 6, 0);
        ListNode result = Exercises.prepend(head, 4);
        String expected = "4 -> 7 -> 6 -> 0";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "prepend failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    // ------ removeLast() tests ------

    @Test
    public void testRemoveLastOnNull() {
        assertNull(Exercises.removeLast(null),
            "removeLast(null) should return null");
    }

    @Test
    public void testRemoveLastOnSingle() {
        ListNode head = buildList(5);
        ListNode result = Exercises.removeLast(head);
        assertNull(result,
            "removeLast on single-element list should return null");
    }

    @Test
    public void testRemoveLastExample() {
        ListNode head = buildList(6, 4, 9, 2);
        ListNode result = Exercises.removeLast(head);
        String expected = "6 -> 4 -> 9";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "removeLast failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testRemoveLastTwoElements() {
        ListNode head = buildList(1, 2);
        ListNode result = Exercises.removeLast(head);
        String expected = "1";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "removeLast on [1 -> 2] should produce \"" + expected + "\" but got \"" + actual + "\"");
    }

    // ------ min() tests ------

    @Test
    public void testMinOnNull() {
        assertEquals(Integer.MAX_VALUE, Exercises.min(null),
            "min(null) should be Integer.MAX_VALUE");
    }

    @Test
    public void testMinOnSingle() {
        ListNode head = buildList(42);
        assertEquals(42, Exercises.min(head),
            "min of " + listToString(head) + " should be 42");
    }

    @Test
    public void testMinExample() {
        ListNode example = buildList(3, -4, 9, 2);
        assertEquals(-4, Exercises.min(example),
            "min of " + listToString(example) + " should be -4");
    }

    @Test
    public void testMinAtHeadAndTail() {
        ListNode headMin = buildList(-5, 10, 7);
        assertEquals(-5, Exercises.min(headMin),
            "min of " + listToString(headMin) + " should be -5");

        ListNode tailMin = buildList(8, 1, -2);
        assertEquals(-2, Exercises.min(tailMin),
            "min of " + listToString(tailMin) + " should be -2");
    }

    // ------ removeMin() tests ------

    @Test
    public void testRemoveMinOnNull() {
        assertNull(Exercises.removeMin(null),
            "removeMin(null) should return null");
    }

    @Test
    public void testRemoveMinOnSingle() {
        ListNode head = buildList(7);
        ListNode result = Exercises.removeMin(head);
        assertNull(result,
            "removeMin on single-element list should return null");
    }

    @Test
    public void testRemoveMinExampleMiddle() {
        ListNode head = buildList(3, 9, 0, -1, 5);
        ListNode result = Exercises.removeMin(head);
        String expected = "3 -> 9 -> 0 -> 5";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "removeMin failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testRemoveMinAtHead() {
        ListNode head = buildList(1, 4, 2);
        ListNode result = Exercises.removeMin(head);
        String expected = "4 -> 2";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "removeMin when min is head: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testRemoveMinAtTailAndDuplicates() {
        ListNode head = buildList(2, 4, 2, 9);
        ListNode result = Exercises.removeMin(head);
        String expected = "4 -> 2 -> 9";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "removeMin with duplicates: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    // ------ isDoubled() tests ------

    @Test
    public void testIsDoubledBothNull() {
        assertTrue(Exercises.isDoubled(null, null),
            "isDoubled(null, null) should return true");
    }

    @Test
    public void testIsDoubledOneNull() {
        assertFalse(Exercises.isDoubled(buildList(1, 2), null),
            "isDoubled(non-null, null) should return false");
        assertFalse(Exercises.isDoubled(null, buildList(2, 4)),
            "isDoubled(null, non-null) should return false");
    }

    @Test
    public void testIsDoubledMatchingAndMismatched() {
        ListNode small = buildList(4, 3, 9);
        ListNode bigGood = buildList(8, 6, 18);
        assertTrue(Exercises.isDoubled(small, bigGood),
            "isDoubled(" + listToString(small) + ", " + listToString(bigGood) + ") should be true");

        ListNode bigBad = buildList(8, 6, 7);
        assertFalse(Exercises.isDoubled(small, bigBad),
            "isDoubled with wrong value should return false");
    }

    @Test
    public void testIsDoubledLengthMismatch() {
        assertFalse(Exercises.isDoubled(buildList(1,2,3), buildList(2,4)),
            "isDoubled with different lengths should return false");
    }

    // ------ rotateLeft() tests ------

    @Test
    public void testRotateLeftOnNull() {
        assertNull(Exercises.rotateLeft(null, 3),
            "rotateLeft(null, k) should return null");
    }

    @Test
    public void testRotateLeftOnSingle() {
        ListNode head = buildList(5);
        ListNode result = Exercises.rotateLeft(head, 10);
        String expected = "5";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft on single-element list should return the same list");
    }

    @Test
    public void testRotateLeftByZero() {
        ListNode head = buildList(1,2,3,4,5);
        ListNode result = Exercises.rotateLeft(head, 0);
        String expected = listToString(head);
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft by 0 should produce the same list");
    }

    @Test
    public void testRotateLeftTypical() {
        ListNode head = buildList(1,2,3,4,5);
        ListNode result = Exercises.rotateLeft(head, 2);
        String expected = "3 -> 4 -> 5 -> 1 -> 2";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testRotateLeftByLength() {
        ListNode head = buildList(50, -1, 8, 33, 17);
        ListNode result = Exercises.rotateLeft(head, 5);
        String expected = "50 -> -1 -> 8 -> 33 -> 17";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft by list length should return the same list");
    }

    @Test
    public void testRotateLeftGreaterThanLength() {
        ListNode head = buildList(5, 15, -10, 0, 30);
        ListNode result = Exercises.rotateLeft(head, 7); // 7 % 5 = 2
        String expected = "-10 -> 0 -> 30 -> 5 -> 15";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft by k > length failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

    @Test
    public void testRotateLeftByOne() {
        ListNode head = buildList(99, 42, 17, -8);
        ListNode result = Exercises.rotateLeft(head, 1);
        String expected = "42 -> 17 -> -8 -> 99";
        String actual = listToString(result);
        assertEquals(expected, actual,
            "rotateLeft by 1 failed: expected \"" + expected + "\" but got \"" + actual + "\"");
    }

        /** Helper to build a linked list from an array of values. */
    private ListNode buildList(int... values) {
        if (values == null || values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    /**
     * Helper to convert a list to a string, detecting cycles gracefully.
     * Format: data1 -> data2 -> ...
     */
    private String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) {
                sb.append(" -> ...(cycle detected)...");
                break;
            }
            visited.add(curr);
            sb.append(curr.data);
            curr = curr.next;
            if (curr != null) sb.append(" -> ");
        }
        return sb.toString();
    }

}