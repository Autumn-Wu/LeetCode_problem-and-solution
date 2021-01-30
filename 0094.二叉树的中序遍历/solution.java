/*
        方法1：递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        InOrder(root,res);
        return res;
    }
    private void InOrder(TreeNode root,List<Integer> res)
    {
        if(root!=null)
        {
            InOrder(root.left,res);
            res.add(root.val);
            InOrder(root.right,res);
        }
    }
    /*
        方法2：迭代，借助栈结构先进后出的特点，每遇到一个节点，将节点入栈，然后依次将节点的
              左节点入栈，节点替换为节点的左节点，直到左节点为空，然后弹栈，弹出栈顶节点后
              将节点值加入遍历结构，然后节点替换为节点的右节点，重复上述过程，就可以得到二
              叉树的迭代中序遍历。
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root!=null||!stack.isEmpty())
        {
            while(root!=null)
            {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
