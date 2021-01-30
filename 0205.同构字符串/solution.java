/*
        方法:本题的关键是验证两个字符串的对应字符是否一一对应，与290. 单词规律相类似，
            将问题转化为字符之间是否为一个双射函数，利用两个哈希表进行存储映射关系，不
            使用数组的原因是因为测试字符不止包含字母，然后根据两个哈希表判断是否一一对应。
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map1 = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        for(int i=0;i<s.length();i++)
        {
            char first=map1.getOrDefault(str1[i],'#');
            char second=map2.getOrDefault(str2[i],'#');     //如果两个字符均未确立映射关系，则返回#
            if(first=='#'&&second=='#')
            {
                map1.put(str1[i],str2[i]);
                map2.put(str2[i],str1[i]);                             //若均未建立映射，则建立映射
            }
            else
            {
                if(first!=str2[i]||second!=str1[i])                    //若不是一一对应，则返回false
                    return false;
            }
        }
        return true;
    }
