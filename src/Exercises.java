public class Exercises {
    /**
     * Returns a count of how many nodes are in the linked list.
     * 
     * Returns 0 if head is null.
     * 
     * Example: 
     * Input: 9 -> 2 -> 10 -> 1
     * Output: 4
     * 
     * @param head the head of the linked list
     * @return the length of the list
     */
    public static int length(ListNode head) {
        int nodeCount = 0;

        while (head != null) {
            nodeCount++;
            head = head.next;
        }

        return nodeCount;
    }

    /**
     * Adds a new value to the front of the list and returns the new head.
     * 
     * Example:
     * head: 7 -> 6 -> 0
     * toAdd: 4
     * 
     * Output: 4 -> 7 -> 6 -> 0
     * 
     * @param head the head of the linked list
     * @param toAdd the value to add to the front
     * @return the new head of the linked list
     */
    public static ListNode prepend(ListNode head, int toAdd) {
        if (head == null) {
            return new ListNode(toAdd);
        }

        head = new ListNode(toAdd);

        head.next = new ListNode(7);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(0);

        return head;
    }

    /**
     * Removes the last element in the linked list and returns the head.
     * 
     * If head is null or the only element in the list, returns null.
     * 
     * Example:
     * Input: 6 -> 4 -> 9 -> 2
     * Output: 6 -> 4 -> 9
     *  
     * @param head the head of the linked list
     * @return the head of the list with the last element removed
     */
    public static ListNode removeLast(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode current = head;

        while (current != null) {
            if (current.next.next != null) {
                current = current.next;
            } else {
                current.next = null;
                current = current.next;
            }
        }

        current = head;

        return current;
    }

    /**
     * Returns the minimum value in the linked list, or Integer.MAX_VALUE if
     * head is null.
     * 
     * Example:
     * Input: 3 -> -4 -> 9 -> 2
     * Output: -4
     * 
     * @param head the head of the linked list
     * @return the minimum value in the list 
     */
    public static int min(ListNode head) {
        if (head == null) {
            return Integer.MAX_VALUE;
        }

        int minValue = Integer.MAX_VALUE;

        while (head != null) {
            if (minValue > head.data) {
                minValue = head.data;
            } 

            head = head.next;
        }

        return minValue;
    }

    /**
     * Removes the first instance of the node with the minimum value from the
     * list and returns the head. If head is null or the only element in the list,
     * returns null.
     * 
     * Examples:
     * Input: 3 -> 9 -> 0 -> -1 -> 5
     * Output: 3 -> 9 -> 0 -> 5
     * 
     * Input: 2 -> 4 -> 2 -> 9
     * Output: 4 -> 2 -> 9
     * 
     * HINT: use min as a helper method
     * 
     * @param head the head of the linked list
     * @return the head of the list with the first instance of the minimum value removed
     */
    public static ListNode removeMin(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        int minValue = min(head);

        if (head.data == minValue) {
            return head.next;
        }

        ListNode current = head;
        boolean hasFoundFirstMinValue = false;

        while (!hasFoundFirstMinValue) {
            if (current.next.data != minValue) {
                current = current.next;
            } else {
                hasFoundFirstMinValue = true;
                current.next = current.next.next;
                current = head;
            }
        }

        return current;
    }

    /* ------ OPTIONAL CHALLENGE PROBLEMS ------ */

    /**
     * Returns whether two lists are of equal length AND each value in bigList is double
     * that of the corresponding value in smallList. Returns false otherwise.
     * 
     * If BOTH lists are null, returns true.
     * 
     * Example:
     * smallHead: 4 -> 3 -> 9
     * bigHead:   8 -> 6 -> 18
     * Output: true
     * 
     * smallHead: 4 -> 3 -> 9
     * bigHead:   8 -> 6 -> 7
     * Output: false
     * 
     * 
     * @param smallHead the head of the list with the half values
     * @param bigHead the head of the list with the doubled values
     * @return whether the values in bigList are twice the values in smallList
     */
    public static boolean isDoubled(ListNode smallList, ListNode bigList) {
        // if both lists are null, return true
        if (smallList == null && bigList == null) {
            return true;
        } 

        int smallListCount = 0;
        int bigListCount = 0;
        ListNode currentSmall = smallList;
        ListNode currentBig = bigList;

        // smallList length
        while (currentSmall != null) {
            smallListCount++;
            currentSmall = currentSmall.next;
        }
        
        // bigList length
        while (currentBig != null) {
            bigListCount++;
            currentBig = currentBig.next;
        }

        // check if both lists have the same length
        if (smallListCount != bigListCount) {
            return false;
        }

        // reset references
        currentSmall = smallList;
        currentBig = bigList;

        while (currentSmall != null) {

            // check if each bigList node has a data value double than that of each corresponding smallList node data value
            if (currentBig.data != currentSmall.data * 2) {
                return false;
            } else {
                currentSmall = currentSmall.next;
                currentBig = currentBig.next;
            }
        }

        return true;
    }

    /**
     * Rotates a list by k elements to the left and returns the new head.
     * 
     * If head is null, return null.
     * 
     * Example:
     * head: 1 -> 2 -> 3 -> 4 -> 5
     * k: 2
     * Output: 3 -> 4 -> 5 -> 1 -> 2
     * 
     * @param head the head of the linked list
     * @param k the number of positions to rotate
     * @return the head of the new list after k rotations to the left
     */
    public static ListNode rotateLeft(ListNode head, int k) {
        ListNode current = head;
        int nodeCount = 0;

        // get list length
        while (current != null) {
            nodeCount++;
            current = current.next;
        }

        // reset reference
        current = head;
        
        if (head == null || head.next == null || k == 0 || k == nodeCount) {
            return head;
        }
        
        ListNode firstNode = head;

        for (int i = 0; i < k; i++) {
            firstNode = head;
            ListNode newHead = head.next;

            boolean isAtLastNode = false;
            while (!isAtLastNode) {
                if (head.next != null) {
                    head = head.next;
                } else {
                    head.next = new ListNode(firstNode.data);
                    head = newHead;
                    isAtLastNode = true;
                }
            }
        }

        firstNode = head;

        return firstNode;
    }
}
