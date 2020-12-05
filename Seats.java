import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Seats {
    public static void main(String[] args) {
        String[] IDs = loadfile("data.txt").split(" ");
        LinkedList<Integer> allIDs = new LinkedList<Integer>();
        int biggestID = 0;
        int i = 0;

        for (String ID : IDs) {
            String asBinary = ID.replaceAll("F", "0");
            asBinary = asBinary.replaceAll("B", "1");
            asBinary = asBinary.replaceAll("L", "0");
            asBinary = asBinary.replaceAll("R", "1");
            int asInt = fRow(0, 1023, asBinary);

            if (asInt > biggestID) {
                biggestID = asInt;
            }

            allIDs.add(asInt);
        }

        System.out.println("The biggest id is " + biggestID);

        
        for (int expectedSeat = 1; expectedSeat < biggestID; expectedSeat++)
            if (allIDs.contains(expectedSeat + 1) && allIDs.contains(expectedSeat - 1) && !allIDs.contains(expectedSeat)) {
                System.out.println("my seat is " + expectedSeat);
            }

    }

    // recursively go through rows
    public static int fRow(int seatsStart, int seatEnd, String remainingPInd) {
        if (remainingPInd == "") {
            return seatsStart + 1;

        }
        int pivot = seatsStart / 2 + seatEnd / 2 + (seatsStart % 2 + seatEnd % 2) / 2;

        if (remainingPInd.charAt(0) == '0') {
            return fRow(seatsStart, pivot, remainingPInd.substring(1));
        }

        else {
            return fRow(pivot, seatEnd, remainingPInd.substring(1));
        }
    }

    public static String loadfile(String fname) {
        String out = "";
        try {
            Scanner input = new Scanner(new File(fname));
            while (input.hasNextLine()) {
                out = out + input.nextLine() + " ";
            }

        } catch (FileNotFoundException e) {
            return null;
        }

        return out;
    }
}