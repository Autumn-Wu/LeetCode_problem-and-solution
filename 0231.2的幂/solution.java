/*
        方法1(超时)：暴力
     */
    public boolean isPowerOfTwo(int n)
    {
        int value=1;
        while(value<=n)
        {
            if(value==n)
                return true;
            value=value*2;
        }
        return false;
    }
    /*
        方法2：利用二次幂数的二进制表示形式规律，二次幂的数n可以看成1不断左移的过程，
              最后形式为最高位为1，低位为0的二进制表示。那么n-1则是最高位为0，低位
              为1的二进制表示，两者相与结果为0.即关键点判断为:n&(n-1)==0,利用这一
              点就能完成此题。
     */
    public boolean isPowerOfTwo2(int n)
    {
        return (n>0)&&(n&(n-1))==0;
    }
    /*
        方法3：二次幂数的二进制表示仅有一个1
     */
    public boolean isPowerOfTwo3(int n)
    {
        return (n>0)&&Integer.bitCount(n)==1;
    }
