package dha.lockrepo.dao;

import java.io.FileWriter;
import java.io.IOException;

import dha.lockrepo.dao.fileio.DataGuardian;
import dha.lockrepo.dao.orm.PropertyFileParser;


public class IdGenerator {

    private static final String PIECE_ID = "TSPIECE_ID";
    private static final String GROUP_ID = "TSGROUP_ID";

    PropertyFileParser parser;

    private IdGenerator() {
        DataGuardian dataGuardian = DataGuardian.getInstance(FileConstants.FILE_ID_GEN);
        parser = new PropertyFileParser(dataGuardian);
    }

    public Long getPieceId() {
        return Long.parseLong((String) parser.getProperty(PIECE_ID));
    }

    /**
     * 
     * @param i
     *            Number to add to id. Must be positive
     */
    public void incrementPieceId(int i) {
        if (i <= 0)
            return;
        Long newId = this.getPieceId() + i;
        parser.setProperty(PIECE_ID, newId.toString());
    }

    public Long getGroupId() {
        return Long.parseLong((String) parser.getProperty(GROUP_ID));
    }

    /**
     * 
     * @param i
     *            Number to add to id. Must be positive
     */
    public void incrementGroupId(int i) {
        if (i <= 0)
            return;
        Long newId = this.getGroupId() + i;
        parser.setProperty(GROUP_ID, newId.toString());
    }

    public static void prepare() {
        IdGenerator idGen = IdGenerator.getInstance();
        try {
            idGen.getPieceId();
            idGen.getGroupId();
        } catch (Exception e) {
            try {
                FileWriter writer = new FileWriter(FileConstants.FILE_ID_GEN);
                writer.write(IdGenerator.PIECE_ID + " = 0" + System.getProperty("line.separator"));
                writer.write(IdGenerator.GROUP_ID + " = 0");
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static IdGenerator instance;

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }
}
