package targettooffer;

public class Demo13 {

    public static void main(String[] args) {
        Demo13 demo13 = new Demo13();
//        System.out.println(demo13.movingCount(2, 3, 1));
//        System.out.println(demo13.movingCount(3, 1, 0));
        System.out.println(demo13.movingCount(36, 11, 15));
    }

    public int movingCount(int m, int n, int k) {
        // 初始化数组，默认相当于都是false
        boolean[][] arr = new boolean[m][n];
        // 递归
        dfs(arr, k, 0, 0);
        // 对这个数组递归完了以后，统计为true的count
        int total = 0;
        for (boolean[] booleans : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (booleans[j]) {
                    total++;
                }
            }
        }
        return total;
    }

    public void dfs(boolean[][] arr, int k, int x, int y) {
        // 越界，结束递归
        if (x < 0 || x >= arr.length || y < 0 || y >= arr[0].length) {
            return;
        }
        // 已经为true了，说明被其他的方法栈修改过了，结束递归
        if (arr[x][y]) {
            return;
        }
        // 判断该坐标是否合法
        boolean isRight = isRight(x, y, k);
        // 不合法，结束递归
        if (!isRight) {
            return;
        }
        // 合法，该坐标点置为true
        arr[x][y] = true;
        // 往下和右开始递归（上和左就不用了，从那边过来的）
        dfs(arr, k, x + 1, y);
        dfs(arr, k, x, y + 1);
        // 其实这种做法应该是不能解决类似于迷宫那样的复杂路径的。。。
    }

    public boolean isRight(int x, int y, int k) {
        int total = 0;
        while (x > 0) {
            total += x % 10;
            x /= 10;
        }
        while (y > 0) {
            total += y % 10;
            y /= 10;
        }
        return total <= k;
    }

}
