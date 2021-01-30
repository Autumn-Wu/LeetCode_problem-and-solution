/*
        方法：第一次遍历利用数组记录每个字符出现的次数，第二次遍历时从头至尾不断判断字符是否
             只出现一次，是则直接返回索引。
     */
    public int firstUniqChar(String s)
    {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        for(int i=0;i<str.length;i++)
            count[str[i]-'a']++;
        for(int i=0;i<str.length;i++)
            if(count[str[i]-'a']==1)
                return i;
        return -1;
    }
