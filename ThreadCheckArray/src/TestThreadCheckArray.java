import java.util.Scanner;
import java.util.ArrayList;

public class TestThreadCheckArray {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;
            System.out.println("Enter array size");
            int num  = input.nextInt();
            
            // int[] array = new int[num];
            ArrayList<Integer> array = new ArrayList<>();
            
            System.out.println("Enter numbers for array");
            // for (int index = 0; index < num; index++) 
            //     array[index] = input.nextInt();
            for (int index = 0; index < num; index++) 
                array.add(input.nextInt());

            System.out.println("Enter number");
            // num = input.nextInt();
            int target = input.nextInt();

            // SharedData sd = new SharedData(array, num);
            SharedData sd = new SharedData(array, target);

            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }
            // System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().length);
            System.out.println("Solution for b : " + sd.getB() + ", n = " + sd.getArray().size());

            System.out.print("I:    ");
            // for(int index = 0; index < sd.getArray().length ; index++)
            for(int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();

            System.out.print("A:    ");
            // for (int index : sd.getArray())
            for (int value : sd.getArray()) {
                System.out.print(value);
                int counter = 5;
                int temp = value;
                while (true) {
                    temp = temp / 10;
                    counter--;
                    if (temp == 0)
                        break;
                }
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }
            System.out.println();

            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");    
            }
        }
    }
}
