package interview.aila;

/**
 * @author gurq
 * @date 2020/12/3 7:28 下午
 */
public class Demo2 {

    public static void main(String[] args) {
        String o = "";
        z:
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 2; y++) {
                if (x == 1) break;
                if (x == 2 && y == 1) break z;
                o = o + x + y;
            }
        }
        System.out.println(o);
    }




}
