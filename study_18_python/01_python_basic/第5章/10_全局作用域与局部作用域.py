a = 100
b = 200
if 1==1:
    e = 100000

def test1():
   # print("test1打印a", a)    #这行报错证明只要在某个函数内再次声明某个全局变量，该函数内所有地方都访问不到该全局变量
    a = 300 # 这里的a = 300会在test1局部声明一个a变量，导致test1内部再也无法访问全局a变量
    print("test1打印a",a)
    print("test1打印d",b)
test1()

print("全局打印a",a)

def test2():
    global a
    a = 300 #使用global a时，为a重新赋值会将全局的a变量重新赋值
    print("test2打印a",a)
    print("test2打印e",e)

    c = 1
    if 1==1:
        c = 2
        d = 2
        print("test2的if内打印c",c)
    print("test2的if外打印c",c)
    print("test2的if外打印d",d)


test2()
print("全局打印a",a)
print("全局打印e",e)
# print(d)  未解析的引用 'd'

# 这里c和d变量证明Python中的局部作用域与全局作用域是仅仅针对于函数内部与函数外部，e变量和d变量证明如果条件通过，变量会声明在if所在的作用域（要么函数外，要么函数内）
