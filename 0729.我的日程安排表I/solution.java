/*
        方法1：暴力模拟，遍历每一个日程安排，然后根据条件判断。
        时间复杂度：O(n^2)
     */
    class MyCalendar {
        List<int[]> Calendar;
        public MyCalendar() {
            Calendar = new ArrayList<>();
        }
        public boolean book(int start, int end) {
            for(int[] temp:Calendar)
            {
                int s=temp[0],e=temp[1];
                if(e>start&&end>s)
                    return false;
            }
            Calendar.add(new int[]{start,end});
            return true;
        }
    }
    /*
        方法2：差分思想，边界计数，当新加入一个日程时，则[start,end)的时间轴上均增加一个任务，
              利用差分的思想，我们只需要对区间边界进行修改，start++,end--。从意义上可以理解为
              从start开始多了一个任务，end结束了一个任务，然后统计过程中同时进行的任务数，若超
              过1则说明冲突了，这是处理区间问题的通解。
        时间复杂度：O(n^2)
     */
    class MyCalendar2 {
        TreeMap<Integer,Integer> Map;
        public MyCalendar2() {
            Map = new TreeMap<>();
        }
        public boolean book(int start, int end) {
            int time = 0;
            Map.put(start,Map.getOrDefault(start,0)+1);
            Map.put(end,Map.getOrDefault(end,0)-1);          //将日程加入
            for(int t:Map.values())
            {
                time+=t;
                if(time>=2)                                             //若冲突，则需要进行恢复
                {
                    Map.put(start,Map.get(start)-1);
                    Map.put(end,Map.get(end)+1);
                    return false;
                }
            }
            return true;
        }
    }
    /*
        方法3：借助平衡树，能够利用logn的复杂度快速找出与元素大小关系响铃的元素，在java中为TreeMap结构
        时间复杂度：O(nlogn)
     */
    class MyCalendar3 {
        TreeMap<Integer,Integer> Calendar;
        public MyCalendar3() {
            Calendar = new TreeMap<>();
        }
        public boolean book(int start, int end) {
            Integer large = Calendar.ceilingKey(start),
                    small = Calendar.floorKey(start);
            if((small==null||Calendar.get(small)<=start)&&(large==null||end<=large))
            {
                Calendar.put(start,end);
                return true;
            }
            return false;
        }
    }
