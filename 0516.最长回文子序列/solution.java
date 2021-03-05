/*
        方法：动态规划,定义动态数组dp[i][j]表示子串substring(i,j)的最长回文子序列长度，则状态转移方程如下:
                dp[i][j] = dp[i+1][j-1]+2,其中str[i]==str[j]
                dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]),其中str[i]==str[j]
             初始化时dp[i][i]=1,注，当str[i]和str[j]为相邻字符即j==i+1时，两者相等会导致dp[i][j] = dp[j][i]+2;
             而在dp数组中，所有i>j的情况下均为0，因为不符合子串的定义i<=j，因此这时候dp[i][j]=0+2=2，答案是正确的。
        时间复杂度：O(n^2)
     */
    public int longestPalindromeSubseq(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;++i)                                            //初始化dp数组
            dp[i][i] = 1;
        for(int len = 1;len<n;++len)                                    //枚举所有子串长度
        {
            for(int i=0;i+len<n;++i)
            {
                int j = i+len;                                          //枚举i，j
                if(str[i]==str[j])
                    dp[i][j] = dp[i+1][j-1]+2;
                else
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);         //根据状态转移方程求解
            }
        }
        return dp[0][n-1];
    }
