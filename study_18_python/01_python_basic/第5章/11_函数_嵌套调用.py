def greet(*,name = '',msg = ''):
    print(f"我叫{name}，我想说的话在下面：")
    speak()
    print("我说完了")

def speak(msg = ''):
    print("---------------")
    print(msg)
    print("---------------")

greet(name = "张三",msg = "你好啊")


#test()  # 未解析的引用 'test'  greet函数在speak函数上方，并且greet函数调用了speak函数，在greet函数调用speak函数的时候并没有报错，但是在本行代码却报错，
# 原因：定义函数的时候，Python不会执行函数体中的代码，只有调用的时候才会，按照文件从上往下的顺序，在执行greet函数的时候speak函数已经定义完成
def test():
    print("test函数执行完")
