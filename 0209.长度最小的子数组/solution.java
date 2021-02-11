/*
        方法：滑动窗口，题目要求寻找满足其和 ≥ s 的长度最小的连续子数组，我们利用total记录滑窗中所有元素的和，然后利
             用滑动窗口进行滑动，在此题中，滑动窗口并不是一直向前移动的，end指针和start指针，每次只有一个在移动:
               1、当total>=s时，我们移动start指针，直到total<s。
               2、当total<s时，我们移动end指针，直到total>=s。
             这样前后指针交替滑动，便能够找到满足其和 ≥ s 的长度最小的连续子数组。具体代码如下：
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        int start = 0,end=0,total=0;
        while(end<len)
        {
            total+=nums[end];
            while(total>=target)
            {
                res = Math.min(res,end-start+1);
                total-=nums[start];
                start++;
            }
            end++;
        }
        if(res==Integer.MAX_VALUE)
            return 0;
        return res;
    }
