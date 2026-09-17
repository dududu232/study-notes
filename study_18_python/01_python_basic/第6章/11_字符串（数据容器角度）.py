# 1. 字符串的下标
msg = "welcome to atguigu"
print(msg[0])   # w
print(msg[1])   # e
print(msg[-1])  # u
print(msg[-2])  # g

# 2. 字符串中的字符，不可修改
#msg[0] = "m"   # TypeError: 'str' object does not support item assignment

# 3. 字符串不能嵌套
msg1= 'welcome to "hello" atguigu'
print(msg1[11],msg1[12])    # " h

# 4. 常用方法：字符串相关的方法，都是不修改原字符串的，因为字符串不允许修改
# 4.1 index 方法：获取指定字符串片段在字符串中第一次出现的下标
print(msg.index("w"))  # 0
print(msg.index("t"))  # 8
print(msg.index("we")) # 0
print(msg.index("to")) # 8
# 4.2 split 方法：将字符串按照指定字符串片段进行分隔，并返回一个列表
print(msg.split(" "))  # ['welcome', 'to', 'atguigu']
msg3 = "welcome@@to@@atguigu"
print(msg3.split("@@")) # ['welcome', 'to', 'atguigu']
# 4.3 replace 方法：将字符串中的某个字符串片段，全部替换为目标字符串
print(msg.replace("g","G"))    # welcome to atGuiGu
print(msg.replace("gu","GU"))    # welcome to atGUiGU
# 4.4 count 方法：统计指定字符传片段在字符串中出现的次数
print(msg.count('w'))   # 1
print(msg.count('gu'))  # 2
# 4.4 strip 方法：从当前字符串中删除：参数字符串（死亡名单）中的任意字符
# 规则：从字符串两端开始删除，直到遇到第一个不在参数字符串（死亡名单）中的字符，两端分别计算
msg4 = "666尚6硅6谷666"
print(msg4.strip("6"))  # 尚6硅6谷
print(msg4.strip("6543"))  # 尚6硅6谷
msg5 = "1234尚12硅34谷4321"
print(msg5.strip("1234"))   # 尚12硅34谷
print(msg5.strip("12")) # 34尚12硅34谷43
print(msg5.strip("12456")) # 34尚12硅34谷43
msg6 = " 尚硅谷 "
msg7 = "\n尚硅谷\n"
msg8 = "    尚硅谷   "
print(msg6.strip()) # 尚硅谷,去除了两端的空格
print(msg7.strip()) # 尚硅谷,去除了两端换行符\n
print(msg8.strip()) # 尚硅谷,去除了两端的制表符

# 5. 常用内置函数：
# 5.1 len函数：统计字符串中，字符的个数（计算字符串的长度）
print(len(msg)) # 18
# 5.2 max 函数：返回字符串中 Unicode 编码值最大的字符（不是下标）
print(max(msg)) # w
# 5.3 min 函数：返回字符串中 Unicode 编码值最小的字符（不是下标）
print(min(msg)) # ''
# 5.4 sorted 函数：将字符串按 Unicode 编码值排序，返回新列表（不改变原字符串）
print(sorted(msg))  # [' ', ' ', 'a', 'c', 'e', 'e', 'g', 'g', 'i', 'l', 'm', 'o', 'o', 't', 't', 'u', 'u', 'w']
print(sorted(msg,reverse=True)) # ['w', 'u', 'u', 't', 't', 'o', 'o', 'm', 'l', 'i', 'g', 'g', 'e', 'e', 'c', 'a', ' ', ' ']

# 字符串的循环遍历
print("字符串的循环遍历")
print('方式一：while遍历')
index = 0
while index < len(msg):
    print(index,msg[index])
    index +=1
print('方式二：forin遍历')
for item in msg:
    print(item)
print('方式三：for range遍历')
for index in range(len(msg)):
    print(index,msg[index])
print('方式四：for enumerate遍历')
for index,item in enumerate(msg):
    print(index,item)