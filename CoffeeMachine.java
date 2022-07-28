package machine;
import java.util.Scanner;

enum States {
    CHOOSINGACTION, CHOOSINGVARIANT, FILLING, REMAINING;
}

class Methods {

    static Scanner scanner = new Scanner(System.in);
    static int waterPerCoffee = 200;
    static int milkPerCoffee = 50;
    static int beansPerCoffee = 15;
    static int priceOfCoffee = 0;
    static int waterInMachine = 400;
    static int milkInMachine = 540;
    static int moneyInMachine = 550;
    static int beansInMachine = 120;
    static int cupsInMachine = 9;

    public static boolean determine(int amountOfWater, int amountOfMilk, int amountOfBeans) {
        boolean possible = false;
        boolean enoughWater = waterInMachine >= amountOfWater;
        boolean enoughMilk = milkInMachine >= amountOfMilk;
        boolean enoughBeans = beansInMachine >= amountOfBeans;
        if (enoughWater && enoughMilk && enoughBeans) {
            possible = true;
        }
        return possible;
    }

    public static boolean[] showDeficits(int water, int milk, int beans) {
        boolean enoughWater = waterInMachine >= water;
        boolean enoughMilk = milkInMachine >= milk;
        boolean enoughBeans = beansInMachine >= beans;
        boolean[] deficits = {true, true, true};
        deficits[0] = enoughWater;
        deficits[1] = enoughMilk;
        deficits[2] = enoughBeans;
        return deficits;
    }

    public static void buy() {
        System.out.print("What do you want to buy? ");
        System.out.println("1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String chosen = scanner.next();
        boolean enoughResources = false;
        int choice = 0;
        switch (chosen) {
            case "1":
                choice = 1;
                enoughResources = determine(250, 0, 16);
                waterPerCoffee = 250;
                milkPerCoffee = 0;
                beansPerCoffee = 16;
                priceOfCoffee = 4;
                break;
            case "2":
                choice = 2;
                enoughResources = determine(350, 75, 20);
                waterPerCoffee = 350;
                milkPerCoffee = 75;
                beansPerCoffee = 20;
                priceOfCoffee = 7;
                break;
            case "3":
                choice = 3;
                enoughResources = determine(200, 100, 12);
                waterPerCoffee = 200;
                milkPerCoffee = 100;
                beansPerCoffee = 12;
                priceOfCoffee = 6;
                break;
            case "back":
                return;
            default:
                break;
        }
        if (enoughResources) {
            System.out.println("I have enough resources, making you a coffee!");
            waterInMachine -= waterPerCoffee;
            milkInMachine -= milkPerCoffee;
            beansInMachine -= beansPerCoffee;
            moneyInMachine += priceOfCoffee;
            cupsInMachine--;
        } else {
            boolean[] resources = new boolean[3];
            switch (choice) {
                case 1:
                    resources = showDeficits(250, 0, 16);
                    break;
                case 2:
                    resources = showDeficits(350, 75, 20);
                    break;
                case 3:
                    resources = showDeficits(200, 100, 12);
                    break;
                default:
                    break;
            }
            String output = "Sorry, not enough ";
            if (!resources[0]) {
                output += "water, ";
            }
            if (!resources[1]) {
                output += "milk, ";
            }
            if (!resources[2]) {
                output += "coffee beans, ";
            }

            if (output.charAt(output.length() - 2) == ',') {
                output = output.substring(0, output.length() - 2);
            }
            output += "!";
            System.out.println(output);
        }
    }

        public static void fill() {
            System.out.println("Write how many ml of water you want to add:");
            waterInMachine += scanner.nextInt();
            System.out.println("Write how many ml of milk you want to add:");
            milkInMachine += scanner.nextInt();
            System.out.println("Write how many grams of coffee beans you want to add:");
            beansInMachine += scanner.nextInt();
            System.out.println("Write how many disposable cups of coffee you want to add:");
            cupsInMachine += scanner.nextInt();
        }

        public static void take() {
            System.out.println("I gave you $" + moneyInMachine);
            moneyInMachine = 0;
        }

        public static void showIngredients() {
            System.out.println("The coffee machine has:");
            System.out.println(waterInMachine + " ml of water");
            System.out.println(milkInMachine + " ml of milk");
            System.out.println(beansInMachine + " g of coffee beans");
            System.out.println(cupsInMachine + " disposable cups");
            System.out.println("$" + moneyInMachine + " of money");
        }
    }


public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String action;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();
            switch (action) {
                case "buy":
                    System.out.println();
                    Methods.buy();
                    break;
                case "fill":
                    System.out.println();
                    Methods.fill();
                    break;
                case "take":
                    System.out.println();
                    Methods.take();
                    break;
                case "remaining":
                    System.out.println();
                    Methods.showIngredients();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (!action.equals("exit"));
    }

}
