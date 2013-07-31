def lge(x, y):
  return x >= y

def add(x, y):
  return x + y

def sub(x, y):
  return x - y

def mul(x, y):
  return x * y

def printx(x):
  print x

one = 1

def mull (x, y):
  app_21 = mul(x, y)
  ret_20 = mul(one, app_21)
  return ret_20

def fact (n):
  app_23 = lge(n, 1)
  if app_23: 
    app_25 = sub(n, 1)
    app_24 = fact(app_25)
    ret_22 = mull(n, app_24)
  else:
    ret_22 = 1

  return ret_22


printx("should see \nfact (10) = \n3628800\n")

printx("fact (10) = ")


app_26 = fact(10)
printx(app_26)

printx("\n")
x = fact(10)