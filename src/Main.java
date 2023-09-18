import java.util.Scanner;
import java.lang.Exception;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение только арабскими или только римскими числами от 1 до 10 включительно");
        String input = scanner.nextLine();

        System.out.println("Результат: " + calc(input));
    }

    public static String calc(String input) throws Exception{

        char operation = ' '; //операция для нашего выражения
        int number1 = 0; //первое число
        int number2 = 0; //второе число
        int calcResult = 0; //результат расчетов после преобразования к int
        String calculation = ""; //строка для результата, тк могут быть римские цифры
        boolean itsRomanNumbers = false; //для определения арабских или римских цифр

        //получаем операцию
        if (input.indexOf("+") != -1) {
            operation = '+';
        }
        if (input.indexOf("-") != -1) {
            operation = '-';
        }
        if (input.indexOf("*") != -1) {
            operation = '*';
        }
        if (input.indexOf("/") != -1) {
            operation = '/';
        }

        if (operation == ' ') {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        //вычисляем два числа
        String[] parts = input.split("[+-/*]");

        if (parts.length != 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //отформатируем строку: уберем лишние пробелы и преобразуем к верхнему регистру для римских цифр
        parts[0] = parts[0].trim().toUpperCase();
        parts[1] = parts[1].trim().toUpperCase();

        //проверяем введены арабские или римские цифры
        try {
            number1 = Integer.parseInt(parts[0]);
            number2 = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            itsRomanNumbers = true;
        }

        //преобразуем римские цифры в int
        if (itsRomanNumbers) {
            number1 = convertRomanToArabic(parts[0]);
            number2 = convertRomanToArabic(parts[1]);
        }

        //проверяем корректность цифр от 1 до 10
        if (number1 > 0 && number1 < 11 && number2 > 0 && number2 < 11) {
            calcResult = result(number1, number2, operation);
            if (itsRomanNumbers) {
                //проверяем для римских цифр что результат не отрицателен и не ноль
                if (calcResult > 1) {
                    //переводим int в римские цифры
                    calculation = convertArabicToRoman(calcResult);
                }
                else {
                    throw new Exception("В римской системе нет отрицательных чисел и нуля");
                }
            } else {
                //переведем полученный результат в строку, тк возвращаем String
                calculation = Integer.toString(calcResult);
            }
            return calculation;
        } else {
            throw new Exception("Неверный ввод данных.\n Можно вводить только арабские или только римские числа от 1 до 10 (включительно)");
        }

    }

    //Функция вычисления выражения для сумму, разности, умножения и деления
    public static int result(int nubmer1, int number2, char operation) {

        int result = 0;
        if (operation == '+') {
            result = nubmer1 + number2;
        }
        if (operation == '-') {
            result = nubmer1 - number2;
        }
        if (operation == '*') {
            result = nubmer1 * number2;
        }
        if (operation == '/') {
            result = nubmer1 / number2;
        }

        return result;
    }

    //Функция для перевода римских цифр (1-10) в int
    public static int convertRomanToArabic(String number) {

        int result = 0;
        String[] romanNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (int i = 0; i < 10; i++) {
            if (number.equals(romanNumbers[i])) {
                result = i + 1;
                break;
            }
        }

        return result;
    }

    //перевод арабских в римские цифры
    public static String convertArabicToRoman(int arabicNumber) {

        String result = "";
        if (arabicNumber == 100) {
            return "C";
        }
        ;

        result += toRomanNumerals(arabicNumber / 10, "C", "L", "X");
        arabicNumber = arabicNumber % 10;
        result += toRomanNumerals(arabicNumber, "X", "V", "I");
        return result;
    }

    public static String toRomanNumerals(int num, String hi, String re, String lo) {

        switch (num) {
            case 9:
                return lo + hi;
            case 8:
                return re + lo + lo + lo;
            case 7:
                return re + lo + lo;
            case 6:
                return re + lo;
            case 5:
                return re;
            case 4:
                return lo + re;
            case 3:
                return lo + lo + lo;
            case 2:
                return lo + lo;
            case 1:
                return lo;
            case 0:
                return "";
        }
        return "";
    }
}





