package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用哈希表优化斐波那契
 * @author gurq
 * @date 2020/11/16 1:03 上午
 */
public class Fib {
    public static void main(String[] args) {

        Map<Integer, Long> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            System.out.println(getFib(i, map));
        }

    }

    private static long getFib(int n, Map<Integer, Long> map) {
        if (n < 1) {
            return -1;
        }
        if (n < 3) {
            return 1;
        }
        return fib(n, map);
    }

    private static long fib(int n, Map<Integer, Long> map) {
        Long result = map.get(n);

        if (result != null) {
            return result;
        }

        if (n < 3) {
            result = 1L;
        } else {
            result = fib(n - 1, map) + fib(n - 2, map);
        }

        map.put(n, result);

        return result;
    }
}
