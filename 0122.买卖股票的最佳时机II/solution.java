    /*
        方法1：动态规划，根据手中是否持有股票，定义两个不同的dp数组，dp_has[i]与dp_not[i]分别代表第i天交易
        结束时手中持有股票与手中没有股票时的最大利润，然后定义两个不同的状态转移方程：
            dp_has[i] = max(dp_has[i-1],dp_not[i-1]-prices[i]);
        即第i天手中持有股票下的最大利润，可以从前一天持有股票转移，也可以由前一天没持有股票，然后购买股票转移。
            dp_not[i] = max(dp_not[i-1],dp_has[i-1]+prices[i]);
        即第i天手中未持股下的最大利润，可以由前一天未持股转移，也可以由前一天持股将股票卖掉转移。
        最后返回dp_not的最后一个值即可，因为持股情况下肯定利润没有未持股高。
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp_has = new int[len];
        int[] dp_not = new int[len];
        dp_has[0]=0-prices[0];
        dp_not[0]=0;
        for(int i=1;i<len;i++)
        {
            dp_has[i]=Math.max(dp_has[i-1],dp_not[i-1]-prices[i]);
            dp_not[i]=Math.max(dp_not[i-1],dp_has[i-1]+prices[i]);
        }
        return dp_not[len-1];
    }
