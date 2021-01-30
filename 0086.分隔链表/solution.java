/*
        方法：构建两个链表，一个按原始相对顺序保存原链表中比x小的节点，一个按原始相对顺序
             保存原链表中大于等于x的节点，最后将第一个链表的尾部指向第二个链表的头，即可。
             注：可能出现第一个链表为空的情况
             时间复杂度O(n)
     */
    public ListNode partition1(ListNode head, int x) {
        if(head==null)
            return null;
        ListNode SmallHead=new ListNode(0);
        ListNode LargeHead=new ListNode(0);
        ListNode SmallEnd=SmallHead;
        ListNode LargeEnd=LargeHead;
        while(head!=null)
        {
            if(head.val<x)
            {
                SmallEnd.next=head;
                SmallEnd=SmallEnd.next;
            }
            else
            {
                LargeEnd.next=head;
                LargeEnd=LargeEnd.next;
            }
            head=head.next;
        }
        LargeEnd.next=null;
        SmallEnd.next=LargeHead.next;
        return SmallHead.next;
    }
