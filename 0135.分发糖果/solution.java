/*
        方法：将规则分解，相邻的孩子中评分高的孩子需要得到更多的糖果，那么每个孩子得到的糖果数由其
             左右两个孩子得到的糖果决定，我们可以将这个规则进行分解：
             左规则：若当前孩子比前一个孩子得分高，那么其最少分配糖果为前一个孩子的糖果数加一，
                   即ratings[i]>ratings[i-1],则left[i]=left[i-1]+1，否则糖果数为1，即left[i]=1
             右规则：若当前孩子比后一个孩子得分高，那么其最少分配糖果为后一个孩子的糖果数加一，
                   即ratings[i]>ratings[i+1],则right[i]=right[i+1]+1，否则糖果数为1，即right[i]=1
             最终结果取满足左右规则下的最大值，即res[i]=Math.max(left[i],right[i]);在实际进行代码编写时，
             先遍历一次使结果满足左规则，第二次遍历右规则时直接在结果数组上进行操作即可。
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int sum=0;
        int[] res = new int[len];
        res[0]=1;                                       //对于第一个元素，其不存在左元素，初始化为1;
        for(int i=1;i<len;i++)
        {
            if(ratings[i]>ratings[i-1])                 //根据左规则计算结果数组
                res[i]=res[i-1]+1;
            else
                res[i]=1;
        }
        sum+=res[len-1];                                //若根据右规则初始化res[len-1]应该为1，但是左规则计算得到的res[len-1]>=1,因此不需要更改。
        for(int j=len-2;j>=0;j--)                       //第二次遍历右规则时直接在结果数组中进行操作
        {
            if(ratings[j]>ratings[j+1])
                res[j]=Math.max(res[j],res[j+1]+1);
            sum+=res[j];
        }
        return sum;
    }
