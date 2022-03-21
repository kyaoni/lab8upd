import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class SimpleFraction {
    private int numerator;
    private int denominator;


    public SimpleFraction(int num, int den) {
        this.numerator = num;
        this.denominator = den;
    }

    static void sum(SimpleFraction fraction1, SimpleFraction fraction2) {
        int n = fraction1.numerator * fraction2.denominator + fraction1.denominator * fraction2.numerator;
        int d = fraction1.denominator * fraction2.denominator;
        System.out.println("Сложение с двумя аргументами: " + n + "/" + d);
    }

    static void min(SimpleFraction fraction1, SimpleFraction fraction2) {
        int n = fraction1.numerator * fraction2.denominator - fraction1.denominator * fraction2.numerator;
        int d = fraction1.denominator * fraction2.denominator;
        System.out.println("Вычитание с двумя аргументами: " + n + "/" + d);
    }

    static void multiply(SimpleFraction fraction1, SimpleFraction fraction2) {
        int n = fraction1.numerator * fraction2.numerator;
        int d = fraction1.denominator * fraction2.denominator;
        System.out.println("Умножение с двумя аргументами: " + n + "/" + d);
    }

    static void divide(SimpleFraction fraction1, SimpleFraction fraction2) {
        int n = fraction1.numerator * fraction2.denominator;
        int d = fraction1.denominator * fraction2.numerator;
        System.out.println("Деление первым с двумя аргументами: " + n + "/" + d);
    }

    void sum(SimpleFraction fraction2) {
        int num = fraction2.numerator;
        int denum = fraction2.denominator;
        int x = this.numerator * denum + this.denominator * num;
        int y = this.denominator * denum;
        System.out.println("Сложение с одним аргументом: " + x + "/" + y);
    }

    void min(SimpleFraction fraction2) {
        int num = fraction2.numerator;
        int denum = fraction2.denominator;
        int x = this.numerator * denum - this.denominator * num;
        int y = this.denominator * denum;
        System.out.println("Вычитание с одним аргументом: " + x + "/" + y);
    }

    void multiply(SimpleFraction fraction2) {
        int num = fraction2.numerator;
        int denum = fraction2.denominator;
        int x = 0;
        int y = 0;
        x = this.numerator * num;
        y = this.denominator * denum;
        System.out.println("Умножение с одним аргументом: " + x + "/" + y);
    }

    void divide(SimpleFraction fraction2) {
        int num = fraction2.numerator;
        int denum = fraction2.denominator;
        int x = this.numerator * denum;
        int y = this.denominator * num;
        System.out.println("Деление с одним аргументом: " + x + "/" + y);
    }
}
class Calc {
    private static String operation;
    private static int num1;
    private static int num2;
    private static int den1;
    private static int den2;

    public static void main(String[] args) throws ExceptionForNull {
        String pattern2 = "^(-?\\d{1,10})/(-?\\d{1,10})( )[-+*:]( )(-?\\d{1,10})/(-?\\d{1,10})$";
        Pattern p = Pattern.compile(pattern2);
        Scanner data = new Scanner(System.in);
        System.out.println("Введите выражение с двумя простыми дробями (бесскобочное) в формате: 'num1/den1 (+,-,:,*) num2/den2'");
        String text = data.nextLine();
        data.close();
        Matcher m = p.matcher(text);
        if (m.matches() == true) {
            String[] parts = text.split("[/( )]+");
            num1 = Integer.parseInt(parts[0]);
            den1 = Integer.parseInt(parts[1]);
            num2 = Integer.parseInt(parts[3]);
            den2 = Integer.parseInt(parts[4]);
            operation = parts[2];
            if ((den1 == 0) | (den2 == 0)) throw new ExceptionForNull();
            SimpleFraction fraction1 = new SimpleFraction(num1, den1);
            SimpleFraction fraction2 = new SimpleFraction(num2, den2);
            if (operation.contains("+")) {
                SimpleFraction.sum(fraction1, fraction2);
                fraction1.sum(fraction2);
            }
            if (operation.contains("-")) {
                SimpleFraction.min(fraction1, fraction2);
                fraction1.min(fraction2);
            }
            if (operation.contains("*")) {
                SimpleFraction.multiply(fraction1, fraction2);
                fraction1.multiply(fraction2);
            }
            if (operation.contains(":")) {
                SimpleFraction.divide(fraction1, fraction2);
                fraction1.divide(fraction2);
            }
        } else {
            System.out.println("Введите выражение ещё раз.");
            System.exit(1);
        }
    }
}
class ExceptionForNull extends Exception {
    public ExceptionForNull() {
        System.out.println("Знаменатель одной или обеих дробей равен нулю, повторите ввод.");
        System.exit(1);
    }
}
