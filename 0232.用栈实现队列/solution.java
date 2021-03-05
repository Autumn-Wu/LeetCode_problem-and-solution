/*
        方法1：双栈，一个栈用于存储数据，另一个栈用于辅助，当需要pop和peek时，将存储在第一个
              栈中的数据倒入辅助栈中，此时辅助栈中保持的数据即为队列顺序，辅助完后再将辅助栈
              中元素倒入第一个栈。
     */
    class MyQueue {

        /** Initialize your data structure here. */
        private Deque<Integer> stack1;
        private Deque<Integer> stack2;
        private int size;
        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
            size = 0;
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.offerFirst(x);
            size++;
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            while(!stack1.isEmpty())
            {
                stack2.offerFirst(stack1.pollFirst());
            }
            int res = stack2.pollFirst();
            while(!stack2.isEmpty())
            {
                stack1.offerFirst(stack2.pollFirst());
            }
            size--;
            return res;
        }

        /** Get the front element. */
        public int peek() {
            while(!stack1.isEmpty())
            {
                stack2.offerFirst(stack1.pollFirst());
            }
            int res = stack2.peek();
            while(!stack2.isEmpty())
            {
                stack1.offerFirst(stack2.pollFirst());
            }
            return res;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return size==0;
        }
    }
    /*
        优化：在方法1中，我们反复将栈中元素倒入和还原，消耗了大量时间，而pop和peek仅需要队列中的
             头元素，因此我们定义两个栈一个为输入栈，一个为输出栈，输入栈保存push的结果，输出栈
             用于pop和peek；在输出栈为空时，我们需要将输入栈中元素倒入输出栈，但不需要进行还原，
             因为peek和pop一定优先使用输出栈中的元素，而不会用到后面push到队列中的元素。
     */
    class MyQueue2 {
        /** Initialize your data structure here. */
        private Deque<Integer> stack1;
        private Deque<Integer> stack2;
        private int size;
        public MyQueue2() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
            size = 0;
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.offerFirst(x);
            size++;
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stack2.isEmpty())
            {
                while(!stack1.isEmpty())
                {
                    stack2.offerFirst(stack1.pollFirst());
                }
            }
            size--;
            return stack2.pollFirst();
        }

        /** Get the front element. */
        public int peek() {
            if(stack2.isEmpty())
            {
                while(!stack1.isEmpty())
                {
                    stack2.offerFirst(stack1.pollFirst());
                }
            }
            return stack2.peekFirst();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return size==0;
        }
    }
