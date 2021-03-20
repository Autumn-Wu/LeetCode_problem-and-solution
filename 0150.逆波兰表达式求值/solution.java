/*
        方法：栈
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<tokens.length;++i)
        {
            String str = tokens[i];
            if(str.equals("+"))
            {
                int one = stack.pop();
                int two = stack.pop();
                stack.push(one+two);
            }
            else if(str.equals("-"))
            {
                int one = stack.pop();
                int two = stack.pop();
                stack.push(two-one);
            }
            else if(str.equals("/"))
            {
                int one = stack.pop();
                int two = stack.pop();
                stack.push(two/one);
            }
            else if(str.equals("*"))
            {
                int one = stack.pop();
                int two = stack.pop();
                stack.push(one*two);
            }
            else
            {
                int num = Integer.valueOf(str);
                stack.push(num);
            }

        }
        return stack.pop();

    }
