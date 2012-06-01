import random

sayac = 1
while (sayac > 0):
    sayac = 0
    sayi = random.randint(2**1000,2**1200)
    for i in range (2,112228,1):
        if sayi%i == 0:
            sayac = sayac + 1
print ("ilk asal sayi")
print (sayi)
print ("\n")

carpan = sayi

sayac = 1
while (sayac > 0):
    sayac = 0
    sayi = random.randint(2**1000,2**1200)
    for i in range (2,112228,1):
        if sayi%i == 0:
            sayac = sayac + 1
print ("ikinci asal sayi")
print (sayi)
print ("\n")

print ("asal sayilarin birer eksiklerinin carpimi")
print ((carpan - 1) * (sayi - 1))

