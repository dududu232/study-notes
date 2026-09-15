# 新增操作
# 方式一：通过列表的append方法，在列表尾部追加一个元素
nums1 = [10,20,30,40]
nums1.append(50)
print("nums1",nums1)    # nums1 [10, 20, 30, 40, 50]

# 方式二：通过列表的insert方法，在列表的指定下标处添加一个元素
nums2 = [10,20,30,40]
nums2.insert(2,666)
print("nums2",nums2)    # nums2 [10, 20, 666, 30, 40]

# 方式三：通过列表的extend方法，将可迭代对象中的内容依次取出，追加到列表尾部
nums3 = [10,20,30,40]
nums3.extend("尚硅谷")
print("nums3",nums3)    # nums3 [10, 20, 30, 40, '尚', '硅', '谷']
nums3.extend(range(1,4))
print("nums3",nums3)    # nums3 [10, 20, 30, 40, '尚', '硅', '谷', 1, 2, 3]
nums3.extend([70,80,90])
print("nums3",nums3)    # nums3 [10, 20, 30, 40, '尚', '硅', '谷', 1, 2, 3, 70, 80, 90]


# 删除操作
# 方式一：通过列表的pop方法删除
nums4 = [10,20,30,40]
a = nums4.pop(1)
print("a",a)    # a 20
print("nums4",nums4)    # nums4 [10, 30, 40]

# 方式二：remove方法
nums5 = [10,20,30,40,10]
nums5.remove(10)
print("nums5",nums5)    # nums5 [20, 30, 40, 10]

# 方式三：使用clear方法
nums6 = [10,20,30,40]
nums6.clear()
print("nums6",nums6)    # nums6 []

# 使用del关键字
nums7 =  [10,20,30,40]
del nums7[1]
print("nums7",nums7)    # nums7 [10, 30, 40]

# 修改操作
nums8 =  [10,20,30,40]
nums8[0] = 50
print("nums8",nums8)    # nums8 [50, 20, 30, 40]

# 查询操作
nums9 = [10,20,30,40]
print("nums9[1]",nums9[1])  # nums9[1] 20