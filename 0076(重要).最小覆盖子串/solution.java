/*
        方法：滑动窗口+哈希表，题目要求寻找s中涵盖t所有字符的最小子串，首先我们利用哈希表存储
             t中所有字符的出现次数，然后利用滑动窗口进行滑动，在此题中，滑动窗口并不是一直向
             前移动的，end指针和start指针，每次只有一个在移动:
               1、当t中所有字符均在窗口中出现时，我们移动start指针，直到t中有字符未出现。
               2、当t中有字符未出现时，我们移动end指针，寻找下一个满足要求的子串。
             这样前后指针交替滑动，便能够找到最小的覆盖子串。具体代码如下：
     */
    public String minWindow(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int res = Integer.MAX_VALUE,res_start=0,res_end=0;              //记录最小子串的长度和起始位置
        int start = 0,end=0;
        HashMap<Character,Integer> map = new HashMap<>();               //map统计t中所有字符出现的次数
        for(int i=0;i<str2.length;++i)
        {
            map.put(str2[i],map.getOrDefault(str2[i],0)+1);
        }
        while(end<str1.length)
        {
            if(map.containsKey(str1[end]))                              //若end指针指向的元素不为t中的字符则end++
            {
                map.put(str1[end],map.get(str1[end])-1);                //否则更新哈希表
                while(Check(map))                                       //判断是否满足t中所有字符出现
                {
                    if(end-start+1<res)                                 //记录最小值
                    {
                        res = end-start+1;
                        res_start = start;
                        res_end = end;
                    }
                    if(map.containsKey(str1[start]))                    //start指针移动，直到t中有字符未出现
                        map.put(str1[start],map.get(str1[start])+1);
                    start++;
                }
            }
            end++;
        }
        if(res==Integer.MAX_VALUE)                                      //若未找到满足条件的子串则返回空串
            return new String("");
        return s.substring(res_start,res_end+1);
    }
    public boolean Check(HashMap<Character,Integer> map)
    {
        for(Character c:map.keySet())
        {
            if(map.get(c)>0)
                return false;
        }
        return true;
    }
