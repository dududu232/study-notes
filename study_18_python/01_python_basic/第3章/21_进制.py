# 0b开头表示二进制
num1 = 0b11001
# 0o开头表示八进制
num2 = 0o1034
# 0x开头表示十六进制
num3 = 0x1cf


# Python中的非十进制数字，只是代码层面的编写方式，是给程序员看的
# Python在对上面的num1、num2、num3进行计算、打印等操作时，会自动将其转为十进制
print(num1,num2,num3)
print(num1 + 1)
print(str(num2))
print(num3 > 400)


# 使用bin()将十进制转为二进制
res1 = bin(25)
# 使用oct将十进制转为八进制
res2 = oct(540)
# 使用hex()将十进制转为十六进制
res3 = hex(463)
# 注意： bin() oct() hex()他们返回的值类型都是字符串
print(res1,res2,res3)
print(type(res1),type(res2),type(res3))

# 使用int()将指定进制的数，转为十进制数字
print(int('0b11001', 2),int('0o1034', 8),int('0x1cf', 16))
