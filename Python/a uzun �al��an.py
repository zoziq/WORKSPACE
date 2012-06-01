import math
import random

def modAl(taban, us, mod):
        if (us == 0 or taban == 1): #baslangic kontrolu
                return 1
        usTemp = 1; #gecici us degiskeni
        bolumdenKalan = taban % mod;
        #2^1 mod9 = 2
        #2^2 mod9 = 4
        #2^3 mod9 = 8
        #2^4 mod9 = 7
        #2^5 mod9 = 5
        #2^6 mod9 = 1
        #yukardakine benzer hesaplama yaparak sonucu bolumdenKalan
        #degiskenine atar
        while (bolumdenKalan != 1 and usTemp < us):
                print ("while")
                usTemp = usTemp + 1
                bolumdenKalan = (taban * bolumdenKalan) % mod;
        #while dongusunun yukardakine benzer olarak, 1'i bulana kadar
        #gecen adim sayisinin, us degerinden buyuklugunu kontrol eder, yani;
        #2^6 mod9 = 1 ise modu 1 olan kismi cikarir.
        #2^12 mod9 = 1
        #2^18 mod9 = 1 gibi...
        if (usTemp != us):
                us = us % usTemp;
        #us degeri while dongusunun donme sayisina tam bolunuyorsa,
        #yani, yukardaki if kontrolunden us degeri 0 oluyor ise sonucu 1 olarak veriyor
        if (us != 0):
                #eger 0 degilse bolumdenKalan degiskenini guncelleyerek,
                #ilk degerini veriyor
                bolumdenKalan = taban % mod;
        #son olarak moddan kalan degerleri hesapliyor
        for i in range (1, us, 1):
                print ("for")
                bolumdenKalan = (taban * bolumdenKalan) % mod
        return bolumdenKalan;

print modAl (2,11111,11111)

























