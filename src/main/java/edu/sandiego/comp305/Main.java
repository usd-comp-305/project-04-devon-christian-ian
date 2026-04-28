package edu.sandiego.comp305;

/**
 * Main class for the application.
 */
public final class Main {
    /**
     * Private constructor to prevent instantiation.
     */
    private Main() {
    }

    /**
     * Runs the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}