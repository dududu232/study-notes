# and用于判断其两侧的值，是否都为True
print(True and True)
print(False and True)
print(True and False)
print(False and False)

# and 具备“逻辑短路”的能力
print(False and 3 / 0)
# print(True and 3 / 0) #报错

# and返回的不一定是布尔值，它返回的是某个参与计算的值本身
# 规则：and会先看左边，如果左边是“假”，就直接返回左边，否则返回右边
# 备注：若参与and运算的值不是布尔值，那Python会自动转为布尔值（只是底层转，隐式），然后再进行逻辑操作
print(2 - 2 and True)  # 0
print(2 - 2 and 3 / 0)  # 0
print('' and True)  # ''
print(True and 8 / 2)  # 4.0
print(3 + 3 and 3 * 4)  # 12

# or用于判断其两侧，是否至少有一个为True
print(True and True)
print(True and False)
print(False and True)
print(False and False)

# or同样具备“逻辑短路”的能力
print(True or 3 / 0)
#print(False or 3 / 0)   #报错

# and返回的不一定是布尔值，它返回的是某个参与计算的值本身
# or会先看左边，左边如果为“真”，直接返回左边，否则返回右边
# 备注：若参与or运算的值不是布尔值，那Python会自动转为布尔值（只是底层转，隐式），然后再进行逻辑操作
print(7 -2 or False)
print('你好' or '尚硅谷')
print(False or 8 / 2)
print(2 -2  or 3 * 4)

# not用于取反，返回的值一定是布尔值
# 备注：若参与not计算的值不是布尔值，那Python会自动转为布尔值（显式），然后再进行逻辑操作
print(not False)
print(not True)
print(not 3 > 2)
print(not 3 < 2)

print(not 0)
print(not "你好")
