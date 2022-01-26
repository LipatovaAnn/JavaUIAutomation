package org.uiautomation;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static boolean isTriangle(int a, int b, int c) {

        int ab=a+b;
        int bc=b+c;
        int ca=c+a;

        return (a < bc) && (b < ca) && (c < ab);
    }

    public static double getSquareOfTriangle(int a, int b, int c) throws NotTrinagleException {
        if(!isTriangle(a,b,c))
            throw new NotTrinagleException("Can't create triangle");

        double halfP = (a + b + c) / 2;
        double square = halfP * (halfP - a) * (halfP - b) * (halfP - c);

        return Math.sqrt(square);
    }
}