d1 = {'张三': 99, '李四': 98, '王五': 97}
for key in d1:
    print(f"{key}的成绩为{d1[key]}")

for key in d1.keys():
    print(f"{key}的成绩为{d1[key]}")

# 效率最高
for k,v in d1.items():
    print(f"{k}的成绩为{v}")