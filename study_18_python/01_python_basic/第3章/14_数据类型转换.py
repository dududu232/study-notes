#使用str将指定数据转换为字符串
res1 = str(12)
res2 = str(12.2)
res3 = str(0.8e3)
res4 = str(12_000)
print(type(res1),res1)
print(type(res2),res2)
print(type(res3),res3)
print(type(res4),res4)


print("\n使用int()将指定数据转换为整型")
res5 = int(15.6)
res6 = int('79')
res7 = int('  79  ')
res8 = int(48)
print(type(res5),res5)
print(type(res6),res6)
print(type(res7),res7)
print(type(res8),res8)
#以下情况运行报错
# int(' 7 9 ')
# int('你好')
# int('79个')
# int('15.6')


print("\n使用float()将指定数据转为浮点型")
res9 = float(18)
res10 = float('15.6')
res11 = float('  5.7  ')
res12 = float(13.8)
res13 = float('48')  #对比int('15.6')，会发现float()会宽容很多
print(type(res9),res9)
print(type(res10),res10)
print(type(res11),res11)
print(type(res12),res12)
print(type(res13),res13)
#以下情况运行报错
# float('5. 7')
# float('你好')
# float('5.7元')
# float('1.2.3')