text = input("请输入加密的文字：")
secret = ""
for t in text:
    secret +=  chr(ord(t)+1)

print(f"加密后的文字为：{secret}")


text1 = ""
for t in secret:
    text1 += chr(ord(t)-1)

print(f"原文字：{text1}")