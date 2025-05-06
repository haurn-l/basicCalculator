package formulhesaplama;

import java.util.*;

public class HesapMakinesi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Formülü girin (örn: abc + p - 2 * k + 4): ");
        String ifade = sc.nextLine().replace(" ", "");

        List<String> tumIfadeler = new ArrayList<>();
        String gecici = "";

        // Harfleri ve operatörleri ayırıyoruz
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

        // Harfleri ayıklıyoruz
        Set<Character> harfKumesi = new LinkedHashSet<>();
        for (String ifadeParcasi : tumIfadeler) {
            if (ifadeParcasi.matches("[a-zA-Z]+")) {
                for (char harf : ifadeParcasi.toCharArray()) {
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

        // Değişkenleri değerlere göre güncelle
        List<String> guncelIfade = new ArrayList<>();
        for (String parcacik : tumIfadeler) {
            if (parcacik.matches("[a-zA-Z]+")) {
                int sayiDegeri = 0;
                int basamak = parcacik.length() - 1;
                for (char harf : parcacik.toCharArray()) {
                    sayiDegeri += degerHaritasi.get(harf) * Math.pow(10, basamak);
                    basamak--;
                }
                guncelIfade.add(String.valueOf(sayiDegeri));
            } else {
                guncelIfade.add(parcacik);
            }
        }

        // İşlem önceliğini dikkate almadan işlemi sırayla yap
        double sonuc = Double.parseDouble(guncelIfade.get(0));

        for (int i = 1; i < guncelIfade.size(); i += 2) {
            String islem = guncelIfade.get(i);
            String sayiStr = guncelIfade.get(i + 1);

            double sayi = Double.parseDouble(sayiStr);

            switch (islem) {
                case "+":
                    sonuc += sayi;
                    break;
                case "-":
                    sonuc -= sayi;
                    break;
                case "*":
                    sonuc *= sayi;
                    break;
                case "/":
                    if (sayi == 0) {
                        System.out.println("Sıfıra bölme hatası!");
                        return;
                    }
                    sonuc /= sayi;
                    break;
                case "^":
                    sonuc = Math.pow(sonuc, sayi);
                    break;
                default:
                    System.out.println("Hatalı işlem: " + islem);
                    return;
            }
        }

        System.out.println("Sonuç: " + sonuc);
    }
}
