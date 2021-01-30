 /*
        方法 ：二分法，关键点在于寻找第一个大于目标值的元素，以及大于等于目标值的元素；也可以寻找第一个大于目标值的元素和最后一个小于
             目标值的元素；看具体的实现方式；若为第一种实现方式，则可以利用flag标志实现函数复用。由于是排序后的数组，利用
             二分法进行查找！
    */
    public int[] searchRange(int[] nums,int target){
        int leftindex=Divided_search(nums,target,false);               //flag为false为寻找第一个大于等于目标元素的值
        //System.out.println(leftindex);
        int rightindex=Divided_search(nums,target,true)-1;             //flag为true为寻找第一个大于目标元素的值
        //System.out.println(rightindex);
        if(leftindex<nums.length&&nums[leftindex]==target&&nums[rightindex]==target)    //考虑可能找不到的情况
            return new int[]{leftindex,rightindex};
        return new int[]{-1,-1};
    }
    public int Divided_search(int[] nums,int target,boolean flag){               //利用二分法进行查询
        int left=0;
        int right=nums.length-1;
        int mid;
        int res=nums.length;                                                            //如果没找到则返回nums.length
        while(left<=right)                                                              //利用二分
        {
            mid=(left+right)/2;
            if(nums[mid]>target||(nums[mid]>=target&&!flag))                            //根据flag值进行不同的查找
            {
                right=mid-1;
                res=mid;
            }
            else
                left=mid+1;
        }
        return res;
    }
