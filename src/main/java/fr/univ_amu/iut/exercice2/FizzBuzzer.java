package fr.univ_amu.iut.exercice2;

public class FizzBuzzer {

    public String computeString(int i) {
        // J'espère que ce code est agréable à lire
        return i % 3 == 0 && i % 5 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : Integer.toString(i);
    }

    public String[] computeList(int i) {
        String[] strings = new String[i];

        for (int j = 0; j < i; j++) {
            strings[j] = computeString(j + 1);
        }

        return strings;
    }
}
