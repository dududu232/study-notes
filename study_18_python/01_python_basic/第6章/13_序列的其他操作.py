# 1. 序列相加
# 1.1 列表相加
list1 = [1, 2, 3, 4, 5]
list2 = [6, 7, 8, 9, 10]
print(list1 + list2)  # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# 1.2 元组相加
tuple1 = (1, 2, 3, 4, 5)
tuple2 = (6, 7, 8, 9, 10)
print(tuple1 + tuple2)  # (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

# 1.3 字符串相加
msg1 = "hello"
msg2 = "atguigu"
print(msg1 + msg2)  # helloatguigu

# 1.4 错误示例
# print(list1 + msg1) # TypeError: can only concatenate list (not "str") to list

# 2. 序列相乘

print(list1 * 2)  # [1, 2, 3, 4, 5, 1, 2, 3, 4, 5]
print(tuple1 * 2)  # (1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
print(msg1 * 3)  # hellohellohello
print(list1 * 0)  # []
print(list1 * -1)  # []
# print(list1 * 1.5)    # TypeError: can't multiply sequence by non-int of type 'float'
