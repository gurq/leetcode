package targettooffer;

import java.util.Stack;

public class Demo09 {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();

    }

    /**
     * 存的时候只往in里存，取的时候只从out里取
     * 存的时候很简单，就一直往in里存就行，不用考虑别的
     * 但是取的时候要看了：
     * 1. 如果out不为空，直接返回
     * 2. 如果out是空的，in也是空的，返回-1
     * 3. 如果out是空的，in不为空，把in里的东西放进out里
     * 没有第四种可能了
     */
    static class CQueue {

        Stack<Integer> in;

        Stack<Integer> out;

        public CQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (!out.isEmpty()) {
                return out.pop();
            }
            if (in.isEmpty()) {
                return -1;
            }
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
            return out.pop();
        }
    }
}
