package targettooffer;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gurq
 * @date 2020/11/29 2:13 下午
 */
public class Demo04 {

    public static void main(String[] args) {
        int[][] arr = {{1,4,7,11,15}, {2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(findNumberIn2DArray(arr, 5));
    }

    /**
     * 思路：用x，y代表坐标，minX/maxX代表可取x坐标的范围，minY/maxY代表可取y坐标的范围
     * 然后用剪枝法不断逼近数，如果都剪完了还没的话，那就说明不存在
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int minX = 0;
        int maxX = matrix.length - 1;

        int minY = 0;
        int maxY = matrix[0].length - 1;

        int temp = -1;

        while (minX <= maxX && minY <= maxY) {
            // **这里每一块代码都可以抽成一个方法，但是为了看题解的时候看起来连贯，就不拆成子方法了**

            // 从第一行开始，如果这一行，最大的值都小于目标值，这一行整个都不用看了
            temp = matrix[minX][maxY];
            if (temp == target) {
                return true;
            }
            if (temp < target) {
                minX++;
                continue;
            }

            // 能走到这里，说明二维数组上面的横行都被剪完了，开始剪下面的横行
            // 再从最后一行开始，如果这一行，最小的值都大于目标值，这一行整个也都不用看了
            temp = matrix[maxX][minY];
            if (temp == target) {
                return true;
            }
            if (temp > target) {
                maxX--;
                continue;
            }

            // 能走到这里，说明二维数组下面的横行都被剪完了，开始剪竖行
            // **并且每剪一次竖行，由于先过上面的代码，所以要是有能剪的横行也会被剪掉**
            // 从第一列开始，如果这一列，最大的值都比目标值小，这一列就不用看了
            temp = matrix[maxX][minY];
            if (temp == target) {
                return true;
            }
            if (temp < target) {
                minY++;
            }

            // 能走到这里，说明二维数组左面的竖行都被剪完了，开始剪右边的竖行
            // 从最后一列开始，如果这一列，最小的值都比目标值大，这一列就不用看了
            temp = matrix[minX][maxY];
            if (temp == target) {
                return true;
            }
            if (temp > target) {
                maxY--;
            }
        }

        return false;
    }

}
