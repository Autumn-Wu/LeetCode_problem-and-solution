/*
        方法1：动态规划，构建动态规划数组dp，dp[i]代表的含义以prices[i]作为结尾的子数组的最大利润，
        状态转移方程:dp[i]=max(dp[i-1],prices[i]-min(prices[0:i]));
        即第i天的最大利润，可能为前一天的最大利润，也可能为当前股票价格减去之前最小股票价格。
        为了每次不需要遍历prices[0:i]，构建min_prices数组，min_prices[i]代表第i天前的最低股票价格。
        在遍历prices的同时，更行dp数组与min_prices数组。
    */
    public static int onecemaxProfit1(int[] prices)
    {
        int len = prices.length;
        if(len==0)
            return 0;
        int[] min_prices= new int[len];                             //min_prices存储此前的最小价格
        int[] dp = new int[len];                                    //dp数组存储prices[i]作为结尾的子数组的最大利润，
        min_prices[0]=prices[0];
        dp[0]=0;
        for(int i=1;i<len;i++)
        {
            dp[i]=Math.max(dp[i-1],prices[i]-min_prices[i-1]);      //根据状态转移方程更新dp[i]
            min_prices[i]=Math.min(min_prices[i-1],prices[i]);      //更新min_prices
        }
        return dp[len-1];
    }
    /*
        优化:每一次更新dp数组与min_prices数组只需要用到dp[i-1]以及min_prices[i-1]，而不需要用到之
        前的数组元素我们只需要利用两个变量，记录这两个不断变化的值，就可以不用数组存储。节省空间。
    */
    public static int onecemaxProfit2(int[] prices)
    {
        int len = prices.length;
        if(len==0)
            return 0;
        int min_prices = prices[0];                                  //利用变量存储此前的最小价格
        int max_profit= 0;                                          //利用变量存储此前最大利润
        for(int i=1;i<len;i++)
        {
            max_profit=Math.max(max_profit,prices[i]-min_prices);   //利用状态转移更新最大利润
            min_prices=Math.min(min_prices,prices[i]);              //更新最小价格
        }
        return max_profit;
    }
