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
            sayi = random.randint(2**1023,2**1024)
            if sayi/2*2 == sayi | sayi/5*5 == sayi:
                sayi = 0
        for i in range (0,50,1):
                kontrolSayisi = random.randint(2,10000)
                asal = modAl(kontrolSayisi,sayi-1,sayi)
                if asal != 1:
                        break
print ("p")
print (sayi)

sayi2 = sayi - 1

asal = 0
while (asal != 1):
        sayi = 0
        while (sayi == 0):
            sayi = random.randint(2**1023,2**1024)
            if sayi/2*2 == sayi | sayi/5*5 == sayi:
                sayi = 0
        for i in range (0,50,1):
                kontrolSayisi = random.randint(2,10000)
                asal = modAl(kontrolSayisi,sayi-1,sayi)
                if asal != 1:
                        break
print ("q")
print (sayi)
print ("(p-1)*(q-1)")
print ((sayi-1)*sayi2)
