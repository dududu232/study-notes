# 1.  定义有内容的字典
d1 = {"张三": 72, "李四": 78, "王五": 98}
print(type(d1), d1)  # <class 'dict'> {'张三': 72, '李四': 78, '王五': 98}

# 2. 字典中的key不能重复，若出现重复，则后写的会覆盖之前写的
d2 = {"张三": 72, "李四": 78, "王五": 98, "张三": 99, "李四": 98, "王五": 97}
print(type(d2), d2)  # <class 'dict'> {'张三': 99, '李四': 98, '王五': 97}

# 3. 定义空字典
d3 = {}
d4 = dict()
print(type(d3), d3)  # <class 'dict'> {}
print(type(d4), d4)  # <class 'dict'> {}

# 4. 字典中的key必须是不可变类型，但值可以是任意类型
d5 = {"张三": 74}
d6 = {250: 74}
d7 = {("抽烟", "喝酒"): 74}
# d8 = {["抽烟","喝酒"]: 74}  # TypeError: unhashable type: 'list'

# 5. 字典可以嵌套
student_dict = {
    1: {
        "name": "张三",
        "age": 17
    },
    2: {
        "name": "李四",
        "age": 18
    },
}
print(type(student_dict),student_dict)  # <class 'dict'> {1: {'name': '张三', 'age': 17}, 2: {'name': '李四', 'age': 18}}

