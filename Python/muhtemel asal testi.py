import math
import random

def modAl(taban, us, mod):
	if us == 0:
		return 1
	x = modAl(taban, us / 2, mod)
	if x == 0:
		return 0
	y = (x * x) % mod
	if (us % 2) != 0:
		y = (taban * y) % mod
	return y

def asalBul():
        p = int(random.random()*2**128)
        if (p % 2 == 0):
                p = p - 1
        print "Denenen sayi"
        print p
        for a in range(2,52,1):
                if modAl(a, (p-1)/2, p) == 1 or modAl(a, (p-1)/2, p) == p - 1:
                        print ('1/2**',a-1,"den daha az ihtimalle asal degildir")
                else:
                        print ("kesinlikle asal degildir")
                        return 0
x = 0
while(x == 0):
        x = asalBul()
        

#modAl(3,12,7) -------> 3^12 mod17
#islemine bakacak olursak;
#3^1 mod17 = 3
#3^2 mod17 = 9
#3^3 mod17 = 10 -------> ((3^1 mod17)^2)*3 mod17 = 3^2*3 mod17 = 10
#3^4 mod17 = 13
#3^5 mod17 = 5
#3^6 mod17 = 15 -------> (3^3 mod17)^2 mod17 = 10^2 mod17 = 15
#3^7 mod17 = 11
#3^8 mod17 = 16
#3^9 mod17 = 14
#3^10 mod17 = 8
#3^11 mod17 = 7
#3^12 mod17 = 4 -------> (3^6 mod17)^2 mod17 = 15^2 mod17 = 4

#ussu surekli 2'ye bolerek, en fazla 128 adimda modu bulabiliyor.









        






#	if ((y == 1) and (x != 1) and (x != (mod - 1))):
#		return 0
