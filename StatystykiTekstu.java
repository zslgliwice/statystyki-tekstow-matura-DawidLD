import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatystykiTekstu{

    public static void main(String[] args) {
        try {
            String[] tekst = wczytajTekst("tekst.txt");
            rozwiazZadania(tekst);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku tekst.txt.");
        }
    }

    public static String[] wczytajTekst(String nazwaPliku) throws FileNotFoundException {
        StringBuilder tekst = new StringBuilder();
        File plik = new File(nazwaPliku);
        Scanner scanner = new Scanner(plik);
        while (scanner.hasNextLine()) {
            tekst.append(scanner.nextLine()).append(" ");
        }
        scanner.close();
        return tekst.toString().trim().split(" ");
    }

    public static void rozwiazZadania(String[] tekst) {
        zadanieA(tekst);
        zadanieB(tekst);
        zadanieC(tekst);
    }

    public static void zadanieA(String[] tekst) {
        int liczbaSlowZPowtorzeniami = 0;
        for (String slowo : tekst) {
            if (czySlowoMaPowtorzenia(slowo)) {
                liczbaSlowZPowtorzeniami++;
            }
        }
        System.out.println("Slowa z dwoma kolejnymi literami: " + liczbaSlowZPowtorzeniami);
    }

    public static boolean czySlowoMaPowtorzenia(String slowo) {
        for (int i = 0; i < slowo.length() - 1; i++) {
            if (slowo.charAt(i) == slowo.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void zadanieB(String[] tekst) {
        Map<Character, Integer> statystykaLiter = new HashMap<>();
        int liczbaLiter = 0;

        for (String slowo : tekst) {
            for (char litera : slowo.toCharArray()) {
                statystykaLiter.put(litera, statystykaLiter.getOrDefault(litera, 0) + 1);
                liczbaLiter++;
            }
        }

        System.out.println("");
        for (Map.Entry<Character, Integer> entry : statystykaLiter.entrySet()) {
            double procent = (double) entry.getValue() / liczbaLiter * 100;
            System.out.printf("%c: %d (%.2f%%)\n", entry.getKey(), entry.getValue(), procent);
        }
    }

    public static void zadanieC(String[] tekst) {
        String samogloski = "AEIOUY";
        int najdluzszyCiag = 0;
        int liczbaSlowZNajdluzszym = 0;
        String pierwszeSlowoZNajdluzszym = "";

        for (String slowo : tekst) {
            int aktualnyCiag = 0;
            for (char litera : slowo.toCharArray()) {
                if (!samogloski.contains(String.valueOf(litera))) {
                    aktualnyCiag++;
                    if (aktualnyCiag > najdluzszyCiag) {
                        najdluzszyCiag = aktualnyCiag;
                        liczbaSlowZNajdluzszym = 1;
                        pierwszeSlowoZNajdluzszym = slowo;
                    } else if (aktualnyCiag == najdluzszyCiag) {
                        liczbaSlowZNajdluzszym++;
                    }
                } else {
                    aktualnyCiag = 0;
                }
            }
        }

        System.out.println("");
        System.out.println("Najdluzszy ciag spolglosek: " + najdluzszyCiag);
        System.out.println("Znalezionych slow: " + liczbaSlowZNajdluzszym);
        System.out.println("Pierwsze z nich: " + pierwszeSlowoZNajdluzszym);
    }
}