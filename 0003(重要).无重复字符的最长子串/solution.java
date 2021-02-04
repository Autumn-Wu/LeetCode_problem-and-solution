/*
        方法：滑动窗口+哈希表，利用哈希表记录已经出现过的字符下标，利用双指针指向滑窗的起点和终点，
             将尾指针向后移动，每遇到一个字符则判断其是否出现过，若曾经出现过，需要判断出现的下标
             是否处在滑窗内，若在滑窗内，则移动头指针至出现下标+1，同时将重复元素前的元素一同略过，
             因为子串要求连续，同时更新哈希表中字符下标。过程中不断记录最大长度。
             注：哈希表相当于只记录最近一次元素出现的下标
        时间复杂度：O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        int max = 0;
        int start = 0;
        int end = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while(end<str.length)
        {
            int index = map.getOrDefault(str[end],-1);
            if(index == -1||index<start)                //若当前元素未在滑窗内出现过
            {
                map.put(str[end],end);                  //记录元素出现下标
                end++;
                if(end-start>max)                       //记录结果
                    max = end-start;
            }
            else                                       //当前元素在滑窗内出现，则移动头指针
            {
                map.put(str[end],end);                 //同时更新哈希表
                start = index+1;
                end++;
            }
        }
        return max;
    }
