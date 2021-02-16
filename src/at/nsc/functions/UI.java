package at.nsc.functions;

import java.util.Locale;
import java.util.Scanner;

/** NIS DiskAnalyse - UI
 * @author Niklas Schachl
 * @version 1.0, 8.2.2021
 * @version 1.1, 16.2.2021
 */

//This class contains everything about the UserInterface
public class UI
{
    private static boolean firstStart = true;
    private static final Scanner sc = new Scanner(System.in);

    public static void mainMenu()
    {
        System.out.println();
        if (firstStart)
        {
            System.out.println("************************************");
            System.out.println("*  Welcome to NIS DiskAnalyse 1.0  *");
            System.out.println("*                                  *");
            System.out.println("************************************");
            System.out.println("Type in 'action.program.help' to view a list of possible commands");
            firstStart = false;
        }

        System.out.print("main@DiskAnalyse:~$ ");
        String input = sc.next();

        switch (input) {
            case "action.program.help" -> {
                System.out.println(" ");
                System.out.println("Command                            Explanation");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(" action.program.quit               Ends this program");
                System.out.println(" action.analyse.start              Starts to analyse a file");
                System.out.println(" action.program.info               Displays information about this program");
                System.out.println("----------------------------------------------------------------------------");
                mainMenu();
            }
            case "action.program.quit" -> {
                System.out.println("************************************");
                System.out.println("*            Exiting...            *");
                System.out.println("*                                  *");
                System.out.println("************************************");
            }
            case "action.analyse.start" -> startAnalyse();
            case "action.program.info" -> {
                System.out.println(" ");
                System.out.println("************************************");
                System.out.println("*          NIS DiskAnalyse         *");
                System.out.println("* Version: 1.1                     *");
                System.out.println("* A simple command-line tool to    *");
                System.out.println("* analyse drives (.iso-files)      *");
                System.out.println("************************************");
                mainMenu();
            }
            default -> displayException(String.format("Command not recognized! %nType in 'action.program.help' to view a list of possible commands"));
        }
    }

    public static void displayException (String message)
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!             ERROR              !!");
        System.out.println("!!       An error occurred        !!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Error: " + message);

        mainMenu();
    }

    private static void startAnalyse()
    {
        Analyse analyse = new Analyse();

        System.out.println(" ");
        System.out.println("Type in the name of the file you want to analyse:");
        System.out.print("analyse@DiskAnalyse:~$ ");

        String fileName = sc.next();

        if (!fileName.contains(".iso"))
            displayException("File format not supported!");
        else {
            analyse.readFile(fileName);
        }

        System.out.println(displayResult(analyse));
    }

    private static String displayByte(byte[] bytes)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte aByte : bytes)
        {
            stringBuilder.append("0x");
            stringBuilder.append(Integer.toHexString(Math.abs(aByte)).toUpperCase(Locale.ROOT));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private static String displayResult(Analyse analyse)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");
        stringBuilder.append("************************************\n");
        stringBuilder.append("Signature: " + displayByte(analyse.getSignature()) + "\n");
        stringBuilder.append("Header length and checksum: " + displayByte(analyse.getHeaderChecksumAndSize()) + "\n");
        stringBuilder.append("Amount of Partitions: " + displayByte(analyse.getAmountPartitions()) + "\n");
        stringBuilder.append("Partition-size: " + displayByte(analyse.getPartitionSize()) + "\n");
        stringBuilder.append("First useable LBA: " + displayByte(analyse.getFirstLBA()) + "\n");
        stringBuilder.append("GUID: " + displayByte(analyse.getGuid()) + "\n");
        stringBuilder.append("Attributes: " + displayByte(analyse.getAttributes()) + "\n");
        stringBuilder.append("Partition-name: " + displayByte(analyse.getPartitionName()) + "\n");
        stringBuilder.append("************************************\n");

        return stringBuilder.toString();
    }
}
