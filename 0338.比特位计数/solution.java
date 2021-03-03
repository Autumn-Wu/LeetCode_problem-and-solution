/*
        方法1：暴力模拟或api调用
        时间复杂度：O(k*num),k为二进制位数
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for(int i=0;i<=num;++i)
        {
            res[i] = Integer.bitCount(i);                    //API方法
            /*
                int ones = 0;                              //暴力扫描法
                while (x > 0) {
                   x &= (x - 1);
                   ones++;
                }
                res[i] = ones;
            */
        }
        return res;
    }
    /*
        方法2：动态规划，状态转移方程如下：
                若i为奇数，则i-1为偶数，i比i-1多一个1，这个1在最后一位上；
                若i为偶数，则i最后一位为0，与i>>1具有相同的1个数，因为最后一位为0；
        时间复杂度：O(num)
     */
    public int[] countBits2(int num) {
        int[] res = new int[num+1];
        for(int i=1;i<=num;++i)
        {
            if((i&1)==1)
                res[i] = res[i-1]+1;
            else
                res[i] = res[i/2];
        }
        return res;
    }
    /*
        方法3：动态规划，类似方法2，状态转移方程：
               res[i] = res[i>>1]+(i&1);
        时间复杂度：O(num)
     */
    public int[] countBits3(int num) {
        int[] res = new int[num+1];
        for(int i=1;i<=num;++i)
        {
            res[i] = res[i>>1]+(i&1);
        }
        return res;
    }
