/*
        方法：排序
     */
    public int arrayPairSum(int[] nums) {
        int len=nums.length,res=0;
        Arrays.sort(nums);
        for(int i=0;i<len;i+=2)
        {
            res+=nums[i];
        }
        return res;
    }
