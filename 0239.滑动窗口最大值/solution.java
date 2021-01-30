/*
        方法1：大顶堆，利用优先队列维护一个大顶堆，为了保证堆中得到的最大值时在当前滑动窗口中，因此需要记录最大值对应的
              下标，所以我们可以将堆中的元素设置为一个二元组(value,index),当滑动窗口移动时，将新增元素压入堆中，并
              不断弹出堆顶元素，若元素的下标不在当前窗口中，则将元素弹出。最后返回堆顶元素的值，就是当前窗口的最大值。
              时间复杂度为O(nlogn)，空间复杂度为O(n)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {   //重写比较器
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]!=o2[0]? o2[0]-o1[0]:o2[1]-o1[1];
            }
        });
        int[] res = new int[len-k+1];
        int res_index=0;
        for(int i=0;i<k;++i)
            heap.offer(new int[]{nums[i],i});
        res[res_index++]=heap.peek()[0];
        for(int j=k;j<len;++j)
        {
            int First = j-k+1;                                                      //记录窗口的开头
            heap.offer(new int[]{nums[j],j});
            while(heap.peek()[1]<First)                                             //不断弹出过期元素
                heap.poll();
            res[res_index++]=heap.peek()[0];
        }
        return res;
    }
    /*
        方法2:单调队列，维护一个单调队列，队列中的头为最大元素，队列中的尾部为最小元素，队列从队尾到队头为递增
             顺序。但是同样，需要保证队头最大元素为当前滑动窗口中的元素，因此每次都需要弹出队头中的过期元素，
             所以我们可以设置队列中的元素为二元组(value,index)，或者直接将下标作为元素放入队列中，比较时
             通过索引获取对应值即可。当新遍历到一个值时，需要将这个值不断与队尾进行比较，若这个值比队尾大，则需要
             将队尾弹出，最终将值从队尾中加入。这样就可以保持整个队列的递增关系，可以理解为当前元素存在时，这些比
             他小的元素都没有可能作为最大值。单调队列的实现需要用到队头与队尾，因此需要用到双向队列Deque。
             时间复杂度O(n),空间复杂度O(n)
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        int len = nums.length;
        int[] res = new int[len-k+1];
        int res_index = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(0);
        for(int i=1;i<k;i++)
        {
            while(!deque.isEmpty()&&nums[i]>nums[deque.peekLast()])         //下标作为队列元素，保持递增关系
                deque.pollLast();
            deque.addLast(i);
        }
        res[res_index]=nums[deque.peekFirst()];
        res_index++;
        for(int j=k;j<len;j++)
        {
            int First = j-k+1;
            while(!deque.isEmpty()&&nums[j]>nums[deque.peekLast()])
                deque.pollLast();
            deque.addLast(j);
            while(deque.peekFirst()<First)                                  //删除队头
                deque.pollFirst();
            res[res_index]=nums[deque.peekFirst()];
            res_index++;
        }
        return res;
    }
