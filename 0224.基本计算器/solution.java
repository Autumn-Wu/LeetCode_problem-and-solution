/*
        方法：单栈，利用栈来解决括号的优先级问题，每当遇到一个左括号，则将括号前面的符号
             和符号前的值压入栈中，当遇到右括号时，将当前的计算结果res与之前压入栈的
             符号相乘，然后加上符号前的值，即完成计算。这样就做到了值+-括号内的值,其中
             res表示的是当前括号内的计算结果，因此在遇到左括号后压入后需要置为0，计算
             新括号中的计算结果。具体代码如下：
        时间复杂度：O(n)
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sign = 1, res = 0;                      //sign 代表正负，初始化符号和res
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {            //当读取到的是数字时，将整个数字读入
                int cur = ch - '0';
                while(i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                res = res + sign * cur;
            } else if (ch == '+') {                 //不是数字时根据计算符号更改sign
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {                 //左括号
                stack.push(res);                    //压入符号前的值
                res = 0;                            //将res置零
                stack.push(sign);                   //压入括号前的符号
                sign = 1;                           //将符号位初始化
            } else if (ch == ')') {                         //右括号
                res = stack.pop() * res + stack.pop();      //则弹出符号和符号前的值，进行合并
            }
        }
        return res;
    }
