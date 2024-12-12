import java.util.Scanner;

class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, 1 + 2) или 'q' для выхода:");

        while (true) {
            System.out.print("Input: ");
            String input = scanner.nextLine();

            // Проверка ввода для выхода
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Завершение работы.");
                break;
            }

            try {
                String result = calc(input);
                System.out.println("Output: " + result);
            } catch (MyException e) {
                System.out.println("Output: throws Exception //" + e.getMessage());
                System.out.println("Завершение работы.");
                break; // Завершение работы при возникновении исключения
            }
        }
        scanner.close();
    }

    public static String calc(String input) {
        int result; //для вывода результата из функции
        String input2 = input.trim();//удаление пустых символов вначале и в конце
        String[] arr = input2.split(" "); //за счет пробела-разделяем на массив

        // Проверка формата ввода: два операнда и один оператор (три элемента в массиве)
        if (arr.length != 3) {
            throw new MyException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // Парсим массив операндов и операции
        int operand1 = parseNumber(arr[0]);
        String operator = arr[1];
        int operand2 = parseNumber(arr[2]);

        // Проверка диапазона чисел
        if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) {
            throw new MyException("т.к. числа должны быть от 1 до 10 включительно");
        }

        // Вычисляем результат в зависимости от оператора
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) {
                    throw new MyException("т.к. деление на ноль невозможно");
                }
                result = operand1 / operand2; // Целочисленное деление
                break;
            default:
                throw new MyException("т.к. операция '" + operator + "' не поддерживается");
        }
        return String.valueOf(result);
    }

    static int parseNumber(String str) {
        try {
            int number = Integer.parseInt(str);
            if (number < 1 || number > 10) {
                throw new MyException("т.к. число '" + number + "' вне допустимого диапазона");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new MyException("т.к. формат числа '" + str + "' неверный");
        }
    }
}