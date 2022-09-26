package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] cinema = new char[rows][seats];

        populateArray(cinema, 'S');

        printMenu();
        int choice = scanner.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1 -> printCinema(cinema);
                case 2 -> buyTicket(cinema);
                case 3 -> printStatistics(cinema);
                default -> System.out.println("Invalid choice");
            }
            printMenu();
            choice = scanner.nextInt();
        }
    }

    public static void printCinema(char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i <= cinema.length; i++) {
            System.out.print(i);
            for (char j : cinema[i - 1]) {
                System.out.print( " " + j);
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] cinema) {
        final Scanner scanner = new Scanner(System.in);
        int totalSeats = cinema.length * cinema[0].length;
        int price;

        do {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            System.out.println();

            if (rowNumber > cinema.length || seatNumber > cinema[0].length ||
                    rowNumber < 1 || seatNumber < 1) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                if (totalSeats <= 60) {
                    price = 10;
                } else {
                    price = rowNumber <= cinema.length / 2 ? 10 : 8;
                }
                System.out.println("Ticket price: $" + price);
                System.out.println();
                break;
            }
        } while (true);
    }

    public static void printStatistics(char[][] cinema) {
        int totalSeats = cinema.length * cinema[0].length;
        int totalPurchased = 0;
        int currentIncome = 0;
        int totalIncome;
        int price;

        for (char[] row : cinema) {
            for (char seat : row) {
                if (seat == 'B') {
                    totalPurchased++;
                }
            }
        }

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (cinema.length / 2) * cinema[0].length * 10 + (cinema.length - cinema.length / 2) * cinema[0].length * 8;
        }

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (cinema[i][j] == 'B') {
                    if (totalSeats <= 60) {
                        price = 10;
                    } else {
                        price = i < cinema.length / 2 ? 10 : 8;
                    }
                    currentIncome += price;
                }
            }
        }

        System.out.println("Number of purchased tickets: " + totalPurchased);
        System.out.printf("Percentage: %.2f%%%n", (double) totalPurchased / totalSeats * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void populateArray(char[][] arr, char c) {
        for (char[] chars : arr) {
            Arrays.fill(chars, c);
        }
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}
