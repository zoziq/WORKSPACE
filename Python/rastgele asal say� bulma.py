import random
sayi = int(random.random()*1000)
print (sayi)
sayac = 0
for i in range (2,int(sayi**0.5)+1,1):
    if sayi%i == 0:
        sayac = sayac + 1
if sayac > 0:
    print ("asal sayı değildir")
else:
    print ("asal sayıdır")
