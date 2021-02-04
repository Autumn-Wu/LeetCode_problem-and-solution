/*
        方法：哈希表，即构造一个双射函数，字母与单词一一对应，不能出现一对多或多对一的情况，利用两个哈希表进行构造。
     */
    public boolean wordPattern(String pattern, String s) {
        String[] temp = s.split(" ");
        char[] str = pattern.toCharArray();
        HashMap<Character,String> map1 = new HashMap<>();
        HashMap<String,Character> map2 = new HashMap<>();
        if(temp.length!=str.length)
            return false;
        for(int i=0;i<str.length;i++)
        {
            if(map1.containsKey(str[i])&&!map1.get(str[i]).equals(temp[i]))
                return false;
            if(map2.containsKey(temp[i])&&map2.get(temp[i])!=str[i])
                return false;
            map1.put(str[i],temp[i]);
            map2.put(temp[i],str[i]);
        }
        return true;
    }
