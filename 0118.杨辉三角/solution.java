public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> YangHui = new ArrayList<>();
        if(numRows==0)
            return YangHui;
        YangHui.add(Arrays.asList(1));
        for(int i=1;i<numRows;++i)
        {
            List<Integer> temp = new ArrayList<>();
            for(int j=0;j<i+1;++j)
            {
                if(j==0||j==i)
                    temp.add(1);
                else
                    temp.add(YangHui.get(i-1).get(j-1)+YangHui.get(i-1).get(j));
            }
            YangHui.add(temp);
        }
        return YangHui;
    }
