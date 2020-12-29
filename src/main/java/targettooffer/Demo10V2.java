package targettooffer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Demo10V2 {

    public static void main(String[] args) {
        Demo10V2 demo10V2 = new Demo10V2();
        System.out.println(demo10V2.numWays(1));
        System.out.println(demo10V2.numWays(2));
        System.out.println(demo10V2.numWays(3));
        System.out.println(demo10V2.numWays(4));
        System.out.println(demo10V2.numWays(5));
        System.out.println(demo10V2.numWays(95));
    }


    /**
     * 本质上，青蛙跳也是斐波那契问题
     * 在跳最后一步时，只有一次跳1级和一次跳2级的区别，所以最后一步是跳1级的可能+跳2级的可能
     * 也就是假如说跳的次数是n 即 f(n) = f(n - 1) + f(n - 2)
     * 只是起始点不同：
     * 跳1级台阶：1种
     * 跳2级台阶：2种
     * 跳三级台阶：3种
     * 跳四级台阶：5种
     * 
     * 用哈希表存，虽然空间大，但时间快，空间换时间是我一向最推崇的
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n < 0) {
            return 0;
        }
        Map<Integer, BigInteger> map = new HashMap<>();
        map.put(0, BigInteger.ONE);
        map.put(1, BigInteger.ONE);
        map.put(2, new BigInteger("2"));
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
