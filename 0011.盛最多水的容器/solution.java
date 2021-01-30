public class solution_11 {
    /*
        方法：双指针，容量的计算公式为：min(height[i],height[j])*(j-i)，则可以利用双指针分别指向、
             数组的头和尾，在选择指针移动时，只需要移动height值小的那个指针即可，因为根据计算公式，当
             两个指针往里移动时，j-i是一定减小的，我们能够增大的只有min(height[i],height[j])，这
             时候若移动height值大的指针，只能使此值更小，而不会变大。而移动height值小的指针，则有可能
             使此值变大。
     */
    public int maxArea(int[] height) {
        int res = Integer.MIN_VALUE;
        int len = height.length;
        int left=0,right=len-1;
        while(left<right)                                               //左右指针移动
        {
            if(height[left]<height[right])                              //移动height值小的指针
            {
                res = Math.max(res,height[left]*(right-left));
                left++;
            }
            else
            {
                res = Math.max(res,height[right]*(right-left));
                right--;
            }
        }
        return res;
    }
}
