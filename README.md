# Formül Hesaplayıcı (Java)

Bu proje, kullanıcıdan alınan bir matematiksel formülü değerlendirerek adım adım sonucu hesaplayan bir Java uygulamasıdır.

## ✨ Özellikler

- Formülde kullanılan değişkenleri (harfleri) otomatik olarak algılar.
- Kullanıcıdan her değişken için değer alır.
- Harfli sayıları (örneğin `abc`) 100'lük basamak sistemine göre değerlendirir:  
  `abc = a * 100 + b * 10 + c`
- Toplama, çıkarma, çarpma, bölme ve üs alma işlemlerini destekler: `+`, `-`, `*`, `/`, `^`
- Tüm işlemleri adım adım ekrana yazdırır.
- Sıfıra bölme ve geçersiz operatör gibi durumlara karşı kontroller içerir.

## 🚀 Nasıl Çalıştırılır?

1. Java kurulu olduğundan emin olun.
2. Projeyi bir Java IDE'sinde veya terminalde açın.
3. `HesapMakinesi.java` dosyasını derleyip çalıştırın:

```bash
javac HesapMakinesi.java
java formulhesaplama.HesapMakinesi
