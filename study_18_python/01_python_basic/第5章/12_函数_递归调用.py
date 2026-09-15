# 使用递归打印n次你好啊，（从小到大）
def test1( n = 0):
    if n > 1:
        test1(n - 1)
    print(f"你好啊{n}")

test1(5)

# 使用递归打印n次你好啊，（从大到小）
def test2( n = 0):
    print(f"你好啊{n}")
    if n > 1:
        test2(n - 1)
test2(5)

