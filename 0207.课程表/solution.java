 /*
        方法1：广度优先遍历，本题其实就是询问根据先决条件得到的有向图是否能够完成拓扑排序，
              我们利用广度优先进行拓扑排序，然后判断拓扑排序得到的序列个数是否与课程数相
              等即可。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] EnterDegree = new int[numCourses];                                    //存储节点入度
        List<List<Integer>> Graph = new ArrayList<>();                              //存储依赖图
        for(int i=0;i<numCourses;i++)
            Graph.add(new ArrayList<>());
        for(int i=0;i<prerequisites.length;i++)
        {
            Graph.get(prerequisites[i][0]).add(prerequisites[i][1]);                //根据先决条件构造有向图
            EnterDegree[prerequisites[i][1]]++;
        }
        List<Integer> res = TopSort(EnterDegree,Graph);                             //进行拓扑排序
        return (res.size()==numCourses);
    }
    public List<Integer> TopSort(int[] EnterDegree,List<List<Integer>> Graph)
    {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<EnterDegree.length;i++)                                       //将入度为0的节点压入队列
        {
            if(EnterDegree[i]==0)
                queue.offer(i);
        }
        while(!queue.isEmpty())
        {
            int CurNode = queue.poll();
            res.add(CurNode);
            List<Integer> temp = Graph.get(CurNode);
            for(int node:temp)                                                      //更新入度，并将新的入度为0节点压入队列
            {
                if(--EnterDegree[node]==0)
                    queue.offer(node);
            }
        }
        return res;
    }
    /*
        方法2：深度优先遍历，能否完成拓扑排序的关键在于，有向图中是否存在环，
              若拓扑排序中存在环，则拓扑排序无法完成。
              我们可以定义一个节点的三种状态：
              1、未搜索，即当前节点未被遍历到。                 flag=0
              2、搜索中，即当前节点正在当前的遍历过程中。         flag=1
              3、已完成，即当前节点已经在之前的遍历过程中遍历过。   flag=-1
              则有环的条件是在搜索中状态下再次被遍历，则说明有向图中有环。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> Graph = new ArrayList<>();
        for(int i=0;i<numCourses;i++)
            Graph.add(new ArrayList<>());
        for(int i=0;i<prerequisites.length;i++)
            Graph.get(prerequisites[i][1]).add(prerequisites[i][0]);                //根据先决条件构造有向图
        int[] flag = new int[numCourses];
        for(int i=0;i<numCourses;i++)
        {
            if(!dfs(Graph,flag,i))                                                  //若找到环，直接返回false
                return false;
        }
        return true;
    }
    public boolean dfs(List<List<Integer>> Graph,int[] flag,int index)
    {
        if(flag[index]==-1) return true;
        if(flag[index]==1)  return false;                                           //若找到环，直接返回false
        flag[index] = 1;
        for(int j:Graph.get(index))
        {
            if(!dfs(Graph,flag,j))  return false;                                   //若找到环，返回false
        }
        flag[index] = -1;                                                           //搜索完成
        return true;
    }
