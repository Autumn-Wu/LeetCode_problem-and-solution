/*
        方法1：动态规划，维护一个dp数组，dp数组的含义是dp[i]表示以nums[i]作为结尾数字的最长
              严格递增子序列的长度，则在此含义下的状态转移方程为：
              dp[i+1] = max(dp[0:i]&&nums[0:i]<nums[i+1])+1
              即在保持严格递增的条件下能够获取到的最大长度+1。最后返回dp数组中的最大值即可。
              时间复杂度O(n^2),空间复杂度O(n)
     */
    public int lengthOfLIS(int[] nums)
    {
        int[] dp = new int[nums.length];                //构建dp数组
        int res=0;
        for(int i=0;i<nums.length;i++)
        {
            int max=0;
            for(int j=0;j<i;j++)                        //寻找0-i-1中保持严格递增下的最大长度
               if(nums[i]>nums[j]&&dp[j]>max)
                   max=dp[j];
            dp[i]=max+1;                                //dp[i] = 最大长度+1
        }
        for(int j=0;j<nums.length;j++)                  //返回dp数组中的最大值(因为此dp数组并非单调递增)
                if(dp[j]>res)
                    res=dp[j];
        return res;
    }
    /*
        方法2：在方法1中,由于定义的状态dp数组非单调，因此造成了复杂度的O(n^2)，如果能够
              找到单调的dp定义，那么就能够利用二分，从而降低复杂度至O(nlogn)。
              定义一个dp数组，表示长度为i的最长上升子序列的末尾元素的最小值，可以通过反证法
              证明此dp数组是单调的，在新遇到一个元素时，判断元素与与d[len]的关系，若当前元素
              比d[len]大，则d[++len]=nums[i],否则利用二分法在d中找满足d[i−1]<nums[j]<d[i]
              的下标i，然后更新d[i]=nums[j]，即找到第一个比nums[i]小的数d[k]，更新d[k+1]=nums[i].
              这样做即为贪心思路，在满足递增序列的同时，保证相邻元素尽可能接近。
              最终返回len作为结果，len为最长上升子序列的长度。
              时间复杂度O(nlogn),空间复杂度O(n)
     */
    public int lengthOfLIS2(int[] nums)
    {
        if(nums.length==0)
            return 0;
        int[] d = new int[nums.length+1];               //构建dp数组，长度为i的最长上升子序列的末尾元素的最小值
        int len = 1;
        d[len] = nums[0];                               //初始化d[1]
        for(int i = 1;i<nums.length;i++)
        {
            if(nums[i]>d[len])                          //若当前元素大于d[len]，则在末尾添加nums[i]
                d[++len]=nums[i];
            else                                        //否则利用二分寻找即找到第一个比nums[i]小的数d[pos]
            {
                int l=1,r=len,pos=0;
                while(l<=r)
                {
                    int mid=(l+r)>>1;
                    if(d[mid]<nums[i])
                    {
                        pos=mid;
                        l=mid+1;
                    }
                    else
                        r=mid-1;
                }
                d[++pos]=nums[i];                       //更改d[++pos]=nums[i]
            }
        }
        return len;
    }
