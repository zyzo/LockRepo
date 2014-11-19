package dha.lockrepo.dao;

import dha.lockrepo.dao.fileio.DataGuardian;
import dha.lockrepo.dao.orm.PropertyFileParser;


public class IdGenerator {

    private static final String PIECE_ID = "TSPIECE_ID";
    private static final String GROUP_ID = "TSGROUP_ID";

    PropertyFileParser parser;
    public IdGenerator() {
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
}
