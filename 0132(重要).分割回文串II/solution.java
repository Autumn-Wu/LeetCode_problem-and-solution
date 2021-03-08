/*
        方法：动态规划，根据题5.最长回文子串的思路，我们可以利用动态规划构建一个boolean类型dp数组，
             其中dp[i][j]代表的是以i下标开头，j下标结束的子串是否为回文串。然后我们再定义给一个
             动态规划数组res，其中res[i]代表子串s[0:i]分割为每个子串都为回文串的最少分割次数，
             则状态转移方程：
                res[i] = 0, if dp[0][i] = true;
                res[i] = min(res[0:i-1]+1,if dp[k+1][i]=true);
             即若s[0:i]为回文串，则不需要进行分割，res[i]=0;
             否则我们尝试将字符串s[0:i]分割为两个子串，根据拓展的方向，i之前的子串我们都已经知道他们
             的res[i]，则我们只需要保证分割后第二个子串为回文串，因为第一个子串的res[i]已知，然后
             取最小值即可。
        时间复杂度:O(n^2)
     */
    public int minCut(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
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
        int[] res = new int[n];
        for(int i=0;i<n;++i)
        {
            if(dp[0][i])                                            //若当前串已经为回文串，则res[i]=0；
                continue;
            else
            {
                int min = i+1;
                for(int k=0;k<i;++k)
                {
                    if(dp[k+1][i]&&res[k]+1<min)
                        min = res[k]+1;
                }
                res[i] = min;                                       //根据状态转移方程进行拓展
            }
        }
        return res[n-1];
    }
