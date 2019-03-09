package ru.atom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

import org.slf4j.LoggerFactory;

public class BullsAndCows {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BullsAndCows.class);

    public static void main(String[] args) {

        log.info("Logger started");
        play("dictionary.txt");

    }

    public static boolean play(String filename) {

        String secret = getSecret(filename); //System.out.println(secret);
        System.out.println("Welcome to Bulls and Cows game!");
        System.out.println("I offered a " + secret.length() + "-letter word, your guess?");
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {

            String guess = scan.nextLine();
            if (secret.equals(guess)) {
                System.out.println("You Won!!!");
                return true;
            }

            int bulls = getBulls(secret, guess);
            int cows = getCows(secret, guess);
            System.out.println("Bulls: " + bulls);
            System.out.println("Cows: " + (cows - bulls));
        }

        System.out.println("You Lose: " + secret);
        return false;

    }

    public static int getBulls(String secret, String guess) {
        int bulls = 0;
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }
        return bulls;

    }

    public static int getCows(String secret, String guess) {
        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (secret.indexOf(guess.charAt(i)) > -1)
                cows++;
        }
        return cows;
    }

    public static String getSecret(String filename) {

        try {
            Scanner scan = new Scanner(new File(filename));
            int wordsCount = 0;
            while (scan.hasNextLine()) {
                scan.nextLine();
                wordsCount++;
            }

            Random rnd = new Random(System.currentTimeMillis());

            int wordNumber = rnd.nextInt(wordsCount);

            scan = new Scanner(new File(filename));

            for (int i = 0; i < wordNumber - 1; i++)
                scan.nextLine();
            return (scan.nextLine());

        } catch (FileNotFoundException ex) {

            System.err.println("File not found: " + ex.getMessage());
            return null;

        }

    }


}
