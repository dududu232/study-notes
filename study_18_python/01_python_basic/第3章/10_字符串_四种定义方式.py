message1 = ('尚'
            '硅谷')
message2 = '''尚
硅谷'''
message3 = ("尚"
            "硅谷")
message4 = """尚
硅谷"""


print(message1)
print(message2)
print(message3)
print(message4)



"""
Python 是如何比较两个字符串是否相等？ 
核心函数：unicode_eq(a, b)


unicode_eq 的比较步骤：
a == b
  │
  ├─ a 和 b 是同一个对象？ → 是 → True
  │
  ├─ 长度不同？ → 是 → False
  │
  ├─ 空字符串？ → 是 → True
  │
  ├─ 存储宽度 kind 不同？ → 是 → False
  │
  └─ memcmp 逐字节比较    memcmp是c标准库函数
         │
         ├─ 完全一样 → True
         └─ 有差异 → False
"""