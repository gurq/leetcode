package interview;

/**
 * 题目来自【号称】阿里子公司：熙牛医疗
 *
 * 查找字符串没有出现重复字符的最长字串
 * 例如： qaawerq 最长字串 awerq， qqteqzxaa 最长字串 teqzxa
 *
 * @author gurq
 * @date 2020/11/29 2:06 上午
 */
public class Demo03 {
    public static void main(String[] args) {
        String parent = "qaawerq";
        System.out.println(getLongestChild(parent));
    }

    private static String getLongestChild(String parent) {
        if (parent == null && parent.length() < 1) {
            return null;
        }
        int left = 0;
        int right = 2;
        String temp = null;
        String child;
        while (right <= parent.length()) {
            if (temp != null) {
                int repeat = temp.indexOf(String.valueOf(parent.charAt(right - 1)));
                if (repeat != -1 && repeat < right - 1) {
                    left += repeat + 1;
                    right += repeat + 1;
                    continue;
                }
            }

            child = parent.substring(left, right);

            int index = parent.indexOf(child);
            int last = parent.lastIndexOf(child);
            if (last > index) {
                left++;
            } else {
                temp = child;
            }
            right++;
        }
        if (temp == null) {
            return String.valueOf(parent.charAt(0));
        }
        if (temp.equals(parent)) {
            return parent.substring(0, parent.length() - 1);
        }
        return temp;
    }
}
