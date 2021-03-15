/*
        方法：模拟，利用top,bottom,left,right四个变量来维护每层螺旋填充的边界，
             在每次填充完后更新边界，以此保证螺旋遍历，具体代码如下：
        时间复杂度:O(m*n)
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1,tar = n*n;
        int top=0,bottom=n-1,left=0,right=n-1;
        while(num<=tar)
        {
            for(int i=left;i<=right&&num<=tar;++i)
            {
                res[top][i] = num;
                num++;
            }
            top++;
            for(int i=top;i<=bottom&&num<=tar;++i)
            {
                res[i][right] = num;
                num++;
            }
            right--;
            for(int i=right;i>=left&&num<=tar;--i)
            {
                res[bottom][i] = num;
                num++;
            }
            bottom--;
            for(int i=bottom;i>=top&&num<=tar;--i)
            {
                res[i][left] = num;
                num++;
            }
            left++;
        }
        return res;
    }
