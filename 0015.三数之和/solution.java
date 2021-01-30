/*
        方法：排序+双指针，如果直接进行循环暴力求解，复杂度为O(n^3),如何将复杂度进行缩小，成为了比较关键的问题。
             首先考虑不重复的情况下，找出所有和为0的三元组，则可以利用排序+双指针的方法，第一层循环的相反数作为目
             标值，再利用双指针的方法寻找nums[left]+nums[right]==target的元素，这样就可以将复杂度从O(n^3)
             降低至O(n^2)。此题的情况只是多了一个不重复的要求，因此只需要在排序后，遍历到重复元素时跳过即可。
        时间复杂度O(n^2)，空间复杂度log(n)，空间用于排序。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);                                                      //首先对数组进行排序
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();                            //保存结果
        for(int first=0;first<len-2;++first)
        {
            if(first>0&&nums[first]==nums[first-1])                             //跳过重复元素
                continue;
            int target = -nums[first];                                          //左右指针的目标值
            int left = first + 1,right = len-1;
            while(left<right)
            {
                if(nums[left]+nums[right]==target)                              //满足要求则保存结果
                {
                    res.add(new ArrayList<>(Arrays.asList(nums[first],nums[left],nums[right])));
                    left++;
                    right--;                                                   //移动左右指针
                    while(left<right&&nums[left]==nums[left-1]) left++;        //跳过重复元素
                    while(left<right&&nums[right]==nums[right+1]) right--;     //跳过重复元素
                }
                else if(nums[left]+nums[right]<target)
                {
                    left++;                                                    //和小于目标值，左指针右移
                }
                else
                {
                    right--;                                                   //和大于目标值，右指针左移
                }
            }
        }
        return res;
    }
