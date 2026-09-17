# 1. 定义有内容的【可变集合】
# 在set中 True和 1 是相等的，所以会被去重，False和 0也一样
s1 = {1,2,2,3,4,4,5,6,7,8,9,10}
s2 = {10,9,8,7,6,5,4,4,3,2,2,1}
s3 = {"你好","hello","你好","atguigu","北京"}
s4 = {"北京","atguigu","你好","hello","你好"}
s5 = {10,"你好",True,1,False,0,12.4}
s6 = {12.4, 0, False, 1, True, '你好', 10}
print(s1)   # {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
print(s2)   # {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
print(s3)   # {'你好', '北京', 'hello', 'atguigu'}
print(s4)   # {'你好', '北京', 'hello', 'atguigu'}
print(s5)   # {False, True, '你好', 10, 12.4}
print(s6)   # {0, 1, '你好', 10, 12.4}
s3.add("11")
print(s3)   # {'你好', '北京', '11', 'hello', 'atguigu'}

# 2. 定义有内容的【不可变集合】
fs1 = frozenset({1,2,2,3,4,4,5,6,7,8,9,10})
fs2 = frozenset({10,9,8,7,6,5,4,4,3,2,2,1})
fs3 = frozenset({"你好","hello","你好","atguigu","北京"})
fs4 = frozenset({"北京","atguigu","你好","hello","你好"})
fs5 = frozenset({10,"你好",True,1,False,0,12.4})
fs6 = frozenset({12.4, 0, False, 1, True, '你好', 10})
print(fs1)
print(fs2)
print(fs3)
print(fs4)
print(fs5)
print(fs6)
# fs3.add("11")   # AttributeError: 'frozenset' object has no attribute 'add'

# 3. frozenset 接收的参数，可以是任意可迭代对象，但最终返回的一定是【不可变集合】
fs7 = frozenset([1,2,3,4,5])
fs8 = frozenset((1,2,3,4,5))
fs9 = frozenset("12345")
print(type(fs7),fs7)    # <class 'frozenset'> frozenset({1, 2, 3, 4, 5})
print(type(fs8),fs8)    # <class 'frozenset'> frozenset({1, 2, 3, 4, 5})
print(type(fs9),fs9)    # <class 'frozenset'> frozenset({'4', '2', '5', '3', '1'})

# 4. 定义空集合【可变集合】
s7 = set()
print(type(s1),s1)  # <class 'set'> {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
# 不能直接写{}来定义空集合，{}定义的是空字典
s8 = {}
print(type(s8),s8)  # <class 'dict'> {}

# 5. 定义空集合【不可变集合】
fs10 = frozenset()
print(type(fs10),fs10)  # <class 'frozenset'> frozenset()

# 6. 集合中不能嵌套【可变的数据容器】，只能嵌套【不可变的数据容器】
# 集合底层是哈希表，元素必须可哈希，而且哈希值在放进集合后不能变。
# s9 = {1,2,[1,2]}    # TypeError: unhashable type: 'list'
s10 = {1,2,(1,2)}
print(type(s10),s10)    # <class 'set'> {1, 2, (1, 2)}
s11 = {1,2,"12"}
print(type(s11),s11)    # <class 'set'> {'12', 2, 1}
# s12 = {1,2,{1,2}}   # TypeError: unhashable type: 'set'
s13 = {1,2,frozenset({1,2})}
print(type(s13),s13)    # <class 'set'> {1, 2, frozenset({1, 2})}