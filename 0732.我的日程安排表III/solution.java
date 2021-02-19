/*
        方法：差分思想与边界计数
     */
    class MyCalendarThree {
        TreeMap<Integer,Integer> Map;
        public MyCalendarThree() {
            Map = new TreeMap<>();
        }
        public int book(int start, int end) {
            int res = 0,time=0;
            Map.put(start,Map.getOrDefault(start,0)+1);
            Map.put(end,Map.getOrDefault(end,0)-1);
            for(int t:Map.values())
            {
                time+=t;
                res=Math.max(res,time);
            }
            return res;
        }
    }
