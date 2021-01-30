/*
        方法1：递归BFS，在BFS递归的过程中记录每一次递归的深度depth，在访问子节点时，
              depth = depth+1，然后根据不同深度存储最终的遍历结果。
     */
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<>();
        BFS(root,0,res);                                  //进入BFS,初始深度为0，与数组第一个index=0对应
        return res;
    }
    public void BFS(TreeNode root,int depth,List<List<Integer>> res)
    {
        if(root==null)                                          //空节点直接返回
            return;
        if(res.size()==depth)                                   //对与未出现的深度数组，创建新数组
        {
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            res.add(temp);
        }
        else
        {
            List<Integer> temp = res.get(depth);
            temp.add(root.val);
            res.set(depth,temp);                                //根据深度保存遍历结果
        }
        BFS(root.left,depth+1,res);
        BFS(root.right,depth+1,res);                      //依次遍历左右子节点
    }
    /*
        方法2：迭代BFS，利用队列进行迭代BFS，关键问题是：如何根据深度保存每一层的
              遍历结果。平时在进行BFS时，队列是一个一个弹出节点，然后将节点的左右
              子节点放入队列中，而在层次遍历中，我们可以观察到，若我们每次将队列中
              所有的节点取出，那么这所有的节点是同一层的，然后将这一层节点的子节点
              全部压入队列中，那么下次取出的节点也是同一层的。因此我们只需要在队列
              弹出节点时，将节点全部弹出，那么这些节点为同一层次。
     */
    public List<List<Integer>> levelOrder2(TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)                                          //若根节点为空，则直接返回
            return res;
        queue.add(root);                                        //否则将根节点放入队列中
        while(!queue.isEmpty())
        {
            List<Integer> temp = new ArrayList<>();
            int num = queue.size();                             //每次将队列中所有的节点取出
            for(int i=0;i<num;++i)
            {
                TreeNode node = queue.poll();
                temp.add(node.val);                             //此时的节点均为同一层，保存遍历结果
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);                      //将所有子节点放入队列中
            }
            res.add(temp);                                      //保存当前层的遍历结果
        }
        return res;
    }
