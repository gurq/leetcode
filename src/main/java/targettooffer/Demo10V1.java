package targettooffer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Demo10V1 {

    public static void main(String[] args) {
        Demo10V1 demo10V1 = new Demo10V1();
        System.out.println(demo10V1.fib(1));
        System.out.println(demo10V1.fib(2));
        System.out.println(demo10V1.fib(3));
        System.out.println(demo10V1.fib(4));
        System.out.println(demo10V1.fib(5));
        System.out.println(demo10V1.fib(95));
    }


    /**
     * 用哈希表存，虽然空间大，但时间快，空间换时间是我一向最推崇的
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 1) {
            return 0;
        }
        Map<Integer, BigInteger> map = new HashMap<>();
        map.put(1, BigInteger.ONE);
        map.put(2, BigInteger.ONE);
        BigInteger mapValue = get(n, map);
        return mapValue.mod(new BigInteger("1000000007")).intValue();
    }

    public BigInteger get(int n, Map<Integer, BigInteger> map){
        BigInteger mapValue = map.get(n);
        if (mapValue == null) {
            mapValue = get(n - 1 , map).add(get(n - 2, map)) ;
            map.put(n, mapValue);
        }
        return mapValue;
    }
}
