package com.sigma;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Handler {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        File input1 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\input1");
        File input2 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\input2");
        File input3 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\input3");


        File output1 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\output1");
        File output2 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\output2");
        File output3 = new File("C:\\Users\\mikha\\IdeaProjects\\SigmaFile\\src\\com\\sigma\\output3");


        ArrayList<String[]> bottles = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader((new FileReader(input1)));
            String temp = " ";
            while ((temp = br.readLine()) != null) {

                bottles.add(temp.split("\\s+"));
            }
            br.close();

            br = new BufferedReader((new FileReader(input2)));
            temp = " ";
            while ((temp = br.readLine()) != null) {

                bottles.add(temp.split("\\s+"));
            }
            br.close();

            br = new BufferedReader((new FileReader(input3)));
            temp = " ";
            while ((temp = br.readLine()) != null) {

                bottles.add(temp.split("\\s+"));
            }
            br.close();


            System.out.println(bottles.get(0)[0]);   // N ? N.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error file not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in input-output sequence");
        }


        PrintWriter pw1 = new PrintWriter(output1);
        PrintWriter pw2 = new PrintWriter(output2);
        PrintWriter pw3 = new PrintWriter(output3);

        pw1.println("N" + " " + "Bottle" + " " + "Volume" + " " + "Material");
        pw2.println("N" + " " + "Bottle" + " " + "Volume" + " " + "Material");
        pw3.println("N" + " " + "Bottle" + " " + "Volume" + " " + "Material");

        for (String[] array : bottles) {// This loop is used to iterate through the arraylist

            if (!array[2].equals("Volume")) {

                if (Double.parseDouble(array[2]) <= 0.5) {
                    pw1.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);

                } else if (Double.parseDouble(array[2]) < 0.99 && Double.parseDouble(array[2]) > 0.51) {
                    pw2.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);

                } else if (Double.parseDouble(array[2]) >= 1.0) {
                    pw3.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);

                }


            }
        }
        pw1.flush();
        pw1.close();
        pw2.flush();
        pw2.close();
        pw3.flush();
        pw3.close();


        ///READ FOR SORTING
        ArrayList<String[]> bottlesOutput1 = new ArrayList<>();
        ArrayList<String[]> bottlesOutput2 = new ArrayList<>();
        ArrayList<String[]> bottlesOutput3 = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader((new FileReader(output1)));  //scope of variable?
            String temp = " ";
            while ((temp = br.readLine()) != null) {

                bottlesOutput1.add(temp.split("\\s+"));
            }
            br.close();

            br = new BufferedReader((new FileReader(output2)));
            temp = " ";
            while ((temp = br.readLine()) != null) {

                bottlesOutput2.add(temp.split("\\s+"));
            }
            br.close();

            br = new BufferedReader((new FileReader(output3)));
            temp = " ";
            while ((temp = br.readLine()) != null) {

                bottlesOutput3.add(temp.split("\\s+"));
            }
            br.close();


            // System.out.println(bottles.get(0)[0]);   // N ? N.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error file not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in input-output sequence");
        }


        //Sorting
        System.out.println("sort method");
        Scanner sc = new Scanner(System.in);
        String sortMethod = sc.nextLine();
        boolean allEqualVolume = false;
        boolean allEqualMaterial = false;
        boolean allEqualBottle = false;

        for (int i = 0; i < bottlesOutput1.size() - 1; i++) {
            if (Double.parseDouble(bottlesOutput1.get(i)[2]) == Double.parseDouble(bottlesOutput1.get(i + 1)[2])) {
                allEqualVolume = true;
            } else allEqualVolume = false;
        }
        for (int i = 0; i < bottlesOutput1.size() - 1; i++) {
            if (bottlesOutput1.get(i)[3] == bottlesOutput1.get(i + 1)[3]) {
                allEqualMaterial = true;
            } else allEqualMaterial = false;
        }
        for (int i = 0; i < bottlesOutput1.size() - 1; i++) {
            if (bottlesOutput1.get(i)[1] == bottlesOutput1.get(i + 1)[1]) {
                allEqualBottle = true;
            } else allEqualBottle = false;
        }




        Comparator<String[]> byVolume = new Comparator<String[]>() {
            public int compare(String[] x, String[] y) {
                if(Double.parseDouble(x[2]) < Double.parseDouble(y[2])) {
                    return -1;
                } else if(Double.parseDouble(x[2]) == Double.parseDouble(y[2])) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        Comparator<String[]> byMaterial = new Comparator<String[]>() {
            public int compare(String[] x, String[] y) {
                if(x[3].length() < y[3].length()) {
                    return -1;
                } else if(x[3].length() == y[3].length()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };

        Comparator<String[]> byBottle = new Comparator<String[]>() {
            public int compare(String[] x, String[] y) {
                if(x[2].length() < y[2].length()) {
                    return -1;
                } else if(x[2].length() == y[2].length()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };


//sort 1st file
        switch (sortMethod) {
            case "Volume Material Bottle":        //////Double.parseDouble(bottlesOutput1.get(i)[2])
                if (!allEqualVolume) {
                    Collections.sort(bottlesOutput1, byVolume);
                } else if (!allEqualMaterial) {
                    Collections.sort(bottlesOutput1, byMaterial);
                } else {
                    Collections.sort(bottlesOutput1, byBottle);
                }

                break;

            case "Bottle Volume Material":  //////

                if (!allEqualBottle) {
                    Collections.sort(bottlesOutput1, byBottle);
                } else if (!allEqualVolume) {
                    Collections.sort(bottlesOutput1, byVolume);
                } else {
                    Collections.sort(bottlesOutput1, byMaterial);
                }

                break;

            case "Material Volume Bottle":  //////

                if (!allEqualMaterial) {
                    Collections.sort(bottlesOutput1, byMaterial);
                } else if (!allEqualVolume) {
                    Collections.sort(bottlesOutput1, byVolume);
                } else {
                    Collections.sort(bottlesOutput1, byBottle);
                }



                break;

        }


    }
}





/*Програмне завдання. Прочитати та обробити дані з трьох існуючих файлів та занести
оброблені дані у файли, створені засобами Java.
1. Існуючі файли містять інформацію щодо довільних пляшок у наступному форматі:

N Bottle Volume Material
1 Wine 0.75 Glass
2 Juice 0.25 Metal

...

2.Зміст оброблення даних з файлів:
- прочитати дані з існуючих файлів,
- утворити три файли, до яких занести пляшки місткістю: не більше 0.5 (перший
файл), в межах від 0.51 до 0.99 (другий файл), не меншою за 1.0 (третій файл),
- пляшки у всіх утворених файлах посортувати згідно одного з трьох варіантів:
- по Volume, при рівності – по Material, при рівності – по Bottle (основний спосіб),
- по Bottle, при рівності – по Volume, при рівності – по Material,
- по Material, при рівності – по Volume, при рівності – по Bottle.
- спосіб сортування задавати довільним способом (Scanner, масив String[] args методу
main, тощо).
3.Передбачити виведення потрібної інформації у випадку виникнення Exceptions при
роботі з файлами.
4.Для перетворення текстового рядка у масив Strings використати метод split() класу
String. Для перетворення текстових змінних у числові використати метод parseDouble()
класу Double.*/
