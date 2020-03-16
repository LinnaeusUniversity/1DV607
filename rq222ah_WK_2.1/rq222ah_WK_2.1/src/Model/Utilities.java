package Model;

import java.util.Scanner;

public class Utilities {
    static String[] strEng = {"Please give an integer within the range :",
            "Please give a valid number!",
            "Please a non-empty string"};
    static String[] strSwe = {"Ange ett rätt tal :",
            "Ange ett gilltigt tal!",
            "Ange en gilltig text"};

    public static int getInteger(int lowLimit, int upperLimit, LanguageType lang) {
        String strLang[] = (lang == LanguageType.SVENSKA) ? strSwe : strEng;
        String str = String.format(strLang[0], lowLimit, upperLimit);
        System.out.println(str);
        int number = 0;
        boolean goodNumber = false;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println();

            number = reader.nextInt();

            goodNumber = (number >= lowLimit) && (number <= upperLimit);
            if (!goodNumber)
                System.out.println(strLang[0]);

        } while (!goodNumber);

        // reader.close();
        return number;
    }

    public static void padChars(char chr, int count) {
        for (int i = 0; i < count; i++)
            System.out.print(chr);
        System.out.println();
    }

    public static String getString(LanguageType lang) {
        boolean done = true;
        String strLang[] = (lang == LanguageType.SVENSKA) ? strSwe : strEng;

        String str = "";
        Scanner reader = new Scanner(System.in);
        do {
            done = reader.hasNextLine();

            if (done) {
                str = reader.nextLine();
                str = str.trim();

                done = !(str.isEmpty() || str.isBlank());
            } else
                System.out.println(strLang[2] + " : ");

        } while (!done);

        return str;
    }
//    public char charInput(){
//
//        Scanner scanner=new Scanner(System.in);
//        char ch=scanner.next().toLowerCase().charAt(0);
//        if(ch<'a' &&ch>'k'){
//           return ch=scanner.next().toLowerCase().charAt(0)  ;
//        }
//        return ch;
//    }
}
