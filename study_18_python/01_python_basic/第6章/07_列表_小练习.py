score_list = []

while True:
    msg = input("请输入成绩：")
    if msg == "结束":
        break
    else:
        score_list.append(int(msg))

if score_list:
    print(f"您输入的成绩为：{score_list}")
    print(f"总人数：{len(score_list)}")
    print(f"最高分：{max(score_list)}")
    print(f"最低分：{min(score_list)}")

    hege = 0
    youxiu = 0
    for item in score_list:
        if item >= 60:
            hege += 1
        if item >= 90:
            youxiu += 1
    print(f"合格人数：{hege}")
    print(f"合格率：{hege / len(score_list):.1%}")
    print(f"优秀人数：{youxiu}")
    print(f"优秀率：{youxiu / len(score_list):.1%}")
    print(f"平均成绩：{sum(score_list) / len(score_list):.1f}")
else:
    print("无输入")