package at.nsc.functions;

import java.util.Scanner;

/** NIS DiskAnalyse - UI
 * @author Niklas Schachl
 * @version 1.0, 8.2.2021
 */
public class UI
{
    private static boolean firstStart = true;
    private static Scanner sc = new Scanner(System.in);

    public static void mainMenu()
    {
        System.out.println("");
        if (firstStart)
        {
            System.out.println("************************************");
            System.out.println("*  Welcome to NIS DiskAnalyse 1.0  *");
            System.out.println("*                                  *");
            System.out.println("************************************");
            System.out.println("Type in 'action.program.help' to view a list of possible commands");
            firstStart = false;
        }

        System.out.printf("main@DiskAnalyse:~$ ");
        String input = sc.next();

        switch (input)
        {
            case "action.program.help":
                System.out.println(" ");
                System.out.println("Command                            Explanation");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(" action.program.quit               Ends this program");
                System.out.println(" action.analyse.start              Starts to analyse a file");
                System.out.println(" action.program.info               Displays information about this program");
                System.out.println("----------------------------------------------------------------------------");
                mainMenu();
                break;
            case "action.program.quit":
                System.out.println("************************************");
                System.out.println("*            Exitting...           *");
                System.out.println("*                                  *");
                System.out.println("************************************");
                break;
            case "action.analyse.start":
                startAnalyse();
                break;
            case "action.program.info":
                System.out.println(" ");
                System.out.println("************************************");
                System.out.println("*          NIS DiskAnalyse         *");
                System.out.println("* Version: 1.0                     *");
                System.out.println("* A simple command-line tool to    *");
                System.out.println("* analyse drives (.iso-files)      *");
                System.out.println("************************************");
                mainMenu();
                break;
            default:
                displayException(String.format("Command not recognized! %nType in 'action.program.help' to view a list of possible commands"));
                break;
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
        System.out.printf("analyse@DiskAnalyse:~$ ");

        String fileName = sc.next();

        if (!fileName.contains(".iso"))
            displayException("File format not supported!");
        else {
            analyse.readFile(fileName);
        }
    }
}
