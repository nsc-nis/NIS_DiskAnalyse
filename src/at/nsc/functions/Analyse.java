package at.nsc.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** NIS DiskAnalyse - Analyse
 * @author Niklas Schachl
 * @version 1.0, 8.2.2021
 */
public class Analyse
{
    private Byte[] signature = new Byte[8];
    private Byte[] revision = new Byte[4];
    private Byte[] headerChecksum = new Byte[8];
    private Byte[] positionPartitionTable = new Byte[16];
    private Byte[] positionFirstLast = new Byte[16];
    private Byte[] guid = new Byte[16];
    private Byte[] positionPartitions = new Byte[8];
    private Byte[]

    public void readFile(String fileName)
    {
        byte[] buffer = new byte[0];
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(fileName);
            buffer = inputStream.readAllBytes();

            System.out.println("************************************");
            System.out.println("*      File successfully read      *");
            System.out.println("************************************");
        }
        catch (Exception exception)
        {
            UI.displayException(exception.getMessage());
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    private void sort(Byte[] allBytes)
    {
        Byte[] signature
        for (int i = 0; i < 8; i++)
        {

        }
    }
}
