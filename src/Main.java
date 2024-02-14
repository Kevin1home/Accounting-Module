import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        YearlyReport yearlyReport = new YearlyReport();

        System.out.println("Добро пожаловать!"); // Приветствие
        while (true) {
            System.out.println("Выберите команду:");
            printMenu();
            String inputCommand = scan.next();
            int command = Integer.parseInt(inputCommand);
            if (command == 1) { // Считать все месячные отчёты (item_name,is_expense,quantity,unit_price)
                for (int i = 1; i <= 3; i++) {
                    ArrayList<String> lines = fileReader.readFileContents("m.20210" + i + ".csv");
                    MonthlyReport monthlyReport = new MonthlyReport();
                    for (int j = 1; j < lines.size(); j++) {
                        String line = lines.get(j);
                        String[] lineContents = line.split(",");
                        Transaction transaction = new Transaction(lineContents[0], Integer.parseInt(lineContents[2]), Integer.parseInt(lineContents[3]));
                        if (lineContents[1].equals("TRUE")) {
                            monthlyReport.saveExpenses(transaction); // Сохранить в расходы
                        }
                        else if (lineContents[1].equals("FALSE")) {
                            monthlyReport.saveProfits(transaction); // Сохранить в доходы
                        }
                        else {
                            System.out.println("Ошибка в данных файла");
                            break;
                        }
                    }
                monthTotalPerYear.saveMonthlyReports(monthlyReport);
                }
                System.out.println("Месячные отчёты считаны");
            }
            else if (command == 2) { // Считать годовой отчёт (month,amount,is_expense)
                ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] lineContents = line.split(",");
                    if (lineContents[2].equals("true")) {
                        yearlyReport.saveExpenses(Integer.parseInt(lineContents[0]), Integer.parseInt(lineContents[1])); // Сохранить в расходы
                    }
                    else if (lineContents[2].equals("false")) {
                        yearlyReport.saveProfits(Integer.parseInt(lineContents[0]), Integer.parseInt(lineContents[1])); // Сохранить в доходы
                    }
                    else {
                        System.out.println("Ошибка в данных файла");
                        break;
                    }
                }
                System.out.println("Годовой отчёт считан");
            }
            else if (command == 3) { // Сверить отчёты
                if (monthTotalPerYear.monthlyReports.isEmpty() || yearlyReport.amountsExpenses.isEmpty()) {
                    System.out.println("Месячные и/или годовой отчёты не были считаны");
                    System.out.println("Воспользуйтесь прежде командами 1 и/или 2");
                }
                else {
                    ReportEngine reportEngine = new ReportEngine(monthTotalPerYear, yearlyReport);
                    reportEngine.compareReports();
                }
            }
            else if (command == 4) { // Вывести информацию обо всех месячных отчётах
                if (monthTotalPerYear.monthlyReports.isEmpty()) {
                    System.out.println("Месячные отчёты не были считаны");
                    System.out.println("Воспользуйтесь прежде командой 1");
                }
                else {
                    for (int i = 0; i <3; i++) {
                        System.out.println("Год 2021. Месяц " + (i+1) + "-й.");

                        MonthlyReport monthlyReport = monthTotalPerYear.monthlyReports.get(i);

                        HashMap<String, Integer> maxProfitGood = monthlyReport.findMaxProfitGood();
                        System.out.println("Самый прибыльный товар: " + maxProfitGood.keySet());
                        System.out.println("Его прибыль: " + maxProfitGood.values());

                        HashMap<String, Integer> maxExpense = monthlyReport.findMaxExpenseCost();
                        System.out.println("Самая большая трата: " + maxExpense.keySet());
                        System.out.println("Её расход: " + maxExpense.values());
                    }
                }
            }
            else if (command == 5) { // Вывести информацию о годовом отчёте
                if (yearlyReport.amountsExpenses.isEmpty()) {
                System.out.println("Годовой отчёт не был считан");
                System.out.println("Воспользуйтесь прежде командой 2");
                }
                else {
                    System.out.println("Год 2021-й.");
                    yearlyReport.findProfitEachMonth();
                    yearlyReport.findAverageExpense();
                    yearlyReport.findAverageProfit();
                }
            }
            else if (command == 6) { // Выйти из программы
                System.out.println("Напишите ключевое слово: EXIT");
                String exit = scan.next();
                if (exit.equals("EXIT")) {
                    return;
                }
                else {
                    System.out.println("Ключевое слово не найдено");
                }
            }
            else {
                System.out.println("Команда не найдена");
            }
        }
    }

    static void printMenu() { // Меню, 6 команд
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию обо всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("6. Выйти из программы");
    }
}

