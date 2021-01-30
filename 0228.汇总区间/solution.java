/*
        方法：暴力，根据题目要求遍历数组nums，记录连续有序的起点终点，然后加入res结果中。
     */
    public List<String> summaryRanges(int[] nums) {
        int len = nums.length;
        List<String> res = new ArrayList<>();
        if(len==0)
            return res;
        int i=0;
        while(i<len)
        {
            int start = i;
            while(i<len-1&&nums[i+1]==nums[i]+1)
                ++i;
            int end = i;
            String temp;
            if(start==end)
                temp = Integer.toString(nums[start]);
            else
                temp = Integer.toString(nums[start])+"->"+Integer.toString(nums[end]);
            res.add(temp);
            ++i;
        }
        return res;
    }
