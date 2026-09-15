for i in range(1,31):
    print(f"第{i}天")
    for j in range(1,3):
        print(f"这是第{j}组仰卧起坐")
    print(f"第{i}天任务已完成！明天继续！")
    print("")
print("为期30天健身计划已完成，我的腹肌在闪闪发光")



for i in range(1,10):
    text = ''
    for j in range(1,i+1):
        text += f"  {i}*{j}={i*j}"
    print(text)