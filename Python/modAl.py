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

sayi = random.randint(2**1023,2**1024)

print modAl(26,sayi-1,sayi)
print modAl(103157, 70355, 140669)
print modAl(2,7830457,10000000000)
