/*
        方法：贪心算法，首先需要明确的是最多只能改变一个元素，那么严格递增序列的总长度不能超过二，
             若超过二则无法变成一个递减数列，也就是说我们找到一个严格递增位置时，进行修改后需要
             立即判断其是否变为非递减序列。其次需要注意的是更改的位置，若nums[i]>nums[i+1]，
             根据贪心，我们要尽量避免影响后续元素和前面元素的递增关系，因此改变方式有两种：
                1.将nums[i]设置为nums[i+1]
                2.将nums[i+1]设置为nums[i]
             我们需要尝试两种方式，然后进行判断。具体代码如下。
        时间复杂度：O(n^2)
     */
    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len-1;++i)
        {
            int x = nums[i],y = nums[i+1];
            if(x>y)                                 //找到需要修改的位置
            {
                nums[i] = y;                        //进行第一种方式修改
                if(Check(nums))                     //进行非递减检查
                    return true;
                else
                {
                    nums[i] = x;                    //若第一种方式行不通，则尝试第二种方式
                    nums[i+1] = x;
                    return Check(nums);             //进行非递减检查
                }
            }
        }
        return true;
    }
    public boolean Check(int[] nums)
    {
        int len = nums.length;
        for(int i=0;i<len-1;++i)
        {
            if(nums[i]>nums[i+1])
                return false;
        }
        return true;
    }
    /*
        优化：在上述方法中，我们需要对整个数组进行判断，其实我们在进行上述方式修改时，
             1.若将nums[i]变换为nums[i+1]，则只有可能影响nums[i-1]与nums[i]的递增关系；
             2.若将nums[i+1]变换为nums[i],则只有可能影响nums[i+1]与nums[i+2]的递增关系；
             因此我们在进行修改时，只需要判断对应情况下的非递减即可，具体代码如下。
        时间复杂度：O(n)

     */
    public boolean checkPossibility2(int[] nums) {
        int len = nums.length;
        int total = 0;
        for(int i=0;i<len-1;++i)
        {
            int x = nums[i],y = nums[i+1];
            if(x>y)
            {
                total++;                        //total记录需要修改的次数，若大于1则直接返回
                if(total>1)
                    return false;
                else
                {
                    nums[i] = y;                //首先尝试第一种方式的修改
                    if(i-1>=0&&nums[i-1]>y)     //如果修改后使nums[i-1]与nums[i]处于递减情况，则采用第二种方式
                    {
                        nums[i] = x;
                        nums[i+1] = x;          //注意第二种方式对于nums[i+1]和nums[i+2]的关系影响在下一层循环中判断
                    }
                }
            }
        }
        return true;
    }
