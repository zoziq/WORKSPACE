sayi1 = 14
sayac = 0
dizi_sayaci = 0
print 
for sayi2 in range (4,sayi1,1):
    for i in range (2,int(sayi2**0.5)+1,1):
        if sayi2%i == 0:
            if sayi1%i == 0:
                sayac = sayac + 1
    if sayac == 0:
        dizi[dizi_sayaci] = sayi2
        print (sayi2)
        dizi_sayaci = dizi_sayaci + 1
    sayac = 0
        
