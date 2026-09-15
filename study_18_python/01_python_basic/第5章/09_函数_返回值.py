# 定义函数
def add(n1 = 0,n2 = 0):
    print(f"add我收到了{n1}、{n2}")
    return n1 + n2


def add1(n1 = 0,n2 = 0):
    print(f"add1我收到了{n1}、{n2}")



#调用函数
print(add(1,2))
print(add1(1,2))    # None 没有返回值的函数返回None