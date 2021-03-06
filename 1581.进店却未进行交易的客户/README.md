[SQL架构](https://github.com/Zhenghao-Liu/LeetCode_problem-and-solution/blob/master/1581.进店却未进行交易的客户/PROBLEM.sql)

表：```Visits```
```
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| visit_id    | int     |
| customer_id | int     |
+-------------+---------+
visit_id是该表的主键。
该表包含有关光临过购物中心的顾客的信息。
```

表：```Transactions```
```
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| transaction_id | int     |
| visit_id       | int     |
| amount         | int     |
+----------------+---------+
transaction_id 是此表的主键。
该表包含有关光临过购物中心的顾客的信息。
```

编写一个 SQL 查询来查找没有进行任何交易的访问用户的 ID ，以及他们进行这些访问的次数。

返回以任何顺序排序的结果表。

查询结果格式如下例所示：
```
Visits
+----------+-------------+
| visit_id | customer_id |
+----------+-------------+
| 1        | 23          |
| 2        | 9           |
| 4        | 30          |
| 5        | 54          |
| 6        | 96          |
| 7        | 54          |
| 8        | 54          |
+----------+-------------+

Transactions
+----------------+----------+--------+
| transaction_id | visit_id | amount |
+----------------+----------+--------+
| 2              | 5        | 310    |
| 3              | 5        | 300    |
| 9              | 5        | 200    |
| 12             | 1        | 910    |
| 13             | 2        | 970    |
+----------------+----------+--------+

Result 表：
+-------------+----------------+
| customer_id | count_no_trans |
+-------------+----------------+
| 54          | 2              |
| 30          | 1              |
| 96          | 1              |
+-------------+----------------+
ID = 23 的客户曾经逛过一次购物中心，并在 ID = 12 的访问期间进行了一笔交易。
ID = 9 的客户曾经逛过一次购物中心，并在 ID = 13 的访问期间进行了一笔交易。
ID = 30 的客户曾经去过购物中心，并且没有进行任何交易。
ID = 54 的客户三度造访了购物中心。在 2 次访问中，他们没有进行任何交易，在 1 次访问中，他们进行了 3 次交易。
ID = 96 的客户曾经去过购物中心，并且没有进行任何交易。
如我们所见，ID 为 30 和 96 的用户一次没有进行任何交易就去了购物中心。用户 54 也两次访问了购物中心并且没有进行任何交易。
```

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/customer-who-visited-but-did-not-make-any-transactions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
