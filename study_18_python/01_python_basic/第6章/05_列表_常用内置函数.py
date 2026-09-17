# 使用内置的 sorted 函数，返回一个排序后的新容器（不改变原容器，默认顺序：从小到大）
# 1.1 若容器中的元素：都是数字，则按照数字的大小进行排序
nums1 = [23,11,12,43]
print(sorted(nums1)) # [11, 12, 23, 43]
print(sorted(nums1,reverse=True))    # [43, 23, 12, 11]
# 1.2 若容器中的元素：既有数字，又有字符串，那就会报错
nums2 = [23,11,12,43,"尚硅谷"]
#sorted(nums2)   # TypeError: '<' not supported between instances of 'str' and 'int'
# 1.3 若容器中的元素：都是字符串，则按照字符串的 Unicode 编码大小进行排序。
nums3 = ["北京","尚硅谷","你好啊"]
print(ord("北"),ord("尚"),ord("你"))   # 21271 23578 20320
print(sorted(nums3))    # ['你好啊', '北京', '尚硅谷']
print(sorted(nums3,reverse=True))    # ['尚硅谷', '北京', '你好啊']
#测试二维数组
nums5 = [[2, 3], [1, 5], [2, 1], [1, 2]]
print(sorted(nums5))    # [[1, 2], [1, 5], [2, 1], [2, 3]]

# 2. 使用内置的 len 函数，获取容器中元素的数量，返回值是元素总数量
nums4 = [1,2,3,[4,1]]
print(len(nums4))   # 4

# 3. 使用内置的 max 函数，获取容器中的最大值，返回值是：最大值
# 3.1 如果容器中的元素：都是数字，那 max 返回的就是最大的数
nums6 = [1,2,3,0,9,5]
print(max(nums6))   # 9
# 3.2 如果容器中的元素：既有数字又有字符串，那 max 会报错
nums7 = [1,5,7,2,"尚硅谷"]
# max(nums7)  # TypeError: '>' not supported between instances of 'str' and 'int'
# 3.3 如果容器中的元素：都是字符串，那 max 会返回； Unicode 编码最大的字符，这里参考sorted方法的比较
msg_list = ["北京","尚硅谷","你好啊"]
print(max(msg_list))    #尚硅谷
# 3.4 max 函数也可以接收多个值，并筛选出最大值
print(max(33,45,12,78,99))  # 99
print(max([1,2,3],[1,2,4])) # [1, 2, 4]

# 4.使用内置的 min 函数，获取容器中的最小值，返回值是最小值。
# 备注：min 函数的使用方式与注意点与 max 函数一样，只不过 min 函数返回的是最小值
nums8 = [1,2,3,0,9,5]
print(min(nums8))   # 0

# 5. 使用内置的 sum 函数，对容器中的数据进行求和（元素只能是数值）。
nums8 = [1,2,3,0,9,5]
print(sum(nums8))   # 20
nums10 = [1,2,3,0,9,5,"尚硅谷"]
# sum(nums10) # TypeError: unsupported operand type(s) for +: 'int' and 'str'
nums11  = [1,2,3,0,9,[1,2]]
# sum(nums11) # TypeError: unsupported operand type(s) for +: 'int' and 'list'
nums12 = "尚硅谷"
# sum(nums12) # TypeError: unsupported operand type(s) for +: 'int' and 'str'
nums13 = "123456"
# sum(nums13) # TypeError: unsupported operand type(s) for +: 'int' and 'str'