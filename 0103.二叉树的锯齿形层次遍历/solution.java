/*
        方法1：锯齿形层次遍历，其实就是将奇数层次的遍历进行逆序，只需要将层次遍历结果中，
              奇数层的遍历结果进行逆序，就可以达到锯齿遍历的效果。
     */

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        BFS(root,0,res);                                //进行层次遍历
        int len = res.size();
        for(int i=0;i<len;i++)                                //处理遍历结果
        {
            if(i%2==1)                                        //对于奇数层，进行逆序
            {
                Deque<Integer> stack = new ArrayDeque<>();    //利用栈先进后出的特点将结果逆序
                List<Integer> temp = res.get(i);
                for(int j=0;j<temp.size();++j)
                    stack.addLast(temp.get(j));
                for(int j=0;j<temp.size();++j)
                    temp.set(j,stack.pollLast());
                res.set(i,temp);
            }
        }
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
        方法2：对迭代版本的层次遍历进行修改，在利用一次性取出队列中的节点作为每一层的
              遍历结果的同时，需要做到将奇数层次的遍历结果逆序，在此算法中利用flag标
              志进行标记，当为true时正序，当为false时逆序。而正序与逆序的实现，使用
              的是双端队列deque，正序addLast,逆序addFirst，最后将结果转为List即可。
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)                                           //根节点为空直接返回
            return res;
        Queue<TreeNode> queue = new LinkedList<>();              //队列用于BFS
        queue.add(root);
        boolean flag = true;                                     //flag作为正序与逆序的标志，初始化为正序
        while(!queue.isEmpty())
        {
            Deque<Integer> deque = new ArrayDeque<>();
            int num = queue.size();                              //一次性去除队列中所有节点，为同一层遍历结果
            if(flag)
                for(int i=0;i<num;i++)
                {
                    TreeNode node = queue.poll();
                    deque.addLast(node.val);                     //若为正序遍历，利用双端队列addLast进行存储
                    if(node.left!=null)
                        queue.add(node.left);
                    if(node.right!=null)
                        queue.add(node.right);
                }
            else
                for(int i=0;i<num;i++)
                {
                    TreeNode node = queue.poll();
                    deque.addFirst(node.val);                    //若为逆序遍历，利用双端队列addFirst进行存储
                    if(node.left!=null)
                        queue.add(node.left);
                    if(node.right!=null)
                        queue.add(node.right);
                }
            flag=!flag;                                          //正序与逆序交替进行
            res.add(new LinkedList<>(deque));                    //将结果转化为List进行存储
        }
        return res;
    }
