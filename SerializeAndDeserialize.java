// Time Complexity : O(n).
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : We serialize the given tree into a string with BFS and then deserialize the string using the same approach of using a queue. If we come across null,
// we append # to differentiate, else append the root value to the serialized string. While doing deserialization, when we come across # we assume it as null and
// move the pointer on the string ahead and continue parsing the charcater strings in it.

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        sb.append(root.val + ",");
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left == null) { //if left child is null, not adding to queue and appending as #
                sb.append('#').append(",");
            } else {
                queue.add(curr.left); //add to queue
                sb.append(curr.left.val + ","); //append the val to string builder
            }
            if (curr.right == null) { //if right child is null, not adding to queue and appending as #
                sb.append('#').append(",");
            } else {
                queue.add(curr.right); //add to queue
                sb.append(curr.right.val).append(","); //append the val to string builder
            }
        }
        sb.setLength(sb.length()-1); //remove trailing ,
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strArr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int idx = 0;
        TreeNode root = new TreeNode(Integer.parseInt(strArr[idx]));
        queue.add(root);
        idx++; //pointer on string
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(!strArr[idx].equals("#")){ //consider only not # character strings
                curr.left = new TreeNode(Integer.parseInt(strArr[idx]));
                queue.add(curr.left);
            }
            idx++;
            if(!strArr[idx].equals("#")){ //consider only not # character strings
                curr.right = new TreeNode(Integer.parseInt(strArr[idx]));
                queue.add(curr.right);
            }
            idx++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));