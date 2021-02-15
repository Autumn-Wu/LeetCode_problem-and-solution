/*
        方法：滑动窗口
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res=0,total=0;
        int start=0,len=nums.length;
        for(int i=0;i<len;++i)
        {
            while(i<len&&nums[i]==1)
                ++i;
            res = Math.max(i-start,res);
            start = i+1;
        }
        return res;
    }
