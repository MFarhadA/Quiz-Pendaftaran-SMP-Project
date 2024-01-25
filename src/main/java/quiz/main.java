package quiz;

import java.util.*;

public class main {

    public record Murid (String nama, String npm, int nilai, int gelombang) {}
    public record Soal (String paket, String judul) {}
    public record DataPengambilan(String npm, String paket) {}
    
    public static void main(String[] args) {
               
        Scanner input = new Scanner(System.in);
        
        Murid[] murid = data.MuridArray();
        Soal[] soal = data.SoalArray();

        // STACK SOAL
        Stack<Soal> rak = new Stack<>();
        for (Soal rakStack : soal) {
            rak.push(rakStack);
        }

        // PRIORITY QUEUE MURID
        PriorityQueue<Murid> barisan = new PriorityQueue<>(Comparator.comparingInt(Murid::gelombang));
        for (Murid muridQueue : murid) {
            barisan.add(muridQueue);
        }

        // LINKED LIST PEMINJAMAN & Hasil
        LinkedList<DataPengambilan> listAmbil = new LinkedList<>();
        LinkedList<Murid> listHasil = new LinkedList<>();
        
        boolean ulangMenuAwal = true;
        boolean ulangMenuTest = true;
        boolean ulangMenuHasil = true;
        
        while (ulangMenuAwal) {
            
            System.out.println(" ");
            data.MenuAwal();
            System.out.print("-> ");
            int pilihAwal = input.nextInt();
            input.nextLine();
            System.out.println(" ");
            
            switch (pilihAwal) {
                
                case 1: // 1. PILIH SOAL
                    ulangMenuAwal = false;
                    
                    data.PilihSoal(input, barisan, rak, listAmbil);

                    while (ulangMenuTest) {
                        
                        System.out.println(" ");
                        data.MenuTest();
                        System.out.print("-> ");
                        int pilihTest = input.nextInt();
                        input.nextLine();
                        System.out.println(" ");

                        switch (pilihTest) {
                            case 1: // 1. MULAI TEST
                                ulangMenuTest = false;
                                
                                data.Test(murid, listAmbil, listHasil);
                                
                                while (ulangMenuHasil) {
                                
                                    System.out.println(" ");
                                    data.MenuHasil();
                                    System.out.print("-> ");
                                    int pilihHasil = input.nextInt();
                                    System.out.println(" ");

                                    switch (pilihHasil) {
                                        case 1: // 1. DAFTAR KELULUSAN
                                            data.Lulus(listHasil);
                                            ulangMenuHasil = data.ulang();
                                            break;
                                        case 2: // 2. RANKING
                                            data.Ranking(listHasil);
                                            ulangMenuHasil = data.ulang();
                                            break;
                                        default: // JIKA SALAH INPUT
                                            System.out.println("Angka yang anda masukkan tidak sesuai.");
                                            ulangMenuHasil = data.ulang();
                                            break;
                                    }
                                }
                                break;
                                
                            case 2: // 2. PERATURAN TEST
                                data.Peraturan();
                                ulangMenuTest = data.ulang();
                                break;
                                
                            case 3: // 3. DAFTAR MURID
                                data.DaftarMuridTest(murid, listAmbil);
                                ulangMenuTest = data.ulang();
                                break;
                                
                            default: // JIKA SALAH INPUT
                                System.out.println("Angka yang anda masukkan tidak sesuai.");
                                ulangMenuTest = data.ulang();
                                break;
                        }
                    }
                    break;

                case 2: // 2. DAFTAR URUTAN MURID
                    data.DaftarMuridAwal(barisan);
                    ulangMenuAwal = data.ulang();
                    break;
                default: // JIKA SALAH INPUT
                    System.out.println("Angka yang anda masukkan tidak sesuai.");
                    ulangMenuAwal = data.ulang();
                    break;
            }
        }
    }
}