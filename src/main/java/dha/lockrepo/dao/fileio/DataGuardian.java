package dha.lockrepo.dao.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import dha.lockrepo.view.ResourceLocator;

public class DataGuardian {

    private String fileName;
    private BufferedWriter writer;
    private BufferedReader reader;
    private enum ReaderState {
        READING, IDLE
    };

    private ReaderState readerState;
    private DataGuardian(String fileName) {
        this.fileName = fileName;
        reset();
    }

    private void reset() {
        init(this.fileName);
    }

    private void init(String fileName) {
        try {
        	String directory = ResourceLocator.getDirectoryPath();
            writer = new BufferedWriter(new FileWriter(directory + fileName, true));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(directory + fileName)));
            readerState = ReaderState.IDLE;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void writeLine(String content) {
        //System.out.println("Writing " + content);
        try {
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(this.getClass().getSimpleName() + " : unable to write");
        }
    }

    public void removeLine(int lineToRemove) {
        try {
            File file = new File(this.fileName);
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(file.getAbsolutePath() + ".tmp");
            PrintWriter pw;
            pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;
            int cnt = 0;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            this.readBegin();
            while ((line = this.readLine()) != null) {
                if (cnt++ != lineToRemove) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            this.readClose();
            //Delete the original file
            if (!file.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            writer.close();
            reader.close();
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(file))
                System.out.println("Could not rename file");
            // Update references attributes to new files
            reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readBegin() {
        if (readerState != ReaderState.IDLE)
            throw new RuntimeException(
                    "Unusual reader state : a read operation must always be closed first (by invoking readClose()) before starting new one ");
        try {
            reader.mark(999999);
            readerState = ReaderState.READING;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        if (readerState != ReaderState.READING)
            throw new RuntimeException(
"Unusual reader state : readBegin() must be called before readLine()");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void readClose() {
        if (readerState != ReaderState.READING)
            throw new RuntimeException(
"Unusual reader state : useless method invoking");
        try {
            reader.reset();
            readerState = ReaderState.IDLE;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, DataGuardian> instances = new HashMap<String, DataGuardian>();

    public static DataGuardian getInstance(String fileName) {
        if (!instances.containsKey(fileName)) {
            instances.put(fileName, new DataGuardian(fileName));
        }
        return instances.get(fileName);
    }
}
