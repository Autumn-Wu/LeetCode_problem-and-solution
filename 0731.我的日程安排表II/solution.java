/*
        方法：差分思想与边界计数
     */
    class MyCalendar {
        TreeMap<Integer,Integer> Map;
        public MyCalendar() {
            Map = new TreeMap<>();
        }
        public boolean book(int start, int end) {
            int time = 0;
            Map.put(start,Map.getOrDefault(start,0)+1);
            Map.put(end,Map.getOrDefault(end,0)-1);          //将日程加入
            for(int t:Map.values())
            {
                time+=t;
                if(time>=3)                                             //若冲突，则需要进行恢复
                {
                    Map.put(start,Map.get(start)-1);
                    Map.put(end,Map.get(end)+1);
                    return false;
                }
            }
            return true;
        }
    }
