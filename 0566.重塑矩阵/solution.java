/*
        方法：间接映射
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m=nums.length,n=nums[0].length;
        if(m*n!=r*c)
            return nums;
        int[] temp = new int[m*n];
        int index=0;
        for(int i=0;i<m;++i)
            for(int j=0;j<n;++j)
                temp[index++] = nums[i][j];
        int[][] res = new int[r][c];
        index = 0;
        for(int i=0;i<r;++i)
            for(int j=0;j<c;++j)
                res[i][j] = temp[index++];
        return res;
    }
    /*
        方法2：直接映射
     */
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int m=nums.length,n=nums[0].length;
        if(m*n!=r*c)
            return nums;
        int[][] res = new int[r][c];
        for (int x = 0; x < m * n; ++x)
            res[x / c][x % c] = nums[x / n][x % n];
        return res;
    }
