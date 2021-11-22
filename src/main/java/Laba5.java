import java.io.*;

public class Laba5 {


    public static void func() {
        func();
    }

    public static void main(String... args) {
        try { // 1.ArrayIndexOutOfBoundsException
            int a[] = new int[2];
            a[123] = 10;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }

        try { // 2.FileNotFoundException
            File f = new File("lol.kek");
            FileReader fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }

        try { // 3.NullPointerException
            File f = null;
            f.canExecute();
        } catch (NullPointerException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }

        try { // 4.IOException
            BufferedWriter str = new BufferedWriter(new FileWriter("42.txt"));
            str.close();
            str.append("app");
        } catch (IOException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }
        try { // 5.ClassCastException
            Object str = "42";
            StringBuilder bld = (StringBuilder) str;
        } catch (ClassCastException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }
        try { // 6.ArithmeticException
            int a = 1 / 0;
        } catch (ArithmeticException ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }

        try { // 1.OutOfMemoryError
            int i = 10000000;
            while (true) {
                long[] a = new long[i];
                i *= 10;
            }
        } catch (OutOfMemoryError ex) {
            System.err.println("Поймана ошибка:");
            ex.printStackTrace();
        }
        try { // 2.StackOverflowError
            func();
        } catch (StackOverflowError ex) {
            System.err.println("Поймана ошибка: " + ex);

        }
    }
}
