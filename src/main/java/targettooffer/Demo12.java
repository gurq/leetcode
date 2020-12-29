package targettooffer;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo12 {

    public static void main(String[] args) {
        // A B C E
        // S F C S
        // A D E F
        Demo12 demo12 = new Demo12();
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "ABCCED";
        // A B C E
        // S F E S
        // A D E E
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";
        System.out.println(demo12.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        return get(board, word);
    }

    public boolean get(char[][] board, String word) {
        // 从0,0开始每个点进递归，只要有一个返回true，结束循环返回true
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean success = recursive(board, word, 0, new HashSet<>(), i, j);
                if (success) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean recursive(char[][] board, String word, int index,
                             Set<String> xySet, int x, int y) {
        // index++后已经超出word的长度了（index从0开始的），结束递归
        if (index >= word.length()) {
            return true;
        }
        // 越界，结束递归
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        // 之前已经走过的节点，结束递归
        if (xySet.contains(x + "," + y)) {
            return false;
        }
        // 该节点并不等于word上的对应位，结束递归
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        // 前面都经过了，说明该节点是有用的节点，记录下来路径
        xySet.add(x + "," + y);
        // 准备找下一个节点
        index++;
        // 这里每个分支都要一个新的set，否则退回分支的时候set里面已经放东西了，会导致判断除错
//        // 向上
//        boolean up = recursive(board, word, index, new HashSet<>(xySet), x + 1, y);
//        // 向下
//        boolean down = recursive(board, word, index, xySet, x - 1, y);
//        // 向左
//        boolean left = recursive(board, word, index, xySet, x, y - 1);
//        // 向右
//        boolean right = recursive(board, word, index, xySet, x, y + 1);
        // 再分别判断上下左右的相邻节点是否成功
        return recursive(board, word, index, new HashSet<>(xySet), x + 1, y)
                || recursive(board, word, index, new HashSet<>(xySet), x - 1, y)
                || recursive(board, word, index, new HashSet<>(xySet), x, y - 1)
                || recursive(board, word, index, new HashSet<>(xySet), x, y + 1);
    }
}
