/*
        方法：双哈希表，由题意可知，与nums拥有相同大小的度的最短连续子数组，一定是以某个数第一次出现
             位置为开头，最后一次出现位置为结尾，因此我们需要统计每个数出现的次数，以及其第一次出现
             的位置和最后出现的位置，我们利用两个哈希表进行存储(也可以用一个)，然后出现次数最多的数
             的频次即为数组的度，对应的最短长度为该数开头到该数结尾的长度。若次数最多的数有多个，则
             选取长度最短的。
     */
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> count = new HashMap<>();
        HashMap<Integer,int[]> map = new HashMap<>();
        for(int i=0;i<nums.length;++i)
        {
            int time = count.getOrDefault(nums[i],0);
            count.put(nums[i],time+1);                                  //记录数的频次
            if(time==0)
                map.put(nums[i],new int[]{i,i});                        //记录数的开头和结尾
            else
                map.get(nums[i])[1]=i;
        }
        int res = Integer.MAX_VALUE,max = 0;                            //res保存最短长度，max保存数组的度
        for(int key:count.keySet())
        {
            if(count.get(key)>max)
            {
                res = map.get(key)[1]-map.get(key)[0]+1;
                max = count.get(key);
            }
            else if(count.get(key)==max)
                res = Math.min(res,map.get(key)[1]-map.get(key)[0]+1);  //存在相同的情况下，则取最短长度
        }
        return res;
    }
