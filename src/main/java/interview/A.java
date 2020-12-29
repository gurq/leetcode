package interview;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author gurq
 * @date 2020/12/3 8:09 下午
 */
public class A {
    public static void main(String[] args) {
        System.out.println(true && true || false);
        System.out.println(true && (true || false));
        System.out.println(false || false || true);
        System.out.println(false || (false || true));
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    }
}
