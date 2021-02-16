/*
        方法：并查集，将每对情侣抽象为一个节点，当情侣A的某人与情侣B的某人坐到一起时，则将节点A与B联通，说明节点A和节点B
             需要进行交换；然后讨论交换次数，当两对情侣互相错开时，需要交换1次，当三对情侣错开时，需要交换2次，当n对情侣
             错开时，需要交换n-1次。因此我们在联通整个图后，需要计算每个联通分量中的节点个数，需要交换的次数为联通分量中
             节点个数-1，当节点个数为1时，说明这对情侣已经牵手，不需要进行交换。我们利用并查集来进行联通操作，最后统计各
             个联通分量中的节点个数，获取最终结果。
        时间复杂度：nlogn
     */
    public int minSwapsCouples(int[] row) {
        int n = row.length/2;
        UnionFind unionFind = new UnionFind(n);
        for(int i=0;i<2*n;i+=2)
        {
            unionFind.union(row[i]/2,row[i+1]/2);                      //合并节点
        }
        HashMap<Integer,Integer> count = new HashMap<>();
        for(int i=0;i<n;++i)
        {
            int f = unionFind.find(i);
            count.put(f,count.getOrDefault(f,0)+1);              //统计各个联通分量中的节点个数
        }
        int res = 0;
        for(int f: count.keySet())
        {
            res+=count.get(f)-1;
        }
        return res;
    }
    private class UnionFind {                                               //并查集实现

        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {                                           //初始化
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int[] GetParent()
        {
            return parent;
        }

        public void union(int x, int y) {                                   //并操作
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if(rank[rootX]>rank[rootY])
            {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            rank[rootY]+=rank[rootX];
            parent[rootX] = rootY;
        }

        public int find(int x) {                                            //查与路径压缩
            if (x != parent[x])
            {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean isConnected(int x,int y)
        {
            return find(x)==find(y);
        }
    }
    /*
        方法2：广度优先，我们可以建图后利用广度优先遍历，在遍历的同时统计联通分量中的节点个数。
        时间复杂度：O(n)
     */
    public int minSwapsCouples2(int[] row) {
        int n = row.length/2;
        List<Integer>[] graph = new List[n];
        for(int i=0;i<n;++i)
            graph[i] = new ArrayList<>();
        for(int i=0;i<2*n;i+=2)                                              //建图
        {
            if(row[i]/2==row[i+1]/2)
                continue;
            graph[row[i]/2].add(row[i+1]/2);
            graph[row[i+1]/2].add(row[i]/2);
        }
        boolean[] visit = new boolean[n];
        int res = 0;
        for(int i=0;i<n;++i)
        {
            if(visit[i])
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visit[i] = true;
            int total = 1;                                                    //total统计联通分量中的节点个数
            while(!queue.isEmpty())
            {
                int now = queue.poll();
                for(int node:graph[now])
                {
                    if(!visit[node])
                    {
                        queue.offer(node);
                        visit[node] = true;
                        total++;
                    }
                }
            }
            res += total-1;
        }
        return res;
    }
