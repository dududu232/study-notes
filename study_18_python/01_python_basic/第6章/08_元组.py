# 1. 定义元组
t1 = (1, 2, 3, 4, 5)
t2 = ("北京", "尚硅谷", "你好啊")
t3 = (100, True, "尚硅谷", None)
t4 = (100, True, "尚硅谷", None, (1, 2, 3), ["你好"])
print(type(t1), t1)  # <class 'tuple'> (1, 2, 3, 4, 5)
print(type(t2), t2)  # <class 'tuple'> ('北京', '尚硅谷', '你好啊')
print(type(t3), t3)  # <class 'tuple'> (100, True, '尚硅谷', None)
print(type(t4), t4)  # <class 'tuple'> (100, True, '尚硅谷', None, (1, 2, 3), ['你好'])

# 2. 元组的下标
print(t4[0])  # 100
print(t4[1])  # True
print(t4[-1])  # ['你好']
print(t4[-2])  # (1, 2, 3)

# 3. 元组中的元素，不可修改
t5 = (1, 2, 3, 4, 5)
# t5[0] = 2   # 'tuple' object does not support item assignment

# 4. 元组中的元素，不可修改，但元组中如果存放了可变类型（列表），那可变类型中的内容仍可修改
# 理解： 元组中的地址不可被替换
t6 = (1, 2, 3, 4, 5, ["北京", "尚硅谷", (100, 200, 300)])
t6[5][0] = "你好啊"
print(t6)  # (1, 2, 3, 4, 5, ['你好啊', '尚硅谷', (100, 200, 300)])
# t6[5][2][0] = 200   # TypeError: 'tuple' object does not support item assignment

# 5. 定义空元组
t7 = ()
t8 = tuple()
print(type(t7), t7)  # <class 'tuple'> ()
print(type(t8), t8)  # <class 'tuple'> ()

# 6. 定义只有一个元素的元组
# 6.1 下面的写法，Python会当作字符串处理
t9 = ("你好")
t10 = (
    "你好"
    "尚硅谷"
)
print(type(t9), t9)  # <class 'str'> 你好
print(type(t10), t10)  # <class 'str'> 你好尚硅谷
# 6.2 正确定义只有一个元素的元组
t11 = ("你好啊",)
print(type(t11), t11)  # <class 'tuple'> ('你好啊',)

# 7. 元组的常用方法
# 7.1 index 方法：获取指定元素在元组中第一次出现的下标
t12 = (10, 20, 10, 40, 30, 20)
print(t12.index(20))  # 1
# 7.3 count 方法：统计指定元素在元组中出现的次数
print(t12.count(20))  # 2
print(t12.count(30))  # 1

# 8. 元组常用的内置函数
# 8.1 max函数： 返回元组中的最大值
print(max(t12))  # 40
# 8.2 min 函数，： 返回元组中的最小值
print(min(t12))  # 10
# 8.3 len 函数：返回元组中元素的个数（元组长度）
print(len(t12))  # 6
# 8.5 sorted 函数：对元组进行排序（不修改原元组，返回一个新d的列表）
a = sorted(t12)
print(type(a))  # <class 'list'>
print(a)  # [10, 10, 20, 20, 30, 40]  注意，这里返回的是列表
print(type(tuple(a)), tuple(a))  # <class 'tuple'> (10, 10, 20, 20, 30, 40)
# 8.6 sum 函数：统计元组中所有元素的和（元素必须是纯数字）
t13 = (1, 2, 3, 4, 5, 6)
t14 = (1.2, 1.1, 1.3, 1.4, 1.5)
t15 = (1,2,3,"4")
print(sum(t13)) # 21
print(sum(t14)) # 6.5
#print(sum(t15)) # TypeError: unsupported operand type(s) for +: 'int' and 'str'

# 实际开发中的元组，不一定是我们自己定义的，比如函数的可变参数*args是一个元组
def demo(*args):
    print(type(args),args)  # <class 'tuple'> (1, 2, 3)
    return sum(args)
print(demo(1,2,3))  # 6
