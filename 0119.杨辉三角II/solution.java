/*
        方法：暴力模拟，利用k个数组存储前k行的杨辉三角结果
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> YangHui = new ArrayList<>();
        YangHui.add(Arrays.asList(1));
        for(int i=1;i<=rowIndex;++i)
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
        return YangHui.get(rowIndex);
    }
    /*
        优化：滚动数组再优化
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for(int i=1;i<=rowIndex;++i)
        {
            res.add(0);
            for(int j=i;j>0;--j)
            {
                res.set(j,res.get(j)+res.get(j-1));
            }
        }
        return res;
    }
