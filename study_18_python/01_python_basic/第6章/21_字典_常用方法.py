# 1。 keys 方法：获取字典中所有的键     返回类型是dict_keys类型，这个dict_keys类型特性与set相似，可以被forin遍历，但是不能通过下标访问

d1 = {'张三': 99, '李四': 98, '王五': 97}
keys = d1.keys()
print(type(keys),keys)  # <class 'dict_keys'> dict_keys(['张三', '李四', '王五'])
for key in keys:
    print(key)
# print(keys[0])  # TypeError: 'dict_keys' object is not subscriptable
print(list(keys))   # ['张三', '李四', '王五']

# 2. values 方法：获取字典中所有的值，返回值类型为dict_values类型，与dict_keys相似，这个类型可以被forin遍历，但是不能通过下标访问
d2 = {'张三': 99, '李四': 98, '王五': 97}
values = d2.values()
print(type(values),values)  # <class 'dict_values'> dict_values([99, 98, 97])
for value in values:
    print(value)
# print(values[0])    # TypeError: 'dict_values' object is not subscriptable  类型错误：'dict_values' 对象不可下标访问
print(list(values)) # [99, 98, 97]

# 3. items 方法，获取字典中所有的键值对，返回值类型为dict_items类型，每组键值对以元组的形式呈现，可以被forin遍历，但是不能通过下标访问
d3 = {'张三': 99, '李四': 98, '王五': 97}
items = d3.items()
print(type(items),items)    # <class 'dict_items'> dict_items([('张三', 99), ('李四', 98), ('王五', 97)])
