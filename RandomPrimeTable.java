package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int numberOfPrimes;
        Scanner scanner = new Scanner(System.in);
        numberOfPrimes = scanner.nextInt();
        printTable(numberOfPrimes);
    }

    private static void printTable(int numberOfPrimes) {
        ArrayList<BigInteger> primesList = new ArrayList<>();
        primesList.add(BigInteger.ONE);
        for (long i = 0; i < numberOfPrimes; ++i) {
            BigInteger random;
            do {
                random = generateRandom().abs();
            } while (!isPrime(random));
            primesList.add(random);
        }
        for (BigInteger aPrimesList:primesList) {
            for (BigInteger bPrimesList : primesList) {
                System.out.print(" " + String.format("%10d", aPrimesList.multiply(bPrimesList)) + " |");
            }
            System.out.println();
        }
    }

    private static BigInteger generateRandom() {
        BigInteger result = new BigInteger(10, new Random());
        BigInteger minus1=BigInteger.valueOf(-1);
        if (result.equals(BigInteger.ZERO) || result.equals(BigInteger.ONE) || result.equals(minus1)) {
            return BigInteger.TEN;
        }
        return result;
    }

    private static boolean isPrime(BigInteger number) {
        BigInteger zero = BigInteger.ZERO;
        BigInteger one = BigInteger.ONE;
        BigInteger two = BigInteger.valueOf(2);
        BigInteger numSubOne = number.subtract(one);
        int pow = 0;
        BigInteger oddNum;
        BigInteger exponent = numSubOne;

        while (exponent.mod(two).equals(zero)) {
            exponent = exponent.shiftRight(1);
            pow += 1;
        }
        oddNum = exponent.abs();
        BigInteger result = BigInteger.TEN;
        try {
            result = two.modPow(oddNum, number);
        } catch (ArithmeticException e) {
            System.out.println(oddNum + " " + number);
        }
        if (result.compareTo(one) == 0) {
            return true;
        }
        for (; pow > 0; pow -= 1) {
            if (result.compareTo(numSubOne) == 0) {
                return true;
            }
            result = result.modPow(two, number);
        }
        return result.compareTo(numSubOne) == 0;
    }
}
