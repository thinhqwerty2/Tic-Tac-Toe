package tictactoe;


import java.util.Scanner;

public class Main {

    //    public static boolean isNumeric(String value) {
//        try {
//            Integer.parseInt(value);
//            return true;
//        } catch(Exception e) {
//            System.out.println("You should enter numbers!");
//        }
//        return false;
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = "         ".toCharArray();
        printState(arr);
        int sumX, sumO;
        sumX = sumO = 0;
        int nowChar = 0;
        char[] c = new char[]{'X', 'O'};
        while (true) {
            try {
                int y = sc.nextInt();
                int x = sc.nextInt();
                int checkError = isValidMove(x, y, arr);
                switch (checkError) {
                    case 1 -> System.out.println("Coordinates should be from 1 to 3!");
                    case 2 -> System.out.println("This cell is occupied! Choose another one!");
                }
                if (checkError != 0) {
                    continue;
                }
                arr[x - 1 + 3 * y - 3] = c[nowChar];
                if (c[nowChar] == 'X') {
                    sumX++;
                }
                if (c[nowChar] == 'O') {
                    sumO++;
                }
                nowChar = (nowChar == 0) ? 1 : 0;
                printState(arr);
                if (isEndGame(sumX, sumO, arr)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }

        }


//
    }

    public static boolean isEndGame(int sumX, int sumO, char[] chars) {
        if (isXWin(chars)) {
            System.out.println("X wins");
            return true;
        }
        if (isOWin(chars)) {
            System.out.println("O wins");
            return true;
        }
        if (isDraw(sumX, sumO)) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    public static int isValidMove(int x, int y, char[] chars) {
        if (x < 1 || x > 3 || y < 1 || y > 3) return 1;
        if (chars[x - 1 + 3 * y - 3] != ' ') return 2;
        return 0;
    }

    public static void printState(char[] input) {
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.print("| ");
            System.out.print(input[i] + " ");
            if (i % 3 == 2) System.out.println("|");
        }
        System.out.println("---------");
    }

//    public static boolean isImpossible(int sumX, int sumO) {
//        return Math.abs(sumX - sumO) > 1;
//    }

    public static boolean isDraw(int sumX, int sumO) {
        return sumX + sumO == 9;
    }

//    public static boolean isFinished(int sumX, int sum0) {
//        return sumX + sum0 < 9;
//
//    }

    public static boolean isXWin(char[] chars) {
        if (checkDiag('X', chars)) return true;
        if (checkCol('X', chars)) return true;
        return checkRow('X', chars);
    }

    public static boolean isOWin(char[] chars) {
        if (checkDiag('O', chars)) return true;
        if (checkCol('O', chars)) return true;
        return checkRow('O', chars);
    }

    public static boolean checkDiag(char c, char[] chars) {
        if (chars[4] != c) return false;
        if (chars[0] == chars[4] && chars[0] == chars[8]) return true;
        return chars[2] == chars[4] && chars[2] == chars[6] && chars[0] == c;
    }

    public static boolean checkRow(char c, char[] chars) {
        for (int i = 0; i < 9; i += 3) {
            if (chars[i + 1] == chars[i] && chars[i + 2] == chars[i] && chars[i] == c) return true;
        }
        return false;
    }

    public static boolean checkCol(char c, char[] chars) {
        for (int i = 0; i < 3; i++) {
            if (chars[i + 3] == chars[i] && chars[i + 6] == chars[i] && chars[i] == c) return true;
        }
        return false;
    }


}
