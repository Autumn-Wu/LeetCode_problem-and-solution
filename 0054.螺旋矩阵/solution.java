/*
        方法：模拟，利用top,bottom,left,right四个变量来维护每层螺旋遍历的边界，
             在每次遍历完后更新边界，以此保证螺旋遍历，具体代码如下：
        时间复杂度:O(m*n)
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length,n = matrix[0].length;
        int num = m*n,top = 0,bottom = m-1,left = 0,right = n-1;
        List<Integer> res = new LinkedList<>();
        while(num>0)
        {
            for(int i=left;i<=right&&num>0;++i)             //注意每次循环都要判断num，防止多余遍历
            {
                res.add(matrix[top][i]);
                num--;
            }
            top++;
            for(int i=top;i<=bottom&&num>0;++i)
            {
                res.add(matrix[i][right]);
                num--;
            }
            right--;
            for(int i=right;i>=left&&num>0;--i)
            {
                res.add(matrix[bottom][i]);
                num--;
            }
            bottom--;
            for(int i=bottom;i>=top&&num>0;--i)
            {
                res.add(matrix[i][left]);
                num--;
            }
            left++;
        }
        return res;
    }
