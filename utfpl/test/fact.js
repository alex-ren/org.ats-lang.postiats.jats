function lge(x, y) {
  return x >= y
}

function add(x, y) {
  return x + y
}

function sub(x, y) {
  return x - y
}

function mul(x, y) {
  return x * y
}

function printx(x) {
  console.log(x)
}

var x
var one
one = 1
var mull
function mull(x, y) {
  var ret_20
  var app_21
  app_21 = mul(x, y)
  ret_20 = mul(one, app_21)
  return ret_20
}
function fact(n) {
  var ret_22
  var app_23
  app_23 = lge(n, 1)
  if (app_23) { 
    var app_24
    var app_25
    app_25 = sub(n, 1)
    app_24 = fact(app_25)
    ret_22 = mull(n, app_24)
  } else {
    ret_22 = 1
  }
  return ret_22
}

printx("should see \nfact (10) = \n3628800\n")

printx("fact (10) = ")

var app_26
app_26 = fact(10)
printx(app_26)

printx("\n")
x = fact(10)