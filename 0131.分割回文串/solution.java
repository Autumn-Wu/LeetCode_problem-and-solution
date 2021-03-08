/*
        方法：动态规划+回溯，根据题5.最长回文子串的思路，我们可以利用动态规划构建一个boolean类型dp数组，
             其中dp[i][j]代表的是以i下标开头，j下标结束的子串是否为回文串。然后我们利用回溯的方法进行
             子串的分割，当且仅当分割下来的子串为回文串时才进行分割，具体代码如下：
        时间复杂度:O(n^2*2^n)
     */
    List<List<String>> res;                                         //存储最终结果
    List<String> temp;                                              //暂时保存分割子串结果
    boolean[][] dp;                                                 //dp数组记录以i下标开头，j下标结束的子串是否为回文串
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        char[] str = s.toCharArray();
        int n = str.length;
        dp = new boolean[n][n];
        for(int i=0;i<n;++i)
            dp[i][i] = true;
        for(int len=1;len<n;++len)                                  //枚举长度
        {
            for(int i=0;i+len<n;++i)
            {
                int j = i+len;
                if(len==1&&str[i]==str[j])
                    dp[i][j] = true;
                else
                {
                    dp[i][j] = dp[i+1][j-1]&&str[i]==str[j];        //根据状态转移方程生成dp数组
                }
            }
        }
        dfs(s,0,n);                                           //进入回溯
        return res;
    }
    public void dfs(String s,int start,int n)
    {
        if(start==n)                                               //当起点为n时，说明我们已经将字符串分割为符合要求的子串
        {
            res.add(new ArrayList<String>(temp));
            return;
        }
        for(int i=start;i<n;++i)
        {
            if(dp[start][i])                                       //若进行分割的子串为回文串，则进入下一步分割
            {
                temp.add(s.substring(start,i+1));
                dfs(s,i+1,n);
                temp.remove(temp.size()-1);                  //进行回溯
            }
        }
    }
