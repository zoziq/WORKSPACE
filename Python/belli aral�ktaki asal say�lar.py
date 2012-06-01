sayac = 0
a = 0
dizi = []
for sayi in range(2,10000,1):
    for i in range(2,int(sayi**.5)+1,1):
        if int(sayi / i) * i == sayi:
            sayac = sayac + 1
            break
    if sayac == 0:
        dizi.append(sayi)
        a = a + 1
    else:
        sayac = 0;
print dizi
