/*
        方法：两个堆，利用两个堆，一个小顶堆存储较大的一半，一个大顶堆存储较小的一半，这样可以根据大小顶堆的个数关系来获取中
             位数。若大顶堆个数==小顶堆，则返回两个堆顶元素的均值；若小顶堆个数==大顶堆个数+1，则返回小顶堆堆顶元素。需要
             注意的是，要保证两个堆元素个数的平衡，只有当堆完全平衡时，才能正确获取中位数。因此在加入元素后，需要维护两个堆
             的平衡。具体代码如下。
        时间复杂度:O(logn)
     */
    class MedianFinder {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> large;
        /** initialize your data structure here. */
        public MedianFinder() {                                             //在构造函数初始化大顶堆和小顶堆
            this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
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
        }
        public void addNum(int num) {
            small.offer(num);
            large.offer(small.poll());
            while(small.size()<large.size())                                //平衡两个堆
                small.offer(large.poll());
        }
        public double findMedian() {
            if(small.size()==large.size())                                  //根据情况返回中位数
                return ((double)small.peek()+(double)large.peek())/2.0;
            else
                return small.peek();
        }
    }
