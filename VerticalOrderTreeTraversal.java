// Time Complexity : O(n).
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : We do level order traversal and capture the range of width along with corresponding nodes so that we can iterate over that ranges and get the vertical
// order. We start by maintaining width and it's corresponding node values in a hashmap and capture the range with min and max variables. We perform BFS with the
// help of queue and increase the range by 1 for left child and compress it for right child. Finally, we iterate over range of width in ascending order and return the list of node values.


class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>(); //hashmap to store width and corresponding node values
        Queue<TreeNode> queue = new LinkedList<>(); //BFS queue
        Queue<Integer> widthQ = new LinkedList<>(); //Queue to capture width
        int min = Integer.MAX_VALUE;
        int max = 0;
        queue.add(root);
        widthQ.add(0);
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            int currWidth = widthQ.poll();
            if(!map.containsKey(currWidth)){
                map.put(currWidth, new ArrayList<>()); //add to hashmap
            }
            map.get(currWidth).add(curr.data);
            min = Math.min(min, currWidth); //calculate min range of width
            max = Math.max(max, currWidth); //calculate max range of width
            if(curr.left != null){
                queue.add(curr.left);
                widthQ.add(currWidth - 1); //expand the width for left child
            }
            if(curr.right != null){
                queue.add(curr.right);
                widthQ.add(currWidth + 1); //compress the width for right child
            }
        }

        for(int i = min; i<= max;i++){ //iterate over range of width
            result.add(map.get(i));
        }
        return result;
    }
}