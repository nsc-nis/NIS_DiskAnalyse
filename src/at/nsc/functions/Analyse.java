package at.nsc.functions;

import java.io.File;
import java.nio.file.Files;


/** NIS DiskAnalyse - Analyse
 * @author Niklas Schachl
 * @version 1.0, 11.2.2021
 * @version 1.1, 16.2.2021
 */

//This class contains the core of the program
public class Analyse
{
    private byte[] signature = new byte[8];
    private byte[] headerChecksumAndSize = new byte[8];
    private byte[] amountPartitions = new byte[4];
    private byte[] partitionSize = new byte[8];
    private byte[] firstLBA = new byte[8];
    private byte[] guid = new byte[16];
    private byte[] attributes = new byte[8];
    private byte[] partitionName = new byte[72];

    public void readFile(String fileName)
    {

        byte[] isoBytes;
        try
        {
            File iso = new File(fileName);
            isoBytes = Files.readAllBytes(iso.toPath());

            System.out.println("************************************");
            System.out.println("*      File successfully read      *");
            System.out.println("************************************");
            sort(isoBytes);

            /* Just for testing purpose
            for (int i = 0; i < isoBytes.length; i++)
                System.out.println(isoBytes[i]);
             */
        }
        catch (Exception exception)
        {
            UI.displayException(exception.getMessage());
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    private void sort(byte[] allBytes)
    {

        signature = slice(allBytes, 512, 8);
        headerChecksumAndSize = slice(allBytes, 524,4);
        amountPartitions = slice(allBytes, 592, 4);
        partitionSize = slice(allBytes, 596, 4);
        firstLBA = slice(allBytes, 552, 8);

        int indexFirstLBA = firstLBA[1] * 128;

        guid = slice(allBytes, indexFirstLBA, 16);
        attributes = slice(allBytes, indexFirstLBA+48, 8);
        partitionName = slice(allBytes, indexFirstLBA+56, 72);
    }

    public byte[] getSignature() {
        return signature;
    }

    public byte[] getHeaderChecksumAndSize() {
        return headerChecksumAndSize;
    }

    public byte[] getAmountPartitions() {
        return amountPartitions;
    }

    public byte[] getPartitionSize() {
        return partitionSize;
    }

    public byte[] getFirstLBA() {
        return firstLBA;
    }

    public byte[] getGuid() {
        return guid;
    }

    public byte[] getAttributes() {
        return attributes;
    }

    public byte[] getPartitionName() {
        return partitionName;
    }

    public byte[] slice(byte[] allBytes, int allLength, int newLength)
    {
        byte[] newArray = new byte[newLength];
        int count = 0;
        for (int i = allLength; i < allLength+newLength; i++)
        {
            newArray[count] = allBytes[i];
            count++;
        }
        return newArray;
    }
}
