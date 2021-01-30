/*
        方法1：暴力，借助额外数组直接保存移动结果，然后进行复制。(注意leecode的判断机制，要求返回的nums地址不能改变)
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        for(int i=0;i<len;i++)
        {
            int index = (i+k)%len;
            res[index] = nums[i];
        }
        System.arraycopy(res,0,nums,0,len);
    }
    /*
        方法2：数组翻转，进行移动后，可以知道最后k%n个元素将被移动到数组的开头，前面的元素则往后移动k%n个位置，
              为此我们首先将数组翻转，保证局部的位置对应，然后分别将两个部分的数组翻转，恢复原本的顺序。
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        reverse(nums,0,len-1);                                      //整体翻转
        reverse(nums,0,k%len-1);                                    //翻转前半部分
        reverse(nums,k%len,len-1);                                  //翻转后半部分
    }
    private void reverse(int[] nums,int start,int end)
    {
        while(start<end)
        {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
