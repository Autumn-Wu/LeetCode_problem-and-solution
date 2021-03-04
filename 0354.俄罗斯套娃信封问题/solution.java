/*
        方法1：LIS，动态规划，最长上升子序列问题，利用解决最长上升子序列问题的动态规划方法
              进行求解，dp[i]代表第i个信封作为最外层信封时能够包含的最大信封数，状态转移方程如下：
                dp[i] = max(dp[j])+1 同时node[0]>temp[0]&&node[1]>temp[1]
        时间复杂度：O(n^2)
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        int len = envelopes.length;
        int[] dp = new int[len];
        for(int i=0;i<len;++i)
        {
            int max = 0;
            int[] node = envelopes[i];
            for(int j=0;j<i;++j)
            {
                int[] temp = envelopes[j];
                if(node[0]>temp[0]&&node[1]>temp[1]&&dp[j]>max)
                    max = dp[j];
            }
            dp[i] = max+1;
        }
        int res = 0;
        for(int i = 0;i<len;++i)
            res = Math.max(res,dp[i]);
        return res;
    }
    /*
        优化：在方法1中我们需要比较两个元素的大小情况来判断是否上升，我们可以通过一个技巧来只进行一次比较，
             首先我们根据w进行排序，则需要进行比较的时h，对于相同的w，我们要保证这些信封不能相互包含。在
             排序时，我们保证了信封中w的非严格升序，若我们将h按照升序或不排序，则有可能出现相同w情况下，
             信封的包含关系。当我们对h进行降序排序后，可以发现相同的w下，h呈现降序，则在比较h的情况下，
             信封不能相互包含。
             综上，我们对w进行升序排序，对h进行降序排序，这样就只需要比较h。
         时间复杂度：O(n^2)
     */
    public int maxEnvelopes2(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o2[1],o1[1]);
            }
        });
        int len = envelopes.length;
        int[] dp = new int[len];
        for(int i=0;i<len;++i)
        {
            int max = 0;
            int[] node = envelopes[i];
            for(int j=0;j<i;++j)
            {
                int[] temp = envelopes[j];
                if(node[1]>temp[1]&&dp[j]>max)
                    max = dp[j];
            }
            dp[i] = max+1;
        }
        int res = 0;
        for(int i = 0;i<len;++i)
            res = Math.max(res,dp[i]);
        return res;
    }
    /*
        方法2：LIS的二分查找动态规划，将方法1的优化利用LIS中的二分查找动规进行求解。
        时间复杂度：O(nlogn)
     */
    public int maxEnvelopes3(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o2[1],o1[1]);
            }
        });
        int len = envelopes.length;
        int[] dp = new int[len+1];
        int index = 1;
        dp[index] = envelopes[0][1];
        for(int i=1;i<len;++i)
        {
            int num = envelopes[i][1];
            if(num>dp[index])
                dp[++index] = num;
            else
            {
                int left=1,right=index,pos=0;
                while(left<=right)
                {
                    int mid = (left+right)>>1;
                    if(mid>num)
                        right = mid-1;
                    else
                    {
                        left = mid + 1;
                        pos = mid;
                    }
                }
                dp[++pos] = num;
            }
        }
        return index;
    }
