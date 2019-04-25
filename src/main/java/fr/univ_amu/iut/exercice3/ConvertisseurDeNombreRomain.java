package fr.univ_amu.iut.exercice3;

public class ConvertisseurDeNombreRomain {
    public static int enNombreArabe(String i) {

        int number = 0;

        for (int j = 1; j < i.length() + 1; ++j) {
            int current = poids(i.charAt(j - 1));
            int next = j >= i.length() ? 0 : poids(i.charAt(j));

            if (current == 1 && (next == 50 || next == 100 || next == 500 || next == 1000))
                throw new IllegalArgumentException("Cannot subtract I to C, L, D, or M");

            if (current == 10 && (next == 500 || next == 1000))
                throw new IllegalArgumentException("Cannot subtract X to D or M");

            if (current == 5 && next == 10)
                throw new IllegalArgumentException("Cannot subtract V to X");

            if (current >= next) number += current;
            if (next > current) {
                number += next - current;
                ++j;
            }
        }

        return number;
    }

    public static int poids(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException(String.format("Unrecognized number: %s", c));
        }
    }

    public static void main(String[] args) {
        System.out.println(enNombreArabe("III"));
    }
}
