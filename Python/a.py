import math
import random

#-----------------------------------------------
def test(a, i, n):
	if i == 0:
		return 1
	x = test(a, i / 2, n)
	if x == 0:
		return 0
	y = (x * x) % n
	if ((y == 1) and (x != 1) and (x != (n - 1))):
		return 0

	if (i % 2) != 0:
		y = (a * y) % n
	return y
durum = 0
while durum == 0:
    n = random.randint(2**961,2**972)

    if test(random.randint(2, 3), n - 1, n) == 1:
        print ("p")
        print (n)
        durum = 1
durum = 0
while durum == 0:
    m = random.randint(2**961,2**999)

    if test(random.randint(2, m - 2), m - 1, m) == 1:
        print ("q")
        print (m)
        durum = 1
print ("p*q")
print (m*n)
