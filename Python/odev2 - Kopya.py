import random


def modAl(taban, us, mod):
	dizi = []
	while us:
		dizi.append(us%2)
		us /= 2
	cozum = 1
	dizi.reverse()
	for x in dizi:
		cozum = (cozum*cozum)%mod
		if x:
			cozum = (cozum*taban)%mod
	return cozum


asal = 0
while (asal != 1):
        sayi = 0
        while (sayi == 0):
            sayi = random.randint(2**23,2**24)
            if sayi/2*2 == sayi | sayi/5*5 == sayi:
                sayi = 0
        for i in range (2,52,1):
                asal = modAl(i,sayi-1,sayi)
                if asal != 1:
                        break
print ("p")
print (sayi)


       
