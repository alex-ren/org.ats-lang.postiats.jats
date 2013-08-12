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

def mull_gv14 (x_gv15, y_gv16):
  app_gv20 = mul(x_gv15, y_gv16)
  ret_gv19 = mul(1, app_gv20)
  return ret_gv19

def fact_gv13 (n_gv17):
  app_gv22 = gte(n_gv17, 1)
  if app_gv22: 
    app_gv24 = sub(n_gv17, 1)
    app_gv23 = fact_gv13(app_gv24)
    ret_gv21 = mull_gv14(n_gv17, app_gv23)
  else:
    ret_gv21 = 1

  return ret_gv21

printx("should see \nfact (10) = \n3628800\n")
printx("fact (10) = ")
app_gv25 = fact_gv13(10)
printx(app_gv25)
printx("\n")
xx_gv18 = mul(3, 4)
x_gv12 = fact_gv13(10)