/*
        方法：自身哈希，利用数组本身作为一个标记数组，相当于一个哈希表，用于标记元素i+1是否出现，
             利用正负来标记数组是否出现，nums[i]<0,代表元素i+1已经出现过。
        时间复杂度：O(n),空间复杂度：O(1)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i=0;i< nums.length;++i)
        {
            int num = Math.abs(nums[i]);
            if(nums[num-1]>0)
                nums[num-1] = -nums[num-1];
        }
        for(int i=0;i<nums.length;++i)
        {
            if(nums[i]>0)
                res.add(i+1);
        }
        return res;
    }
