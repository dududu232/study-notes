
def test3(n = 1):
    if n>1:
        return n*test3((n-1))
    else:
        return 1

print(test3(4))