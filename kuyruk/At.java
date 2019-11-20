package kuyruk;

import java.util.Scanner;

public class At {

    Yer şuan; //matrisin x i
    Yer sonra; //matrisin y si
    bağlı_liste BL = new bağlı_liste(); //bağlı listemizi nesne aldık. BL artık bağlı listemizin nesnesi

    public At(Yer şuan, Yer sonra) { //Kullanıcıdan gelen yerler

        this.şuan = şuan;
        this.sonra = sonra;
        Kuyruk K = new Kuyruk(1); //bağlı liste henüz boş. o yüzden şuan ı ekleyecez. şuan da bulunulan konum var yani başlangıç
        K.ekle(şuan); //Şuan ı kuyruğa ekledik

        liste L = new liste(K); // bağlı listeye kuyruğu atmak için L nesnesi aldık liste den. listeye parametre olarak K kuyruğunu yani yukarıda eklediğimiz kuyruğu gönderdik.
        BL.bağlı_listeye_ekle(L); // L deki kuyruğu bağlı listeye ekledik
// bu eklemeler liste ve kuyruk boş olduğundan döngüde sıkıntı olmasın diye.
    }

    public int hesapla() {
        int sonuç = 0; //adım sayısını tutar
        boolean kontrol = false;
        while (!kontrol) { //tru olursa yani sonucu bulursa girmez
            int elemanSayisi = BL.bağlı_esayısı; //bağlı listedeki eleman sayımızı eleman sayısına attık.
            liste tmp = BL.bas; // listeyi gezebilmek için tmp tuttuk
            int sayac = 0;
            while (elemanSayisi > sayac) { //sayaç elemansayısından yani bağlı listedeki eleman sayısından büyük ya da eşit olanaca girer döngüye. burada BL.bağlı_esayısı>sayaç demeyip yukarda elemanSayısı değişkenine atama yapıp karşılaştırma nedenimiz aşağıda listeye yeni eklemeler yapacağımızdan sonsuz döngüye girerdi. Bize o anki ekli olan lazım
                while (!tmp.içerik.bosMu()) { //tmp nin içeriği boş değilse gir
                    Yer yer = tmp.içerik.cikar(); //tmp nin içeriğindeki (kuyruktaki) Yer tipi döndürülür yer değişkenine atılır. buradaki yer ler karıştırılmasın. büyük Y ile başlayan Yer bizim oluşturduğumuz konumu tutan class, küçük y ile başlayan yer ise değişken.
                    if (yer.X == sonra.X && yer.Y == sonra.Y) { //şart sağlanırsa gir. Çünkü ulaşılmıştır
                        kontrol = true;
                        return sonuç; //adım sayısını dönder
                    } else {
                        BL.bağlı_listeye_ekle(new liste(yeniyer(yer))); //şart sağlanmazsa buraya gir yer deki Yeri yeniyere at ve döndürülen kuyruğu bağlı listeye ekle.
                    }
                }
                sayac++; //sayacı artır. çünkü elemanSayısı sayaca eşit olunca o whileye girmeyecek ve bağlı listenin elemanlarını tek tek gezmesi için
                tmp = tmp.ileri; //tmp yi ileri attık eleman içeriğine girebilmek için tmp kullanıyoduk. ileri attık ki diğer bağlı elemanlarını da gezek
            }

            sonuç++;// adım sayısı arttı.
        }

        return 0; //hiçbi şey olmazsa 0 dönder
    }

    public Kuyruk yeniyer(Yer yer) { //burda Yer tipinde parametre alan yer değişkeni tanımlandı
        Kuyruk yerler = new Kuyruk(8); //bi at en fazla 8 yere gittiği için 8 boyutunda Kuyruk nesnesi aldık yerlere
        yerler.ekle(new Yer(yer.X - 1, yer.Y + 2));//bu eklenenler x-1 y+2 gibi bu şekliyle ekle metoduna gidiyor. Orada if şartına girerse kuyruğa ekleniyor
        yerler.ekle(new Yer(yer.X - 1, yer.Y - 2));
        yerler.ekle(new Yer(yer.X + 1, yer.Y + 2));
        yerler.ekle(new Yer(yer.X + 1, yer.Y - 2));
        yerler.ekle(new Yer(yer.X - 2, yer.Y + 1));
        yerler.ekle(new Yer(yer.X - 2, yer.Y - 1));
        yerler.ekle(new Yer(yer.X + 2, yer.Y + 1));
        yerler.ekle(new Yer(yer.X + 2, yer.Y - 1));
        return yerler; //kuyruk tipinde yerler döndürülüyor
    }

    public static void main(String[] args) {
        Scanner k=new Scanner(System.in);
        System.out.println("Bulunulan konumun X değeri:");
        int x0=k.nextInt();
        if(x0>7||x0<0) {
            System.out.println("Girilen değer Santranç tahtası boyutunu aşmakta.");
            System.exit(0);
        }
        System.out.println("Bulunulan konumun Y değeri:");
        int y0=k.nextInt();
        if(y0>7||y0<0) {
            System.out.println("Girilen değer Santranç tahtası boyutunu aşmakta.");
            System.exit(0);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Hedef konumun X değeri:");
        int x1=k.nextInt();
        if(x1>7||x1<0) {
            System.out.println("Girilen değer Santranç tahtası boyutunu aşmakta.");
            System.exit(0);
        }
        System.out.println(" Hedef konumun Y değeri:");
        int y1=k.nextInt();
        if(y1>7||y1<0) {
            System.out.println("Girilen değer Santranç tahtası boyutunu aşmakta.");
            System.exit(0);
        }
        At at = new At(new Yer(x0,y0),new Yer(x1,y1));
        System.out.println("-----------------------------------------");
        System.out.println("Minimum Adım Sayısı:");
        System.out.println(at.hesapla());
    }
}
