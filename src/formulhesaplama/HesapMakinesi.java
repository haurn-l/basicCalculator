package formulhesaplama;

import java.util.*;

public class HesapMakinesi {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String ifade = ifadeAl();
        List<String> tumIfadeler = ifadeyiAyir(ifade);
        Set<Character> harfKumesi = harfleriAyikla(tumIfadeler);
        Map<Character, Integer> degerHaritasi = degerleriAl(harfKumesi);
        List<String> guncelIfade = degerleriGuncelle(tumIfadeler, degerHaritasi);
        double sonuc = hesapla(guncelIfade);
        System.out.println("\nSonuç: " + sonuc);
    }

    private static String ifadeAl() {
        System.out.print("Formülü girin (örn: abc + p - 2 * k + 4): ");
        return sc.nextLine().replace(" ", "");
    }

    private static List<String> ifadeyiAyir(String ifade) {
        List<String> tumIfadeler = new ArrayList<>();
        String gecici = "";

        for (int i = 0; i < ifade.length(); i++) {
            char karakter = ifade.charAt(i);
            if (Character.isLetterOrDigit(karakter) || (karakter == '-' && (i == 0 || ifade.charAt(i-1) == '*' || ifade.charAt(i-1) == '/' || ifade.charAt(i-1) == '+' || ifade.charAt(i-1) == '-'))) {
                gecici += karakter;
            } else {
                if (!gecici.equals("")) {
                    tumIfadeler.add(gecici);
                    gecici = "";
                }
                tumIfadeler.add(String.valueOf(karakter));
            }
        }
        if (!gecici.equals("")) {
            tumIfadeler.add(gecici);
        }
        return tumIfadeler;
    }

    private static Set<Character> harfleriAyikla(List<String> tumIfadeler) {
        Set<Character> harfKumesi = new LinkedHashSet<>();
        for (String ifadeParcasi : tumIfadeler) {
            if (ifadeParcasi.matches("[a-zA-Z]+") || ifadeParcasi.matches("-[a-zA-Z]+")) {
                String temizParcacik = ifadeParcasi.replace("-", "");
                for (char harf : temizParcacik.toCharArray()) {
                    harfKumesi.add(harf);
                }
            }
        }
        return harfKumesi;
    }

    private static Map<Character, Integer> degerleriAl(Set<Character> harfKumesi) {
        Map<Character, Integer> degerHaritasi = new HashMap<>();
        for (char harf : harfKumesi) {
            System.out.print(harf + " için değer girin: ");
            int deger = sc.nextInt();
            degerHaritasi.put(harf, deger);
        }
        return degerHaritasi;
    }

    private static List<String> degerleriGuncelle(List<String> tumIfadeler, Map<Character, Integer> degerHaritasi) {
        List<String> guncelIfade = new ArrayList<>();
        for (String parcacik : tumIfadeler) {
            if (parcacik.matches("[a-zA-Z]+") || parcacik.matches("-[a-zA-Z]+")) {
                boolean negatif = parcacik.startsWith("-");
                String temizParcacik = parcacik.replace("-", "");
                int sayiDegeri = 0;
                int basamak = temizParcacik.length() - 1;
                for (char harf : temizParcacik.toCharArray()) {
                    sayiDegeri += degerHaritasi.get(harf) * Math.pow(10, basamak);
                    basamak--;
                }
                if (negatif) {
                    sayiDegeri = -sayiDegeri;
                }
                guncelIfade.add(String.valueOf(sayiDegeri));
            } else {
                guncelIfade.add(parcacik);
            }
        }
        return guncelIfade;
    }

    private static double hesapla(List<String> guncelIfade) {
        double sonuc = Double.parseDouble(guncelIfade.get(0));
        System.out.println("\nİşlem adımları:");
        System.out.println("Başlangıç: " + sonuc);

        for (int i = 1; i < guncelIfade.size(); i += 2) {
            String islem = guncelIfade.get(i);
            String sayiStr = guncelIfade.get(i + 1);
            double sayi = Double.parseDouble(sayiStr);
            double oncekiSonuc = sonuc;

            sonuc = islemYap(sonuc, islem, sayi);
            System.out.println(oncekiSonuc + " " + islem + " " + sayi + " = " + sonuc);
        }
        return sonuc;
    }

    private static double islemYap(double sonuc, String islem, double sayi) {
        switch (islem) {
            case "+":
                return sonuc + sayi;
            case "-":
                return sonuc - sayi;
            case "*":
                return sonuc * sayi;
            case "/":
                if (sayi == 0) {
                    throw new ArithmeticException("Sıfıra bölme hatası!");
                }
                return sonuc / sayi;
            case "^":
                return Math.pow(sonuc, sayi);
            default:
                throw new IllegalArgumentException("Hatalı işlem: " + islem);
        }
    }
}
