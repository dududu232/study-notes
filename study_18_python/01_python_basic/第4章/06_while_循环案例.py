print("您现在身处密室，需要正确回答问题之后，才能逃出密室")
riddle = "你是什么人？"
answer = "你的心上人"


while input(riddle) != answer:
    print("答案错误，请重新回答")

print("逃脱成功！")

