/*
        方法：贪心算法，假设对于[1,x-1]区间，我们已经能够完全覆盖，那么新遍历到一个数nums[index]，若nums[index]>x
             那么新增的覆盖区间为[nums[index]+1,nums[index]+x-1],则我们可以发现区间[x,nums[index]]
             是没有被遍历到的，因此对于这种情况，我们的策略是将x补充到nums数组中，那么新增覆盖区间为[x+1,2x-1]
             总体覆盖区间变为[1,2x-1]。总体策略为：初始化x=1，此时[1,x-1]为空空间，然后进入迭代，迭代结束的标志
             是正整数n被区间[1,x-1]覆盖，迭代过程中若nums[index]<=x，那么不会出现未覆盖区间，则更新总覆盖区间
             为[1,x+nums[index]-1],index++;否则需要将x补充到nums数组中，新总覆盖区间为[1,2x-1];
     */
    public int minPatches(int[] nums, int n) {
        long x = 1;
        int res=0;                                  //记录补充个数
        int index=0;                                //记录数组下标
        int len=nums.length;
        while(x-1<n)
        {
            if(index<len&&nums[index]<=x)           //若数组元素nums[index]<=x，那么不会出现未覆盖区间
            {
                x=x+nums[index];                    //更新覆盖范围
                index++;
            }
            else
            {
                x=2*x;                              //否则将x补充到数组中
                res++;
            }

        }
        return res;
    }
