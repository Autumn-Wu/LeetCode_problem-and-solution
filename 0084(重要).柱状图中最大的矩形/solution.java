/*
        方法：单调栈，矩阵面积的计算area = length * height,即长乘宽，计算公式中的宽
             可以通过枚举heights数组中的元素来确定，那么剩下需要确定的就是长度，我们可
             以观察到，当以第i个元素为宽时，length由区间[left,right]决定，其中区间中
             元素的height[j]>=height[i],即区间中的元素高度要比第i个元素高，因此我们
             可以通过暴力法，从i元素往左遍历，直到遇到height[left]<height[i]；从i
             元素往右遍历，直到遇到height[right]<height[i]，这样length就确定了，
             这样的暴力寻找方法最终时间复杂度为O(n^2)。我们可以发现寻找left,right的
             本质：找出i元素左右第一个比他小的元素，根据这个定义我们可以想到单调栈的使用，
             单调栈的作用就是用来寻找第一个比当前元素小(大)的元素。因此我们利用单调栈，
             预先寻找每个元素的left和right，寻找过程的复杂度为O(n)，然后以不同的元素
             作为高，计算对应的矩阵面积，得出最终答案。
             细节：当向右找第一个比他小的元素未找到时，应设置值为len(元素个数),
                  当向左找第一个比他小的元素未找到时，应设置值为-1。
             时间复杂度O(n) 空间复杂度O(n)
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] right = new int[len];
        int[] left = new int[len];                                              //利用两个数组记录两边第一个最小的元素下标
        Arrays.fill(right,len);
        Arrays.fill(left,-1);
        Deque<node> stack = new ArrayDeque<>();
        Deque<node> stack2 = new ArrayDeque<>();
        for(int i=0;i<len;++i)                                                  //根据单调栈记录结果
        {
            while(!stack.isEmpty()&&stack.getFirst().value>heights[i])
            {
                right[stack.pollFirst().index] = i;
            }
            stack.offerFirst(new node(heights[i],i));
        }
        for(int i=len-1;i>=0;--i)
        {
            while(!stack.isEmpty()&&stack.getFirst().value>heights[i])
            {
                left[stack.pollFirst().index] = i;
            }
            stack.offerFirst(new node(heights[i],i));
        }
        int res = Integer.MIN_VALUE;
        for(int i=0;i<len;++i)                                                  //根据元素高度计算对应矩阵面积获取最大值
        {
            int area = heights[i]*(right[i]-left[i]-1);
            if(area > res)
            {
                res = area;
            }
        }
        return res;
    }
    class node
    {
        public int value;
        public int index;
        public node(int v,int i)
        {
            this.value = v;
            this.index = i;
        }

    }
