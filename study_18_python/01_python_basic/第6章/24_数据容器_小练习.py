# 练习一：水果清单
from itertools import count

fruits = {
    "苹果": 4.5,
    "香蕉": 3.2,
    "橙子": 5.8,
    "西瓜": 12.0,
    "草莓": 12.0,
    "哈密瓜": 8.8,

}
# 需求1: 打印所有的水果
for key in fruits:
    print(f"{key}：{fruits[key]}元/斤")
for key in fruits.keys():
    print(f"{key}：{fruits[key]}元/斤")
for key, value in fruits.items():
    print(f"{key}：{value:.2f}元/斤")
# 需求二：找到最贵的水果
# 方法一：遍历
a = []
b = 0
for key, value in fruits.items():
    if value > b:
        a = [key]
        b = value
    elif value == b:
        a.append(key)
print(f"最贵的水果有{'、'.join(a)}，价格为{b:.2f}元/斤")  # 最贵的水果有西瓜、草莓，价格为12.00元/斤

# 方法二：使用max函数，注意这里的key参数  缺点：max() 只返回一个元素，并列最大时返回第一个遇到的。字典的顺序是插入顺序
print(
    f"最贵的水果是{max(fruits, key=fruits.get)}，价格为{fruits.get(max(fruits, key=fruits.get)):.2f}元/斤")  # 最贵的水果是西瓜，价格为12.00元/斤
print(
    f"最贵的水果是{max(fruits.keys(), key=fruits.get)}，价格为{fruits.get(max(fruits.keys(), key=fruits.get)):.2f}元/斤")  # 最贵的水果是西瓜，价格为12.00元/斤

# 练习二：学生成绩表
students = [
    {
        'name': '张三',
        'scores': {'语文': 88, '数学': 92, '英语': 95}
    },
    {
        'name': '李四',
        'scores': {'语文': 75, '数学': 83, '英语': 80}
    },
    {
        'name': '王五',
        'scores': {'语文': 92, '数学': 95, '英语': 88}
    }
]
# 需求1：计算每位学生的平均分
# 方法一：循环
for stu in students:
    stu_score = stu['scores'].values()
    print(f"{stu['name']}的平均成绩为{sum(stu_score) / len(stu_score):.1f}")

# 方法二：用字典推导式自定义
d = {x['name']: round(sum(x['scores'].values()) / len(x['scores']), 1) for x in students}
print(d)  # {'张三': 91.7, '李四': 79.3, '王五': 91.7}

# 需求2：找到总分最高的学生   这里同样可以使用max函数，但是max函数只返回一位
i = []
j = 0
for stu in students:
    cur_score = sum(stu['scores'].values())
    if cur_score > j:
        i = [stu['name']]
        j = cur_score
    elif cur_score == j:
        i.append(stu['name'])
print(f"{'、'.join(i)}的总分最高，最高分为：{j}")

# 练习三：评论内容
comment = '这家奶茶真好喝，环境也不错，就是价格有点贵，好喝好喝好喝！强烈推荐！'
# 需求1： 统计'好喝'出现次数
print(comment.count('好喝'))  # 4
# 需求2：将字符串中的‘贵’替换为‘略高’
print(comment.replace('贵', '略高'))  # 这家奶茶真好喝，环境也不错，就是价格有点略高，好喝好喝好喝！强烈推荐！
# 需求3：是否包含“推荐”两个字
print('推荐' in comment)  # True
