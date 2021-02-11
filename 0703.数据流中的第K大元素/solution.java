/*
        方法：小顶堆，小顶堆维护前k大元素。
     */
    class KthLargest {
        PriorityQueue<Integer> Topk;
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            Topk = new PriorityQueue<>();
            for(int x:nums)
                add(x);
        }
        public int add(int val) {
            Topk.offer(val);
            if(Topk.size()>k)
                Topk.poll();
            return Topk.peek();
        }
    }
