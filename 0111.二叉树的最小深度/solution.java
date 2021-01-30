/*
        方法1：树形dp,通过题目描述可以知道，当前节点的最小深度，可以由其子节点得到。状态转移方程如下：
              1、若当前节点只存在左子树，则当前节点最小深度=左子树最小深度+1
              2、若当前节点只存在右子树，则当前节点最小深度=右子树最小深度+1
              3、若当前节点存在左右子树，则当前节点最小深度=min(左子树最小深度,右子树最小深度)+1
              根据上述状态转移方程即可求解，而叶子节点初始化为1。
     */
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;
        return BFS(root);
    }
    public int BFS(TreeNode root)
    {
        if(root.right==null&&root.left==null)                   //根据状态转移方程进行求解
            return 1;
        if(root.right==null&&root.left!=null)
            return BFS(root.left)+1;
        if(root.right!=null&&root.left==null)
            return BFS(root.right)+1;
        return Math.min(BFS(root.right),BFS(root.left))+1;
    }
