package interview.aila;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gurq
 * @date 2020/12/3 7:52 下午
 */
public class Demo4 {
    public static void main(String[] args) {
        String[] arr = {"not", "add", "dog", "acid", "add", "elf", "gap", "cat", "rat"};
        String word = "kjbhgh";
        System.out.println(get(Arrays.asList(arr), word));

    }

    private static List<String> get(List<String> list, String subString) {
        return subString == null ? null : list.stream()
                .filter(i -> i.contains(subString))
                .map(i -> new StringBuilder(i).reverse().toString())
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
    }

}
