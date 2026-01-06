// Time Complexity : O(k+h) - k is treenodes in the range and h is the height of the tree
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : Yes
// Approach : As it's a BST, inorder traversal helps in getting the tree node elements in ascending order. If the root value is greater than low, low will be on the
// left subtree and if root value is less than high, high will be on the right subtree. This condition helps in not visting all the nodes and if the root values falls in
// the range, we add up the values to the result.

class Solution {
    int result;

    public int rangeSumBST(TreeNode root, int low, int high) {
        inorder(root, low, high);
        return result;
    }

    private void inorder(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.val > low) { //low is on left subtree
            inorder(root.left, low, high);
        }

        if (root.val >= low && root.val <= high) { //required range
            this.result += root.val;
        }
        if (root.val < high) { //high is on right subtree
            inorder(root.right, low, high);
        }
    }
}