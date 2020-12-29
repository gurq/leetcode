package targettooffer;

public class Demo11 {

    /**
     * 这不就是简单的遍历找比前一个小的值？
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

}
