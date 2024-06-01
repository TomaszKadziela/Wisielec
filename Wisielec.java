import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Wisielec {
    private static ArrayList<String> listaLatwe = new ArrayList<>();
    private static ArrayList<String> listaSrednie = new ArrayList<>();
    private static ArrayList<String> listaTrudne = new ArrayList<>();
    private static HashMap<String, Integer> statystyki = new HashMap<>();
    private static int proby;
    private static int wygrane;
    private static int przegrane;

    public static void main(String[] args) {
        inicjalizujListySlów();
        inicjalizujStatystyki();
        Scanner scanner = new Scanner(System.in);
        boolean zagracPonownie = true;

        while (zagracPonownie) {
            System.out.println("Wybierz poziom trudności: 1. Łatwy 2. Średni 3. Trudny");
            int trudnosc = scanner.nextInt();
            scanner.nextLine(); // wczytaj nową linię
            String slowo = losoweSlowo(trudnosc);
            graj(slowo);
            System.out.println("Czy chcesz zagrać ponownie? (tak/nie)");
            zagracPonownie = scanner.nextLine().equalsIgnoreCase("tak");
        }

        wyswietlStatystyki();
    }

    private static void inicjalizujListySlów() {
        listaLatwe.add("mama");
        listaLatwe.add("banan");
        listaLatwe.add("kokos");
        listaLatwe.add("kiwi");
        listaLatwe.add("wanna");

        listaSrednie.add("jagoda");
        listaSrednie.add("podróż");
        listaSrednie.add("winogrono");
        listaSrednie.add("felga");
        listaSrednie.add("pomarańcza");

        listaTrudne.add("czereśnia");
        listaTrudne.add("truskawka");
        listaTrudne.add("borówka");
        listaTrudne.add("trębacz");
        listaTrudne.add("szlachta");
    }

    private static void inicjalizujStatystyki() {
        statystyki.put("Wygrane", 0);
        statystyki.put("Przegrane", 0);
        statystyki.put("Proby", 0);
    }

    private static String losoweSlowo(int trudnosc) {
        Random random = new Random();
        switch (trudnosc) {
            case 1:
                return listaLatwe.get(random.nextInt(listaLatwe.size()));
            case 2:
                return listaSrednie.get(random.nextInt(listaSrednie.size()));
            case 3:
                return listaTrudne.get(random.nextInt(listaTrudne.size()));
            default:
                return listaLatwe.get(random.nextInt(listaLatwe.size()));
        }
    }

    private static void graj(String slowo) {
        Scanner scanner = new Scanner(System.in);
        char[] zgadywaneSlowo = new char[slowo.length()];
        for (int i = 0; i < zgadywaneSlowo.length; i++) {
            zgadywaneSlowo[i] = '_';
        }

        int niepoprawneZgadniecia = 0;
        boolean slowoZgadniete = false;
        HashSet<Character> zgadywaneLitery = new HashSet<>();

        while (niepoprawneZgadniecia < 6 && !slowoZgadniete) {
            System.out.print("Zgadywane słowo: ");
            System.out.println(zgadywaneSlowo);
            System.out.println("Wpisz literę:");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println("Nieprawidłowy format. Wpisz pojedynczą literę.");
                continue;
            }

            char litera = input.charAt(0);

            if (zgadywaneLitery.contains(litera)) {
                System.out.println("Już zgadywałeś tę literę. Spróbuj inną.");
                continue;
            }

            zgadywaneLitery.add(litera);

            boolean poprawneZgadniecie = false;
            for (int i = 0; i < slowo.length(); i++) {
                if (slowo.charAt(i) == litera) {
                    zgadywaneSlowo[i] = litera;
                    poprawneZgadniecie = true;
                }
            }

            if (!poprawneZgadniecie) {
                niepoprawneZgadniecia++;
            }

            slowoZgadniete = true;
            for (int i = 0; i < zgadywaneSlowo.length; i++) {
                if (zgadywaneSlowo[i] == '_') {
                    slowoZgadniete = false;
                    break;
                }
            }
        }

        if (slowoZgadniete) {
            System.out.println("Zgadłeś słowo! Słowo to " + slowo);
            wygrane++;
        } else {
            System.out.println("Przegrałeś! Słowo to " + slowo);
            przegrane++;
        }

        proby++;
        statystyki.put("Wygrane", wygrane);
        statystyki.put("Przegrane", przegrane);
        statystyki.put("Proby", proby);
    }

    private static void wyswietlStatystyki() {
        System.out.println("Statystyki:");
        System.out.println("Wygrane: " + statystyki.get("Wygrane"));
        System.out.println("Przegrane: " + statystyki.get("Przegrane"));
        System.out.println("Całkowita liczba prób: " + statystyki.get("Proby"));
    }
}
