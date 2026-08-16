import java.util.Scanner;

class CountDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        n = Math.abs(n);
        int count = 0;

        if (n == 0)
            count = 1;
        else {
            while (n != 0) {
                n = n / 10;
                count++;
            }
        }

        System.out.println("Number of digits = " + count);
    }
}
