package dha.lockrepo.dao;

import java.io.IOException;

import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretGroupVO;
import dha.lockrepo.dao.fileio.DataGuardian;
import dha.lockrepo.dao.orm.DataFileParser;

public class TopSecretDAO {

    private DataGuardian pieceGuardian = DataGuardian.getInstance(FileConstants.FILE_TOP_SECRET_PIECE);
    private DataGuardian groupGuardian = DataGuardian.getInstance(FileConstants.FILE_TOP_SECRET_GROUP);
    private IdGenerator idGen = new IdGenerator();

    public void savePiece(TopSecretPieceBE piece) {
        Long nextId = idGen.getPieceId();
        piece.setId(nextId);
        pieceGuardian.writeLine(DataFileParser.constructStringFromPiece(piece));
        idGen.incrementPieceId(1);
    }

    public void saveGroup(TopSecretGroupBE group) {
        Long nextId = idGen.getGroupId();
        group.setId(nextId);
        groupGuardian.writeLine(DataFileParser.constructStringFromGroup(group));
        idGen.incrementGroupId(1);
    }

    public TopSecretGroupVO findGroupById(Long id) {
        return null;
    }

    /**
     * 
     * @param id
     *            identifier of the TopSecretPiece to find
     * @return
     *         null if the piece is not found
     * @throws IOException
     */
    public TopSecretPieceBE findPieceById(Long id) {
        String pieceString = " ";
        TopSecretPieceBE piece = null;
        pieceGuardian.readBegin();
        while (pieceString != null) {
            pieceString = pieceGuardian.readLine();
            if (pieceString == null) {
                break;
            } else if (DataFileParser.lineMatchesPieceId(id, pieceString)) {
                piece = DataFileParser.constructPieceFromString(pieceString);
            }
        }
        pieceGuardian.readClose();
        return piece;
    }
}
