/*
        方法1：既然是字母相同，排序不同的字符串，则可以利用排序，将不同的字符串统一为统一顺序，
              然后利用HashMap将排序后的统一形式作为键，将所有字母异位词作为值存放。
     */
    public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> res = new ArrayList<>();             //存储结果
        HashMap<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++)
        {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);                                  //将排序后统一形式的字符串作为值
            String k = new String(temp);
            if(map.containsKey(k))
                map.get(k).add(strs[i]);                        //将字母异位词加入对应组
            else
            {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(k,list);
            }
        }
        for(String j:map.keySet())
            res.add(map.get(j));                                 //搬移结果
        return res;
    }
