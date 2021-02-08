package at.nsc.functions;

import java.util.Scanner;

/** NIS DiskAnalyse - UI
 * @author Niklas Schachl
 * @version 1.0, 8.2.2021
 */
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
                System.out.println("* Version: 1.0                     *");
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

    private static String displayByteInline(byte[] bytes)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte aByte : bytes) {
            stringBuilder.append(aByte);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private static String displayResult(Analyse analyse)
    {
       StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Signature: " + displayByteInline(analyse.getSignature()) + "\n");
        stringBuilder.append("Revision: " + displayByteInline(analyse.getRevision()) + "\n");
        stringBuilder.append("Header length and checksum: " + displayByteInline(analyse.getHeaderChecksum()) + "\n");
        stringBuilder.append("Position of the partition-tables: " + displayByteInline(analyse.getPositionPartitionTable()) + "\n");
        stringBuilder.append("Position of the first and last block: " + displayByteInline(analyse.getPositionFirstLast()) + "\n");
        stringBuilder.append("GUID: " + displayByteInline(analyse.getGuid()) + "\n");
        stringBuilder.append("Position of the partition: " + displayByteInline(analyse.getPositionPartitions()) + "\n");
        stringBuilder.append("Amount of Partitions: " + displayByteInline(analyse.getAmountPartitions()) + "\n");
        stringBuilder.append("Size of the partition entry: " + displayByteInline(analyse.getSizeOfPartitionEntry()) + "\n");
        stringBuilder.append("Table-checksum (CRC32): " + displayByteInline(analyse.getCrc()) + "\n");

        return stringBuilder.toString();
    }
}
