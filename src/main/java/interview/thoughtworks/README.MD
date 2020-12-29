### 启动类

```java
Run.java为启动类，运行main方法
可自行选择调用有参或者无参的run方法
不加参数，即从控制台读取，任何时候不输入直接回车即开始计算
加参数，即直接读取参数计算
力求保证注释完备，遵循阿里巴巴Java开发规范
```

### 解题思路

#### 1.先理解题意

1.1 虽然题目在引导我从左往右读，但是显然这个应该从右往左读啊，
从左往右读还要当心读下一个是否比下下一个大，大的话减当前，小的话加当前；
从右往左读就只用比当前，比当前大就加，小就减，描述起来字都少

1.2 当心限制条件，看着这一对这不能那不能，让我回忆起写复杂业务时候护持条件的痛苦场景，唉

#### 2. 验证对不对

根据测试用例：

```
Test input:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

Test Output:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about
```

2.1 glob glob Silver is 34 Credits

即II的银是34，II从右到左读是1+1=2，也就是说银每单位是17

2.2 glob prok Gold is 57800 Credits

即IV的金是57800，IV从右到左读是5-1=4，也就是说金每单位是14450

2.3 pish pish Iron is 3910 Credits

即XX的铁是3910，XX从右到左读是10+10=20，也就是说铁每单位是195.5

2.4 how much is pish tegj glob glob ?

即问XLII，从右到左读是1+1+50-10=42。**思路正确**

2.5 how many Credits is glob prok Silver ?

即问IV的银，从右到左读是5-1=4，4*17=68。**思路正确**

2.6 how many Credits is glob prok Gold ?

即问IV的金，也就是4*14450=57800。**思路正确**

2.7 how many Credits is glob prok Iron ?

即问IV的铁，也就是4*195.5=782。**思路正确**

#### 3. 先完成根据任意罗马字母转数字

见 `RomanToNumber.java` 

> 1. 先切割成一个一个的
> 2. 再转换成list
> 3. 将每一位上的数字用正或者负来表示
> 4. 不能连续出现负数
> 5. DLV不能重复出现,DLV不能是负数
> 6. 从后向前遍历，有负的就求和，得出求和后的结果
> 7. IXC可以重复三次,只能IV或者IX,只能XL或者XC
> 8. 验证通过

#### 4. 再做输入解析器

> 1. 解析录入的数据里面的 单词 -> roman 映射
> 2. 解析录入的数据里面的 银/金/铁 -> 价值 的映射
> 3. 解析 how much 并求值
> 4. 解析 how many 并求值
> 5. 其余情况皆为异常情况

#### 5. 获取输入源

这里做了两种，看需要选用

> 1. 不加参数，即从控制台读取，任何时候不输入直接回车即开始计算
> 2. 加参数，即直接读取参数计算