package targettooffer;

import java.util.Stack;

public class Demo06 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(2);
        head.next = second;
        second.next = third;

    }

    /**
     * 利用栈先进后出的特性就行
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode item = head;
        while (item != null) {
            stack.push(item.val);
            item = item.next;
        }
        int[] arr = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }

      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }
}
