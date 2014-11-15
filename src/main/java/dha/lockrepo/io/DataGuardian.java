package dha.lockrepo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import dha.lockrepo.core.Constants;
import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;

public class DataGuardian {

    private static final char MARKER_PREFIX = '#';
    private BufferedWriter writer;
    private BufferedReader reader;

    public DataGuardian() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constants.TOP_SECRET_FILE, true)));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.TOP_SECRET_FILE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void savePiece(TopSecretPieceBE piece) {
        writeToFile(constructStringFromPiece(piece));
    }

    private String constructStringFromPiece(TopSecretPieceBE piece) {
        return MARKER_PREFIX + piece.getId().toString()
                + " " + piece.getGroupId()
                + " " + piece.getTitle()
                + " " + piece.getUsername() 
                + " " + piece.getPasswd()
                + " " + piece.getInfo();
    }

    private TopSecretPieceBE constructPieceFromString(String s) {
        if (s.charAt(0) != MARKER_PREFIX)
            throw new RuntimeException(this.getClass().getSimpleName() + " : Format invalid");
        String[] parse = s.substring(1).split(" ");
        if (parse.length < 6)
            throw new RuntimeException(this.getClass().getSimpleName() + " : Missing information");
        TopSecretPieceBE topSecretPieceBE = new TopSecretPieceBE(Long.parseLong(parse[1]),
                parse[2], parse[3], parse[4], parse[5]);
        return topSecretPieceBE;
    }

    private void writeToFile(String content) {
        try {
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(this.getClass().getSimpleName() + " : unable to write");
        }
    }

    public void deletePieceById() {

    }

    /**
     * 
     * @param i
     *            identifier of the TopSecretPiece to find
     * @return
     *         null if the piece is not found
     * @throws IOException
     */
    public TopSecretPieceBE findPieceById(int i) throws IOException {
        String s = " ";
        try {
            reader.mark(999999);
            while (s != null) {
                s = reader.readLine();
                if (s == null)
                    return null;
                if (s.startsWith(MARKER_PREFIX + String.valueOf(i))) {
                    return constructPieceFromString(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.reset();
        }
        return null;
    }

    public TopSecretGroupBE findGroupById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
