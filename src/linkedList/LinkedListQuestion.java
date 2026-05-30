package linkedList;

import java.util.*;

public class LinkedListQuestion {
    /// ////////////////reverse pattern/////////////////////
    //206. Reverse Linked List
    private ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null;

        while(curr != null) {
            ListNode np = curr.next;
            curr.next = prev;
            prev = curr;
            curr = np;
        }
        return prev;
    }

    //25. Reverse Nodes in k-Group
    private ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 2 || !hasSufficient(head, k))   return head;

        ListNode curr = head, prev = null;
        int count = k;
        while(curr != null && count > 0) {
            ListNode np = curr.next;
            curr.next = prev;
            prev = curr;
            curr = np;
            count--;
        }
        head.next = reverseKGroup(curr, k);
        return prev;
    }
    private boolean hasSufficient(ListNode head, int k) {
        ListNode curr = head;
        int len = 0;
        while(curr != null) {
            len++;
            curr = curr.next;
            if(len == k)    return true;
        }
        return false;
    }

    /// /////////Slow and fast pointer////////////////////
    //876. Middle of the Linked List
    private ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;

    }

    //2095. Delete the Middle Node of a Linked List
    private ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode fast = head, slow = head, prev = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        slow.next = null;
        return head;
    }

    //141. Linked List Cycle
    private boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) return true;
        }
        return false;
    }

    //142. Linked List Cycle II
    private ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)   return null;
        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) break;
        }
        if(fast == null || fast.next ==  null)  return null;

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    ///////////////////Merge Pattern//////////
    //21. Merge Two Sorted Lists
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummyHead = new ListNode(-1);
        ListNode curr1 = list1, curr2 = list2, curr = dummyHead;

        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
                curr = curr.next;
                curr.next = null;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
                curr = curr.next;
                curr.next = null;
            }
        }
        if(curr1 != null) {
            curr.next = curr1;
        }
        if(curr2 != null) {
            curr.next = curr2;
        }
        return dummyHead.next;
    }

    // merge zig-zg
    private ListNode mergeTwoListsInZigZag(ListNode list1, ListNode list2) {

        ListNode dummyHead = new ListNode(-1);
        ListNode curr1 = list1, curr2 = list2, curr = dummyHead;

        while(curr1 != null && curr2 != null) {
            curr.next = curr1;
            curr1 = curr1.next;
            curr = curr.next;

            curr.next = curr2;
            curr2 = curr2.next;
            curr = curr.next;

            curr.next = null;
        }

        return dummyHead.next;
    }

    //23. Merge k Sorted Lists
    private ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)   return null;
        ListNode list1 = lists[0];

        for(int i = 1;  i < lists.length; i++) {
            ListNode list2 = lists[i];
            list1 = mergeTwoLists(list1, list2);
        }
        return list1;
    }

    //148. Sort List
    private ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    private ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)   return head;

        ListNode prev = middleNode1(head);
        ListNode m = prev.next;
        prev.next = null;

        ListNode list1 = mergeSort(head);
        ListNode list2 = mergeSort(m);
        return mergeTwoLists(list1, list2);
    }
    private ListNode middleNode1(ListNode head) {
        ListNode fast = head, slow = head, prev = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;

    }

    ///////////////////////////////////////
    //160. Intersection of Two Linked Lists
    private ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curr1 = headA, curr2 = headB;

        while(curr1 != curr2) {
            curr1 = curr1 == null ? headB : curr1.next;
            curr2 = curr2 == null ? headA : curr2.next;
        }
        return curr1;
    }

    /// ///////////////////////
    ///
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {

        Node curr = head;
        HashMap<Node, Node> map = new HashMap();
        // old node, new node mapping
        // make link with new node
        while(curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        Node newHead = map.get(head);
        curr = head;

        while(curr != null) {
            Node  oldNode = curr;
            Node  newNode = map.get(curr);

            newNode.next = map.get(curr.next);
            newNode.random = map.get(curr.random);

            curr = curr.next;
        }
        return newHead;

    }

}
