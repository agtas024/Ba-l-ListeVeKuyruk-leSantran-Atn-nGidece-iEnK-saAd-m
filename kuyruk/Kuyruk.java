package kuyruk;

class Yer {

    int X;
    int Y;

    public Yer(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}

public class Kuyruk  {

    private int boyut;
    private Yer[] kuyrukDizi;
    private int bas;
    private int son;
    private int elemanSayisi;

    public Kuyruk(int boyut) {
        this.boyut = boyut;
        kuyrukDizi = new Yer[boyut];
        bas = 0;
        son = -1;
        elemanSayisi = 0;
    }

    public void ekle(Yer yeni) {
        if (son == boyut - 1) {
            son = -1;
        }
        else if (yeni.X >= 0 && yeni.Y >= 0 && yeni.Y < 8 && yeni.Y < 8) { //8x8 matrisi aşmasın diye if şartı
            kuyrukDizi[++son] = yeni;
            elemanSayisi++;
        }

    }

    public Yer cikar() {
        Yer temp = kuyrukDizi[bas++];
        if (bas == boyut) {
            bas = 0;
        }
        elemanSayisi--;
        return temp;
    }

    public boolean bosMu() {
        return (elemanSayisi == 0);
    }

}

class liste {

    Kuyruk içerik;
    liste ileri;

    public liste(Kuyruk içerik) {
        this.içerik = içerik;
        ileri = null;
    }
}

class bağlı_liste {

    int bağlı_esayısı;
    liste bas;
    liste son;

    public bağlı_liste() {

        bağlı_esayısı = 0;
        bas = null;
        son = null;

    }

    void bağlı_listeye_ekle(liste yeni) {
        bağlı_esayısı++; //her eleman eklemede artırıyoruz ki bağlı liste eleman sayısı bilinsin
        if (bas == null) {
            bas = yeni;
            son = yeni;
        } else {
            son.ileri = yeni;
            son = son.ileri;
        }
    }
}
