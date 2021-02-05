/*
        方法：两个堆+延迟删除，此题为295. 数据流的中位数的进阶版，进阶在于多了一个移除元素的操作，基本思路仍然不变，
             利用两个堆，一个小顶堆存储较大的一半，一个大顶堆存储较小的一半，这样可以根据大小顶堆的个数关系来获取中
             位数。若大顶堆个数==小顶堆，则返回两个堆顶元素的均值；若小顶堆个数==大顶堆个数+1，则返回小顶堆堆顶元素。
             需要注意的是，要保证两个堆元素个数的平衡，只有当堆完全平衡时，才能正确获取中位数。因此在加入和移除元素
             后，需要维护两个堆的平衡。其次就是移除元素的操作，堆中删除指定元素是比较麻烦的，因此，我们利用哈希表记录
             需要删除的元素和需要删除的次数，在删除元素时，若当前元素不为堆顶元素，则我们不进行立刻删除，只需要保证
             我们的中位数在滑窗内即可，但是需要判断删除元素所在的堆，并使堆的元素个数-1，这样就起到了延迟删除的效果，
             仅在堆顶元素为过期元素时进行删除，并不断弹出过期元素。具体代码如下。
        时间复杂度：O(nlogn)
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.GetMedian();
        for (int i = k; i < nums.length; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.GetMedian();
        }
        return ans;
    }
    class DualHeap
    {
        private PriorityQueue<Integer> small;                               //记录较小一半的大顶堆
        private PriorityQueue<Integer> large;                               //记录较大一半的小顶堆
        private HashMap<Integer,Integer> delayed;                           //记录延迟删除的元素及次数
        private int k;                                                      //记录滑窗大小
        private int small_size,large_size;                                  //记录两个堆的大小
        public DualHeap(int k)
        {
            this.small = new PriorityQueue<>(new Comparator<Integer>() {    //由于担心溢出，因此使用Integer.compare.
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2,o1);
                }
            });
            this.large = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1,o2);
                }
            });
            this.delayed = new HashMap<>();
            this.k = k;
            this.small_size = 0;
            this.large_size = 0;
        }
        public double GetMedian()                                           //获取中位数函数
        {
            if(k%2==1)
                return small.peek();
            return ((double)small.peek()+(double)large.peek())/2;
        }
        public void insert(int num)                                         //插入元素
        {
            if(small.isEmpty()||num<small.peek())
            {
                small.offer(num);
                ++small_size;
            }
            else
            {
                large.offer(num);
                ++large_size;
            }
            MakeBalance();                                                  //插入元素后维护平衡
        }
        public void erase(int num)                                          //删除元素
        {
            delayed.put(num,delayed.getOrDefault(num,0)+1);      //记录删除元素及其次数
            if(num<=small.peek())                                           //根据大小关系判断删除元素处在哪个堆
            {
                --small_size;
                if(num==small.peek())                                       //若堆顶元素为删除元素，则需要进行更新操作
                    Update(small);
            }
            else
            {
                --large_size;
                if(num==large.peek())
                    Update(large);
            }
            MakeBalance();                                                  //删除完元素后需要维护平衡
        }

        private void MakeBalance()                                          //维护平衡
        {
            if(small_size>large_size+1)                                     //在移动完堆顶元素后
            {
                large.offer(small.poll());
                --small_size;
                ++large_size;
                Update(small);                                              //需要进行更新操作
            }
            else if(small_size<large_size)
            {
                small.offer(large.poll());
                ++small_size;
                --large_size;
                Update(large);
            }
        }
        private void Update(PriorityQueue<Integer> heap)                    //更新过期元素
        {
            while(!heap.isEmpty())
            {
                int num = heap.peek();
                if(delayed.getOrDefault(num,0)!=0)              //将过期元素移除
                {
                    delayed.put(num,delayed.get(num)-1);
                    heap.poll();
                }
                else
                {
                    break;
                }
            }
        }


    }
