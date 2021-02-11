package at.nsc.functions;

import java.io.File;
import java.nio.file.Files;


/** NIS DiskAnalyse - Analyse
 * @author Niklas Schachl
 * @version 1.0, 11.2.2021
 */

//This class contains the core of the program
public class Analyse
{
    private final byte[] signature = new byte[8];
    private final byte[] revision = new byte[4];
    private final byte[] headerChecksum = new byte[8];
    private final byte[] positionPartitionTable = new byte[16];
    private final byte[] positionFirstLast = new byte[16];
    private final byte[] guid = new byte[16];
    private final byte[] positionPartitions = new byte[8];
    private final byte[] amountPartitions = new byte[4];
    private final byte[] sizeOfPartitionEntry = new byte[4];
    private final byte[] crc = new byte[4];

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
        int i;
        for (i = 513; i < signature.length; i++)
        {
            signature[i] = allBytes[i];
        }
        for (int ii = 0; ii < revision.length; ii++, i++)
        {
            revision[ii] = allBytes[i];
        }
        for (int iii = 0; iii < headerChecksum.length; iii++, i++)
        {
            headerChecksum[iii] = allBytes[i];
        }
        for (int iv = 0; iv < positionPartitionTable.length; iv++, i++)
        {
            positionPartitionTable[iv] = allBytes[i];
        }
        for (int v = 0; v < positionFirstLast.length; v++, i++)
        {
            positionFirstLast[v] = allBytes[i];
        }
        for (int vi = 0; vi < guid.length; vi++, i++)
        {
            guid[vi] = allBytes[i];
        }
        for (int vii = 0; vii < positionPartitions.length; vii++, i++)
        {
            positionPartitions[vii] = allBytes[i];
        }
        for (int viii = 0; viii < amountPartitions.length; viii++, i++)
        {
            amountPartitions[viii] = allBytes[i];
        }
        for (int ix = 0; ix < sizeOfPartitionEntry.length; ix++, i++)
        {
            sizeOfPartitionEntry[ix] = allBytes[i];
        }
        for (int x = 0; x < crc.length; x++, i++)
        {
            crc[x] = allBytes[i];
        }
    }

    public byte[] getSignature() {
        return signature;
    }

    public byte[] getRevision() {
        return revision;
    }

    public byte[] getHeaderChecksum() {
        return headerChecksum;
    }

    public byte[] getPositionPartitionTable() {
        return positionPartitionTable;
    }

    public byte[] getPositionFirstLast() {
        return positionFirstLast;
    }

    public byte[] getGuid() {
        return guid;
    }

    public byte[] getPositionPartitions() {
        return positionPartitions;
    }

    public byte[] getAmountPartitions() {
        return amountPartitions;
    }

    public byte[] getSizeOfPartitionEntry() {
        return sizeOfPartitionEntry;
    }

    public byte[] getCrc() {
        return crc;
    }
}
