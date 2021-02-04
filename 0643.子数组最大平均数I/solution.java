/*
        方法1：滑动窗口，维护一个total变量，记录滑窗中所有数的总和，在滑窗移动的过程中
              加上新元素，减去旧元素，即可得到新的总和，然后记录最大平均数即可。
        时间复杂度：O(n)
     */
    public double findMaxAverage(int[] nums, int k) {
        int total = 0;
        int len = nums.length;
        double res = Integer.MIN_VALUE;
        for(int i=0;i<k;++i)
        {
            total+=nums[i];
        }
        res = Math.max((double)total/(double)k,res);
        for(int i=k;i<len;++i)
        {
            total-=nums[i-k];
            total+=nums[i];
            res = Math.max((double)total/(double)k,res);
        }
        return res;
    }
    /*
        方法2：前缀和，由于需要用到区间中的和，因此可以用前缀和的方法来O(1)地获取区间和。
        时间复杂度：O(n)
     */
    public double findMaxAverage2(int[] nums, int k) {
        int len = nums.length;
        double res = Integer.MIN_VALUE;
        int[] sums = new int[len+1];
        for(int i=1;i<len+1;++i)
            sums[i] = sums[i-1]+nums[i-1];
        for(int i=k;i<len+1;++i)
        {
            int total = sums[i]-sums[i-k];
            res = Math.max((double)total/(double)k,res);
        }
        return res;
    }
