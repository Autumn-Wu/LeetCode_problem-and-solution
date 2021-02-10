/*
        方法：滑动窗口+哈希表，此题要求判断s2是否包含s1的排列，对于s1的排列，s1中各字符出现的次数是不会发生改变的，因此我们可以使
             用大小为s1.length()的滑动窗口进行滑动，同时利用哈希表记录每个字符应该存在的次数，哈希表记录滑动窗口内字符的变化，
             具体代码如下。
     */
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length())
            return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int len = str1.length,start=0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i =0;i<len;++i)
            map.put(str1[i],map.getOrDefault(str1[i],0)+1);
        for(int i=0;i<len;++i)
        {
            if(!map.containsKey(str2[i]))
                continue;
            map.put(str2[i],map.get(str2[i])-1);
        }
        if(Check(map))
            return true;
        for(int i=len;i<str2.length;++i)
        {
            if(map.containsKey(str2[start]))
                map.put(str2[start],map.get(str2[start])+1);
            start++;
            if(!map.containsKey(str2[i]))
                continue;
            map.put(str2[i],map.get(str2[i])-1);
            if(Check(map))
                return true;
        }
        return false;
    }
    public boolean Check(HashMap<Character,Integer> map)
    {
        for(Character c: map.keySet())
        {
            if(map.get(c)>0)
                return false;
        }
        return true;
    }
