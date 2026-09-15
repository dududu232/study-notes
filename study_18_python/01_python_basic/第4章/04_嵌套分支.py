age = int(input("请输入你的年龄："))
has_report = input("您是否提交了体检报告？（是/否）")
level = int(input("请输入您的会员等级："))

if 18 <= age <= 45:
    print("你的年龄符和比赛要求")
    if has_report == "是":
        print("您已提交体检报告")
        print("您可以参加比赛")
        if level == 1:
            print("可以领取纪念T恤一件")
        elif level == 2:
            print("可以领取跑鞋一双")
        elif level == 3:
            print("可以领取耳机一副")
        else:
            print("会员等级太低")
    elif has_report == "否":
        print("您尚未提交体检报告，不能参见比赛")
    else:
        print("非法输入")
else:
    print("你的年龄不符和比赛要求")