/*
        方法：槽位与栈，槽位是一颗二叉树中等待被节点填充的位置，我们利用一个栈来更新维护槽位的数量；
             每当遇到一个节点时：
                若当前节点为空节点，则消耗一个槽位数；
                若当前节点为非空节点，则消耗一个槽位数的同时增加两个槽位数，因为他的两个子节点提供两个槽位；
             因此我们维护一个栈，根据当前节点的信息维护栈顶元素，当栈顶元素为0时则弹出栈顶元素。栈中元素代表
             着每个节点能提供的槽位数。具体代码如下：
        时间复杂度：O(n),空间复杂度:O(n)
     */
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack<>();
        char[] str = preorder.toCharArray();
        stack.add(1);                                   //初始化时提供一个槽位用于放置根节点
        int index = 0,n = str.length;
        while(index<n)
        {
            if(stack.isEmpty())                         //若没有槽位可用，则说明其不正确
                return false;
            if(str[index]=='#')                         //当前节点为空节点
            {
                int temp = stack.pop();
                temp--;
                if(temp!=0)                             //消耗一个槽位，若槽位数为0则弹出栈
                    stack.add(temp);
                index++;
            }
            else if(str[index]==',')
            {
                index++;
            }
            else if(Character.isDigit(str[index]))              //非空节点
            {
                while(index<n&&Character.isDigit(str[index]))  //获取全部数字
                    index++;
                int temp = stack.pop();
                temp--;                                        //消耗一个槽位
                if(temp!=0)
                    stack.add(temp);
                stack.add(2);                                  //增加两个槽位
                index++;
            }
        }
        if(!stack.isEmpty())                                   //当遍历完后仍有槽位，说明其不正确
            return false;
        return true;
    }
    /*
        优化：我们可用通过一个变量来维护整个树的总槽位树，具体代码如下：
        时间复杂度:O(n),空间复杂度:O(1)
     */
    public boolean isValidSerialization2(String preorder) {
        char[] str = preorder.toCharArray();
        int index = 0,n = str.length;
        int nums = 1;
        while(index<n)
        {
            if(nums==0)
                return false;
            if(str[index]=='#')
            {
                nums--;
                index++;
            }
            else if(str[index]==',')
            {
                index++;
            }
            else if(Character.isDigit(str[index]))
            {
                while(index<n&&Character.isDigit(str[index]))
                    index++;
                nums++;
                index++;
            }
        }
        if(nums!=0)
            return false;
        return true;
    }
