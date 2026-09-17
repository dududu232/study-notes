# 1. 集合A.difference(集合B)
# 找出集合A中，不同于集合B的元素（返回新集合）,就是说结果 = 集合A - 集合B
s1 = {10,20,30,40,50}
print(s1.difference({10,90,40}))    # {50, 20, 30}

# 2. 集合A.difference_update(集合B)
# 从集合A中，删除集合b中存在的元素，会改变集合A     集合A = 集合A - 集合B
s2 =  {10,20,30,40,50}
s2.difference_update({10,60,50})
print(s2)   # {20, 40, 30}

# 3. 集合A.union(集合B)：
# 将集合A和集合B合并，返回一个新的集合  结果 = 集合A ∪ 集合B   ∪：并集
s3 =  {10,20,30,40,50}
print(s3.union({80,90}))    # {80, 50, 20, 90, 40, 10, 30}

# 3. 集合A.issubset(集合B)
# 判断集合A是否为集合B的子集，返回True或False    集合A ⊆ 集合B 是否成立
s4 =  {10,20,30}
s5 =  {10,20,30,40,50}
s6 = {10,20,30,60}
print(s4.issubset(s5))  # True
print(s6.issubset(s5))  # False
print(s6.issubset(s6))  # True  可以看出，不是真子集，也返回True


# 4. 集合A.issuperset(集合B)
# 判断集合A是否是集合B的超集，返回True或False       集合A ⊇ 集合B 是否成立
s7 = {10,20,30,40,50}
s8 = {10,20,30}
s9 = {10,20,30,60}
print(s7.issuperset(s8))   # True
print(s7.issuperset(s9))   # False
print(s7.issuperset(s7))   # True   可以看出，不是真超集也返回True

# 5. 集合A.isdisjoint(集合B)
# 判断集合A和集合B是否没有交集   是否满足 集合A ∩ 集合B = ∅（空集）  但凡集合A和集合B有一个公共元素，就返回False
s10 = {1,2,3,4,5}
s11 = {1,9,10}
s12 = {10,9,8}
print(s10.isdisjoint(s11))  # False
print(s10.isdisjoint(s12))  # True
