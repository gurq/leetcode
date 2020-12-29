package targettooffer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 */
public class Demo07 {

    public static void main(String[] args) {
        Demo07 demo07 = new Demo07();
//        int[] preorder = {3,9,20,15,7};
//        int[] inorder = {9,3,15,20,7};
        int[] preorder = {1,2,3};
        int[] inorder = {3,2,1};
        TreeNode treeNode = demo07.buildTree(preorder, inorder);
        int a = 1;
    }

    /**
     * 先转成list，方便用indexof，array没有indexof实在痛苦
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorderList = Arrays.stream(preorder).boxed().collect(Collectors.toList());
        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        return buildTree(null, preorderList, inorderList);
    }

    /**
     * 思路：
     * 首先先明确前序遍历，中序遍历的特点：
     * 只看局部，前序遍历：   根节点+左子节点+右子节点
     * 只看局部，中序遍历：   左子节点+根节点+右子节点
     * 所以思路就是不断的递归，利用前序遍历的第一个就是根节点的特点，
     * 来切割中序遍历，就能找到左子节点的段
     * 根据中序遍历左子节点的段的长度，在前序遍历上又能找到前序遍历上的左子节点的段
     * 根据切割出来的两个左子节点段，作为参数递归，
     * 不停的切割子节点，关心局部，直到找到子叶节点，整棵树即可构建完成
     *
     * @param treeNode
     * @param preorderList
     * @param inorderList
     * @return
     */
    public TreeNode buildTree(TreeNode treeNode,
                              List<Integer> preorderList,
                              List<Integer> inorderList) {
        // 除错，终止递归
        if (preorderList == null || preorderList.size() < 1
                || inorderList == null || inorderList.size() < 1) {
            return treeNode;
        }
        // 除错，开始递归
        if (treeNode == null) {
            treeNode = new TreeNode(preorderList.get(0));
            buildTree(treeNode, preorderList, inorderList);
        }
        /**
         * 先找左子树
         */
        // 找中序遍历上，根节点的位置
        int rootIndex = inorderList.indexOf(treeNode.val);
        // 切割出左子节点段
        List<Integer> leftInorderList = inorderList.subList(0, rootIndex);
        // 判断左子节点段的长度
        if (leftInorderList.size() == 0) {
            // 左子叶节点处理完毕，开始处理右子节点
        } else if (leftInorderList.size() == 1) {
            // 左子节点只有一个，说明这是叶子节点，直接赋值，然后开始处理右子节点
            treeNode.left = new TreeNode(leftInorderList.get(0));
        } else {
            /**
             * 左子节点有多个，开始关心这个局部，重新找到当前这个局部的前序遍历和中序遍历，作为参数进行下一次递归
             */
            // 对前序遍历来说，左子节点段即为从1（子节点的根节点永远在第一个）开始，截取长度就是该段的长度
            List<Integer> leftPreorderList = preorderList.subList(1, 1 + leftInorderList.size());
            // 进入递归时，只关心要递归的东西，该次递归的根节点即为前序遍历的第一个元素
            treeNode.left = new TreeNode(leftPreorderList.get(0));
            // 确定好了下次递归要关心的该左子节点段的：根节点、前序遍历、后序遍历以后，开始递归
            buildTree(treeNode.left, leftPreorderList, leftInorderList);
        }
        /**
         * 代码运行到这里，对左子节点段的处理已经完毕，用同样思路处理右子节点段
         */
        // 切割出右子节点段
        List<Integer> rightInorderList = inorderList.subList(rootIndex + 1, inorderList.size());
        // 判断右子节点段的长度
        if (rightInorderList.size() == 0) {
            // 右子叶节点处理完毕，可以结束递归了
        } else if (rightInorderList.size() == 1) {
            // 右子节点只有一个，说明这是叶子节点，直接赋值，可以结束递归了
            treeNode.right = new TreeNode(rightInorderList.get(0));
        } else {
            /**
             * 右子节点有多个，开始关心这个局部，重新找到当前这个局部的前序遍历和中序遍历，作为参数进行下一次递归
             */
            // 对前序遍历来说，右子节点段即为从最后一个元素，到该段的长度的元素
            List<Integer> rightPreorderList = preorderList.subList(preorderList.size() - rightInorderList.size(), preorderList.size());
            // 进入递归时，只关心要递归的东西，该次递归的根节点即为前序遍历的第一个元素
            treeNode.right = new TreeNode(rightPreorderList.get(0));
            // 确定好了下次递归要关心的该右子节点段的：根节点、前序遍历、后序遍历以后，开始递归
            buildTree(treeNode.right, rightPreorderList, rightInorderList);
        }
        return treeNode;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
