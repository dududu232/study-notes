# None是个特殊的字面量，它表示空值 / 无值 / 无意义
msg = None

# None 的类型是NoneType
print(type(msg))    # <class 'NoneType'>

# None 转为bool值为False,不能转为int和float
print(bool(msg))   # False
print(str(msg) + "_1")   # None_1


# 不能参与数学运算，也不能参与字符串拼接
# result1 = msg + 1
# result2 = msg + 'hello'


