/*
        方法1：深度优先遍历，将每一个下标为'1'的地方作为一个节点，这此题就转化为求一个图的
              联通分量个数，其中在矩阵中相邻的'1'元素相互联通。
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]=='1')
                {
                    res++;
                    dfs(grid,i,j,m,n);
                }
            }
        }
        return res;
    }
    public void dfs(char[][] grid,int i,int j,int m,int n)
    {
        grid[i][j] = '0';
        if(i>0&&grid[i-1][j]=='1')
            dfs(grid,i-1,j,m,n);
        if(j>0&&grid[i][j-1]=='1')
            dfs(grid,i,j-1,m,n);
        if(i<m-1&&grid[i+1][j]=='1')
            dfs(grid,i+1,j,m,n);
        if(j<n-1&&grid[i][j+1]=='1')
            dfs(grid,i,j+1,m,n);
    }
    /*
        方法2：广度优先搜索，同理，可以利用广度优先搜索求解联通分量个数。
     */
    public int numIslands2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]=='1')
                {
                    res++;
                    grid[i][j] = '0';                                           //注意需要在压入队列时设置，否则会造成死循环
                    queue.offer(new int[]{i,j});
                    while(!queue.isEmpty())
                    {
                        int[] index = queue.poll();
                        if(index[0]>0&&grid[index[0]-1][index[1]]=='1')
                        {
                            grid[index[0]-1][index[1]]='0';
                            queue.offer(new int[]{index[0]-1,index[1]});
                        }
                        if(index[1]>0&&grid[index[0]][index[1]-1]=='1')
                        {
                            grid[index[0]][index[1]-1]='0';
                            queue.offer(new int[]{index[0],index[1]-1});
                        }
                        if(index[0]<m-1&&grid[index[0]+1][index[1]]=='1')
                        {
                            grid[index[0]+1][index[1]]='0';
                            queue.offer(new int[]{index[0]+1,index[1]});
                        }
                        if(index[1]<n-1&&grid[index[0]][index[1]+1]=='1')
                        {
                            grid[index[0]][index[1]+1]='0';
                            queue.offer(new int[]{index[0],index[1]+1});
                        }

                    }
                }
            }
        }
        return res;
    }
