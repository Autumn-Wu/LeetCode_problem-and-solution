/*
        方法：栈，当之前的符号为+时，直接将当前元素压入栈中，当当前符号为-时，直接
             将当前元素的相反数压入栈中，当当前符号为*或/时，获取栈顶元素与当前元素
             进行计算，然后将计算结果压入栈代替先前栈顶元素；最终整个栈的元素相加即
             为计算结果。
     */
    public int calculate(String s) {
        char[] str = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        for(int i=0;i<str.length;++i)
        {
            char c = str[i];
            if(Character.isDigit(c))
            {
                int cur = c-'0';
                while(i+1<str.length&&Character.isDigit(str[i+1]))      //获取整个数字
                    cur = cur * 10 + str[++i]-'0';
                if(sign==1)                                             //根据符号进行操作
                    stack.add(cur);
                else if(sign==-1)
                    stack.add(-cur);
                else if(sign==2)
                    stack.add(cur*stack.pop());
                else if(sign==3)
                    stack.add(stack.pop()/cur);
            }
            else if(c=='+')                                             //设置符号
                sign=1;
            else if(c=='-')
                sign=-1;
            else if(c=='*')
                sign=2;
            else if(c=='/')
                sign=3;
        }
        while (!stack.isEmpty())                                        //计算结果
            res+=stack.pop();
        return res;
    }
