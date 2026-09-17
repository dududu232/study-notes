# 1.使用index方法，查找指定元素在列表中第一次出现的下标，返回值是：元素下标

nums1 = [1,2,3,4,1,[5,6]]
print(nums1.index(3))   # 2
#print(nums1.index(5)) # ValueError: 5 is not in list
print(nums1.index([5,6]))   # 5


# 2. 使用 count 方法，统计某个元素在列表中出现的次数，返回值是：元素出现的次数
nums2 = [10,20,10,30,50,10,[10,10]]
print(nums2.count(10))  # 3

# 3. 使用 reverse 方法，对列表进行反转（会改变原列表）
nums3 = [10,20,30,40,10]
nums3.reverse()
print(nums3)    # [10, 40, 30, 20, 10]

# 4. 使用 sort 方法，对列表排序（默认从小到大），若想从大到小，可将 reverse 参数设为True
# 4.1 若列表中的元素：都是数字，则按照数字的大小顺序进行排序
nums4 = [1,4,2,4,6,3]
nums4.sort()
print(nums4)    # [1, 2, 3, 4, 4, 6]
nums4.sort(reverse=True)
print(nums4)    # [6, 4, 4, 3, 2, 1]
# 4.2 若列表中的元素：既有数字，又有字符串，那就会报错
nums5 = [1,3,2,5,"尚硅谷"]
# nums5.sort()    # TypeError: '<' not supported between instances of 'str' and 'int'
# 4.3 若列表中的元素：都是字符串，则按照字符串的 Unicode 编码进行大小排序
msg_list = ["北京","尚硅谷","你好啊"]
print(ord('北'),ord('尚'),ord('你'))   # 21271 23578 20320
msg_list.sort()
print(msg_list) # ['你好啊', '北京', '尚硅谷']
msg_list.sort(reverse=True)
print(msg_list) # ['尚硅谷', '北京', '你好啊']

# 备注：所有的列表方法，都只作用于“当前层”的元素（浅层操作），不会自动进入嵌套的“里层”结构中