/*
        方法：排序+双指针，此题同LeeCode的第15题，15. 三数之和。只是多了一层循环，并将
             左右指针和的目标值改为了target-nums[first]-nums[second]，使用同样类似
             的方法即可求解，然后注意跳过重复元素即可。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();                            //保存结果
        for(int i=0;i<len-3;++i)                                                //第一层循环
        {
            if(i>0&&nums[i]==nums[i-1])                                         //跳过重复元素
                continue;
            for(int j=i+1;j<len-2;++j)                                          //第二层循环
            {
                if(j>i+1&&nums[j]==nums[j-1])                                   //跳过重复元素
                    continue;
                int tar = target-nums[i]-nums[j];                               //左右指针和的目标值
                int left = j+1,right = len -1;
                while(left<right)
                {
                    if(nums[left]+nums[right]==tar)                             //若满足要求
                    {
                        res.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[left],nums[right])));
                        left++;
                        right--;                                                //移动左右指针
                        while(left<right&&nums[left]==nums[left-1]) left++;
                        while(left<right&&nums[right]==nums[right+1]) right--;  //跳过重复元素
                    }
                    else if(nums[left]+nums[right]<tar)
                    {
                        left++;                                                 //小于目标值则左指针右移
                    }
                    else
                    {
                        right--;                                                //大于目标值则右指针左移
                    }
                }
            }
        }
        return res;
    }
