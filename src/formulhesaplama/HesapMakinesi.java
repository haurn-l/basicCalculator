package formulhesaplama;

import java.util.*;

public class HesapMakinesi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Formülü girin (örn: abc + p - 2 * k + 4): ");
        String ifade = sc.nextLine().replace(" ", "");

        List<String> tumIfadeler = new ArrayList<>();
        String gecici = "";

        for (int i = 0; i < ifade.length(); i++) {
            char karakter = ifade.charAt(i);

            // Sayı, harf veya negatif başlangıç
            if (Character.isLetterOrDigit(karakter) ||
                    (karakter == '-' && (i == 0 || "+-*/".contains(String.valueOf(ifade.charAt(i - 1)))))) {
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

        // Harfleri toplayalım
        Set<Character> harfKumesi = new LinkedHashSet<>();
        for (String parca : tumIfadeler) {
            String temiz = parca.replace("-", ""); // negatifse baştaki -'yi temizle
            if (temiz.matches("[a-zA-Z]+")) {
                for (char harf : temiz.toCharArray()) {
                    harfKumesi.add(harf);
                }
            }
        }

        Map<Character, Integer> degerHaritasi = new HashMap<>();
        for (char harf : harfKumesi) {
            System.out.print(harf + " için değer girin: ");
            int deger = sc.nextInt();
            degerHaritasi.put(harf, deger);
        }

        // Değişkenleri değerlere çevir
        List<String> guncelIfade = new ArrayList<>();
        for (String parca : tumIfadeler) {
            boolean negatif = false;
            String temizParca = parca;

            if (parca.startsWith("-") && parca.length() > 1) {
                negatif = true;
                temizParca = parca.substring(1);
            }

            if (temizParca.matches("[a-zA-Z]+")) {
                int deger = 0;
                int basamak = temizParca.length() - 1;
                for (char harf : temizParca.toCharArray()) {
                    deger += degerHaritasi.get(harf) * Math.pow(10, basamak);
                    basamak--;
                }
                if (negatif) deger *= -1;
                guncelIfade.add(String.valueOf(deger));
            } else {
                guncelIfade.add(parca);
            }
        }

        // Hesapla (önceliksiz)
        double sonuc = Double.parseDouble(guncelIfade.get(0));
        for (int i = 1; i < guncelIfade.size(); i += 2) {
            String islem = guncelIfade.get(i);
            double sayi = Double.parseDouble(guncelIfade.get(i + 1));

            switch (islem) {
                case "+": sonuc += sayi; break;
                case "-": sonuc -= sayi; break;
                case "*": sonuc *= sayi; break;
                case "/": sonuc /= sayi; break;
                default:
                    System.out.println("Hatalı işlem: " + islem);
                    return;
            }
        }

        System.out.println("Sonuç (önceliksiz hesaplama): " + sonuc);
    }
}
