# 定义一个列表
nums1 = [10,20,30,40,50]

# 使用while循环遍历列表
index = 0
while index<len(nums1):
    print(index,nums1[index])
    index +=1

# for 循环遍历列表
# 写法一：
for item in nums1:
    print(item)
# 写法二
print(range(7))
for index in range(len(nums1)):
    print(index,nums1[index])
# 写法三：使用enumerate 函数可以同时拿到下标跟元素，并且可以使用 start 参数指定开始遍历的下标
print(enumerate(nums1,start=2))
for index,item in enumerate(nums1,start=2):
    print(index,item)
