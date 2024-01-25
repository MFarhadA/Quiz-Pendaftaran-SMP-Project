package quiz;

import java.util.*;
import quiz.main.*;

public class data {
    
    public static Murid[] MuridArray() {
        Murid[] murid = new Murid[3];
        murid[0] = new Murid("Ajil Sepakbor", "3272002", 0, 1);
        murid[1] = new Murid("Haris Kupling", "3272003", 0, 3);
        murid[2] = new Murid("Iwang Kenalpot", "3272004", 0, 2);
        return murid;
    }
    
    public static Soal[] SoalArray() {
        Soal[] soal = new Soal[4];
        soal[0] = new Soal("A", "Matematika I");
        soal[1] = new Soal("B", "Matematika II");
        soal[2] = new Soal("C", "Matematika III");
        soal[3] = new Soal("D", "Matematika IV");
        return soal;
    }
    
    public static void MenuAwal() {
        System.out.println("===========================");
        System.out.println("|   PENDAFTARAN SMP 911    |");
        System.out.println("---------------------------");
        System.out.println("- pilih menu sesuai angka -");
        System.out.println("---------------------------");
        System.out.println("1. PILIH SOAL");
        System.out.println("2. DAFTAR URUTAN MURID");
        System.out.println("===========================");
    }
    
    public static void MenuTest() {
        System.out.println("===========================");
        System.out.println("|       TEST MASUK        |");
        System.out.println("---------------------------");
        System.out.println("- pilih menu sesuai angka -");
        System.out.println("---------------------------");
        System.out.println("1. MULAI TEST");
        System.out.println("2. PERATURAN TEST");
        System.out.println("3. DAFTAR MURID");
        System.out.println("===========================");
    }
    
    public static void MenuHasil() {
        System.out.println("===========================");
        System.out.println("|       PENGUMUMAN        |");
        System.out.println("---------------------------");
        System.out.println("- pilih menu sesuai angka -");
        System.out.println("---------------------------");
        System.out.println("1. DAFTAR KELULUSAN");
        System.out.println("2. RANKING");
        System.out.println("===========================");
    }
    
    public static void Peraturan() {
        System.out.println("==============================================");
        System.out.println("|               PERATURAN TEST               |");
        System.out.println("----------------------------------------------");
        System.out.println("1. Dilarang Menanyakan Jawaban Ke Peserta Lain");
        System.out.println("2. Dilarang Searching");
        System.out.println("3. Dilarang Meninggalkan Ruangan Tanpa Izin");
        System.out.println("==============================================");
    }
    
    public static void DaftarMuridAwal(PriorityQueue<Murid> barisan) {
        int i = 1;
        System.out.println("===========================");
        for (Murid urutanMurid : barisan) {
            System.out.println(i++ + ". Nama :" + urutanMurid.nama());
            System.out.println("   NPM  :" + urutanMurid.npm());
            System.out.println("---------------------------");
        }
        System.out.println("===========================");
    }
    
    public static void DaftarMuridTest(Murid[] murid, LinkedList<DataPengambilan> listAmbil) {
        System.out.println("================================");
        int i = 1;
        for (Murid listmurid : murid) {
            for (DataPengambilan listambil : listAmbil) {
                if (listambil.npm().equals(listmurid.npm())) {
                System.out.println(i++ + ". NAMA       : " + listmurid.nama());
                System.out.println("   NIK        : " + listambil.npm());
                System.out.println("   Paket Soal : " + listambil.paket());
                System.out.println("--------------------------------");
                }
            }
        }
        System.out.println("================================");
    }
    
    public static void PilihSoal(Scanner input, PriorityQueue<Murid> barisan, Stack<Soal> rak, LinkedList<DataPengambilan> listAmbil) {
        int i = 1;
        System.out.println("=========================================");
        while (!barisan.isEmpty()) {
            Murid barisMurid = barisan.poll();
            System.out.println("BARISAN KE- " + i++);
            System.out.println("Nama: " + barisMurid.nama() + ", NIM: " + barisMurid.npm());
            System.out.print("Pilih paket soal: ");
            for (Soal soalTersedia : rak) {
                System.out.print("| " + soalTersedia.paket() + " | ");
            }
            System.out.println(" ");
            System.out.print("-> ");
            String pilihsoal = input.nextLine().toUpperCase();
            System.out.println("-----------------------------------------");
            boolean cocok = false;

            for (Soal pengambilan : rak) {
                if (pengambilan.paket().equals(pilihsoal)) {
                    DataPengambilan datapengambilan = new DataPengambilan(barisMurid.npm(), pilihsoal);
                    listAmbil.add(datapengambilan);
                    rak.remove(pengambilan);
                    cocok = true;
                    break;
                }
            }
            if (!cocok){
                System.out.println("Paket Soal tidak ditemukan.");
                System.out.println("-----------------------------------------");
            }
        }
        System.out.println("=========================================");
    }

    public static void Test(Murid[] murid, LinkedList<DataPengambilan> listAmbil, LinkedList<Murid> listHasil) {
        Iterator<DataPengambilan> iterator = listAmbil.iterator();
        while (iterator.hasNext()) {
            DataPengambilan daftar = iterator.next();
            for (int i = 0; i < murid.length; i++) {
                Murid nilaiMurid = murid[i];
                if (daftar.npm().equals(nilaiMurid.npm())) {
                    int nilai = 0;
                    if (daftar.paket().equalsIgnoreCase("a")) {
                        nilai = paketA();
                    } else if (daftar.paket().equalsIgnoreCase("b")) {
                        nilai = paketB();
                    } else if (daftar.paket().equalsIgnoreCase("c")) {
                        nilai = paketC();
                    } else if (daftar.paket().equalsIgnoreCase("d")) {
                        nilai = paketD();
                    }
                    Murid updatedMurid = new Murid(nilaiMurid.nama(), nilaiMurid.npm(), nilai, nilaiMurid.gelombang());
                    System.out.println(" ");
                    System.out.println("---------------------------");
                    System.out.println("NAMA  : " + updatedMurid.nama());
                    System.out.println("NILAI : " + updatedMurid.nilai());
                    System.out.println("---------------------------");
                    System.out.println(" ");
                    listHasil.add(updatedMurid);
                    iterator.remove();
                    break;
                }
            }
        }
    }
    
    public static int paketA() {
        Scanner input = new Scanner(System.in);
        int totalNilai = 0;
        System.out.println("=======================================================================");
        System.out.println("|                              MATEMATIKA I                           |");
        System.out.println("=======================================================================");
        System.out.println("1. 50 x 8 - 25 x 8 - 32 = ");
        System.out.println("   a. 187");
        System.out.println("   b. 168");
        System.out.println("   c. 158");
        System.out.println("   d. 124");
        System.out.print("-> ");
        String soal1 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("2. Sebuah segitiga siku-siku memiliki sisi vertikal dengan panjang 5 cm");
        System.out.println("   dan sisi horizontal 12 cm. Berapakah sisi miring segitiga tersebut ?");
        System.out.println("   a. 13 cm");
        System.out.println("   b. 11 cm");
        System.out.println("   c. 10 cm");
        System.out.println("   d. 12 cm");
        System.out.print("-> ");
        String soal2 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("3. Jika harga satu buah pensil adalah Rp 500, berapa harga 10 pensil ?");
        System.out.println("   a. 50");
        System.out.println("   b. 500");
        System.out.println("   c. 5000");
        System.out.println("   d. 5500");
        System.out.print("-> ");
        String soal3 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("4. Berapa luas permukaan sebuah kubus jika panjang sisi");
        System.out.println("   kubusnya adalah 4 cm ?");
        System.out.println("   a. 64 cm²");
        System.out.println("   b. 96 cm²");
        System.out.println("   c. 48 cm²");
        System.out.println("   d. 16 cm²");
        System.out.print("-> ");
        String soal4 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("5. Jika 3/5 dari sebuah angka adalah 21, berapakah angka tersebut ?");
        System.out.println("   a. 25");
        System.out.println("   b. 14");
        System.out.println("   c. 30");
        System.out.println("   d. 35");
        System.out.print("-> ");
        String soal5 = input.nextLine();
        System.out.println("========================================================================");

        if (soal1.equalsIgnoreCase("b")){
            totalNilai += 20;
        }
        if (soal2.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal3.equalsIgnoreCase("c")){
            totalNilai += 20;
        }
        if (soal4.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal5.equalsIgnoreCase("d")){
            totalNilai += 20;
        }
        return totalNilai;
    }

    public static int paketB() {
        Scanner input = new Scanner(System.in);
        int totalNilai = 0;
        System.out.println("=======================================================================");
        System.out.println("|                            MATEMATIKA II                            |");
        System.out.println("=======================================================================");
        System.out.println("1. Sebuah buku memiliki 300 halaman. Jika setiap halaman memiliki");
        System.out.println("   25 baris dan setiap baris meemiliki 50 kata, berapa jumlah kata");
        System.out.println("   dalam buku tersebut ?");
        System.out.println("   a. 375.000");
        System.out.println("   b. 750.000");
        System.out.println("   c. 1.125.000");
        System.out.println("   d. 1.500.000");
        System.out.print("-> ");
        String soal1 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("2. Hitunglah hasil dari 57 + 20 x 8 : 2 x 25 = ");
        System.out.println("   a. 2.057");
        System.out.println("   b. 2.050");
        System.out.println("   c. 2.000");
        System.out.println("   d. 2.150");
        System.out.print("-> ");
        String soal2 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("3. Sebuah lingkaran memiliki jari-jari sepanjang 7 cm.");
        System.out.println("   Berapakah keliling lingkaran tersebut ? (Gunakan Phi = 22/7)");
        System.out.println("   a. 44 cm");
        System.out.println("   b. 14 cm");
        System.out.println("   c. 11 cm");
        System.out.println("   d. 21 cm");
        System.out.print("-> ");
        String soal3 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("4. Jika 20% dari sebuah angka adalah 30, berapakah angka tersebut ?");
        System.out.println("   a. 60");
        System.out.println("   b. 150");
        System.out.println("   c. 120");
        System.out.println("   d. 75");
        System.out.print("-> ");
        String soal4 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("5. Sebuah persegi panjang memiliki panjang 10 cm dan lebar 6 cm.");
        System.out.println("   hitunglah luas persegi panjang tersebut ?");
        System.out.println("   a. 36 cm");
        System.out.println("   b. 60 cm");
        System.out.println("   c. 46 cm");
        System.out.println("   d. 16 cm");
        System.out.print("-> ");
        String soal5 = input.nextLine();
        System.out.println("========================================================================");

        if (soal1.equalsIgnoreCase("c")){
            totalNilai += 20;
        }
        if (soal2.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal3.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal4.equalsIgnoreCase("c")){
            totalNilai += 20;
        }
        if (soal5.equalsIgnoreCase("b")){
            totalNilai += 20;
        }
        return totalNilai;
    }

    public static int paketC() {
        Scanner input = new Scanner(System.in);
        int totalNilai = 0;
        System.out.println("=======================================================================");
        System.out.println("|                            MATEMATIKA III                           |");
        System.out.println("=======================================================================");
        System.out.println("1. Jika sebuah kubus memiliki volume 125 cm(kubik).");
        System.out.println("   Berapakah panjang sisinya ?");
        System.out.println("   a. 5 cm");
        System.out.println("   b. 15 cm");
        System.out.println("   c. 25 cm");
        System.out.println("   d. 75 cm");
        System.out.print("-> ");
        String soal1 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("2. Hitunglah hasil dari (4-2)x(5+3)= ?");
        System.out.println("   a. 15");
        System.out.println("   b. 16");
        System.out.println("   c. 17");
        System.out.println("   d. 14");
        System.out.print("-> ");
        String soal2 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("3. Jika harga 5 buah pensil adalah Rp 2000, berapakah harga 8 buah pensil ?");
        System.out.println("   a. Rp 2500");
        System.out.println("   b. Rp 4000");
        System.out.println("   c. Rp 3200");
        System.out.println("   d. Rp 1600");
        System.out.print("-> ");
        String soal3 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("4. Sebuah bus berangkat dari kota A ke kota B dengan kecepatan 60 KM/Jam,");
        System.out.println("   kemudian tiba dalam waktu 3 jam. Berapakah jarak antara 2 kota tersebut ?");
        System.out.println("   kubusnya adalah 4 cm ?");
        System.out.println("   a. 20 KM");
        System.out.println("   b. 180 KM");
        System.out.println("   c. 120 KM");
        System.out.println("   d. 60 Km");
        System.out.print("-> ");
        String soal4 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("5. Jika harga sebuah barang setelah diskon 20% adalah Rp 400.000,");
        System.out.println("   berapakah harga sebelumnya ?");
        System.out.println("   a. Rp 500.000");
        System.out.println("   b. Rp 320.000");
        System.out.println("   c. Rp 480.000");
        System.out.println("   d. Rp 420.000");
        System.out.print("-> ");
        String soal5 = input.nextLine();
        System.out.println("========================================================================");

        if (soal1.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal2.equalsIgnoreCase("b")){
            totalNilai += 20;
        }
        if (soal3.equalsIgnoreCase("d")){
            totalNilai += 20;
        }
        if (soal4.equalsIgnoreCase("c")){
            totalNilai += 20;
        }
        if (soal5.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        return totalNilai;
    }
    
    public static int paketD() {
        Scanner input = new Scanner(System.in);
        int totalNilai = 0;
        System.out.println("=======================================================================");
        System.out.println("|                           MATEMATIKA IV                             |");
        System.out.println("=======================================================================");
        System.out.println("1. Rina memiliki 3 buah apel dan membeli 5 buah apel lagi, kemudian Rina");
        System.out.println("   memberikan setengah dari jumlah apel yang dimilikinya kepada adiknya.");
        System.out.println("   Berapakah jumlah apel yang dimiliki Rina sekarang");
        System.out.println("   a. 4");
        System.out.println("   b. 6");
        System.out.println("   c. 8");
        System.out.println("   d. 10");
        System.out.print("-> ");
        String soal1 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("2. Diketahui panjang sebuah persegi panjang adalah 12 cm dan lebarnya");
        System.out.println("   adalah X cm. Jika kelilingnya adalah 40 cm, berapakah nilai x?");
        System.out.println("   a. 6 cm");
        System.out.println("   b. 8 cm");
        System.out.println("   c. 10 cm");
        System.out.println("   d. 14 cm");
        System.out.print("-> ");
        String soal2 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("3. Suatu segitiga memiliki tinggi 15 cm dan luasnya adalah 45 cm.");
        System.out.println("   Berapakah panjang alas dari segitiga tersebut?");
        System.out.println("   a. 3 cm");
        System.out.println("   b. 6 cm");
        System.out.println("   c. 9 cm");
        System.out.println("   d. 12 cm");
        System.out.print("-> ");
        String soal3 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("4. Tono mempunyai uang sebanyak Rp 80.000, setelah membeli sebuah");
        System.out.println("   buku seharga Rp 35.000, berapakah sisa uang yang dimiliki Tono ?");
        System.out.println("   a. Rp 45.000");
        System.out.println("   b. Rp 50.000");
        System.out.println("   c. Rp 55.000");
        System.out.println("   d. Rp 65.000");
        System.out.print("-> ");
        String soal4 = input.nextLine();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("5. Seorang petani membeli beberapa kantong pupuk seharga Rp 5000 /kantong");
        System.out.println("   dan beberapa kantong pupuk lainnya seharga Rp 8000 /kantong, total biaya");
        System.out.println("   pupuk yang dibelinya adalah Rp 96.000. Jika dia membeli tital 14 kantong");
        System.out.println("   pupuk, berapa banyak kantong pupuk yang dibelinya dengan harga Rp 5000");
        System.out.println("   /kantong ?");
        System.out.println("   a. 6 kantong");
        System.out.println("   b. 8 kantong");
        System.out.println("   c. 10 kantong");
        System.out.println("   d. 12 kantong");
        System.out.print("-> ");
        String soal5 = input.nextLine();
        System.out.println("========================================================================");

        if (soal1.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal2.equalsIgnoreCase("b")){
            totalNilai += 20;
        }
        if (soal3.equalsIgnoreCase("b")){
            totalNilai += 20;
        }
        if (soal4.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        if (soal5.equalsIgnoreCase("a")){
            totalNilai += 20;
        }
        return totalNilai;
    }
    
    public static void Lulus (LinkedList<Murid> listHasil) {
        int i = 1;
        System.out.println("===========================");
        for (Murid lulus : listHasil) {
            System.out.println(i++ + ". Nama   : " + lulus.nama());
                System.out.println("   NPM    : " + lulus.npm());
            if (lulus.nilai() > 75) {
                System.out.println("   Status : LULUS");
            } else {
                System.out.println("   Status : TIDAK LULUS");
            }
            System.out.println("---------------------------");
        }
        System.out.println("===========================");
    }
           
    public static void Ranking (LinkedList<Murid> listHasil) {
        Collections.sort(listHasil, Comparator.comparingInt(Murid::nilai).reversed());
        int i = 1;
        System.out.println("===========================");
        for (Murid rank : listHasil) {
            System.out.println(i++ + ". Nilai : " + rank.nilai());
            System.out.println("   Nama  : " + rank.nama());
            System.out.println("   NPM   : " + rank.npm());
            System.out.println("---------------------------");
        }
        System.out.println("===========================");
    }
    
    public static boolean ulang() {
        Scanner input = new Scanner(System.in);
        System.out.println("Kembali ? (Y/T)");
        System.out.print("-> ");
        String jawab = input.next().toUpperCase();
        input.nextLine();
        return jawab.equals("Y");
    }
}