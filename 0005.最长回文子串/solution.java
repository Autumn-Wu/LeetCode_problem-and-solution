/*
        方法1：动态规划，对于一个回文串，我们将其头尾元素去除，则其仍然是一个回文串。
              最主要的状态转移方程，也就是当字符串长度大于2时：
                 dp[i][j] = (dp[i+1][j-1]&&str[i]==str[j]);
              即当且仅当当前字符串头尾元素相等且去除头尾元素后仍为回文串时，此字符串为回文串。
              考虑特殊情况，当字符串长度小于等于2时：
                当字符串中仅有一个元素时， 其为回文串：
                    dp[i][j] = true,j-i==0;
                当字符串中仅有两个元素时，需要这两个元素为相同元素时，为回文串：
                    dp[i][j] = (str[i]==str[j]),j-i==1;
              具体代码如下：
        时间复杂度:O(n^2)
     */
    public String longestPalindrome(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];                         //判断i,j头尾的字符串是否为回文串
        for(int len=0;len<n;++len)                                  //枚举每种长度的字符串
        {
            for(int i=0;i+len<n;++i)
            {
                int j = i+len;                                      //i,j分别为头尾下标
                if(len==0)                                          //仅有一个元素时
                    dp[i][j]=true;
                else if(len==1)                                     //两个元素时
                    dp[i][j] = (str[i]==str[j]);
                else                                                //两个以上元素时
                    dp[i][j] = (dp[i+1][j-1]&&str[i]==str[j]);
                if(dp[i][j]&&len+1>res.length())                    //更新最长回文串
                    res = s.substring(i,j+1);
            }
        }
        return res;
    }
    /*
        方法2:中心拓展，方法2逆过程，从回文中心开始拓展，当且仅当头尾元素相同时可以继续拓展。
        时间复杂度：O(n^2)
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);                     //长度为1的中心拓展
            int len2 = expandAroundCenter(s, i, i + 1);           //长度为2的中心拓展
            int len = Math.max(len1, len2);
            if (len > end - start) {                                   //更新最长回文串
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {  //当且仅当头尾元素相等时继续拓展
            --left;
            ++right;
        }
        return right - left - 1;
    }
