def test1(*args):
    # 此处args的值，是一种新的数据类型，叫：元组，我们下一章就去讲元组
    print(args)
test1("1",2)


def test2(**kwargs):
    # 此处kwargs的值，是一种新的数据类型，叫：字典，我们下一章就去讲字典
    print(kwargs)
test2(name="张三",age=12,gender="男")

def test3(*args,**kwargs):
    print("--------")
    print(args)
    print(kwargs)
test3(1,"ss",name="张三",age = 14)


def test4(a,b,*args,c,**kwargs):
    print("-----------")
    print(a)
    print(b)
    print(args)
    print(c)
    print(kwargs)
test4("a","b",1,2,"zas",c="c",d="d",e="e")