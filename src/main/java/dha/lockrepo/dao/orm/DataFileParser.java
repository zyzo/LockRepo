package dha.lockrepo.dao.orm;

import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;

public class DataFileParser {

    private static final char MARKER_PREFIX = '#';
    private static final String TOP_SECRET_PIECE_INFO_MISSING = "???";
    private static final String TOP_SECRET_PIECE_INFO_ESC_CHAR = "\\+";

    public static String constructStringFromPiece(TopSecretPieceBE piece) {
        String info = "";
        if (piece.getInfo().isPresent()) {
            info = piece.getInfo().get().replace(" ", TOP_SECRET_PIECE_INFO_ESC_CHAR);
        } else {
            info = TOP_SECRET_PIECE_INFO_MISSING;
        }
        return MARKER_PREFIX + piece.getId().toString() + " " + piece.getGroupId() + " " + piece.getTitle() + " "
                + piece.getUsername() + " " + piece.getPasswd() + " " + info;
    }

    public static TopSecretPieceBE constructPieceFromString(String s) {
        if (s.charAt(0) != MARKER_PREFIX)
            throw new RuntimeException(DataFileParser.class.getSimpleName() + " : Format invalid");
        String[] parse = s.substring(1).split(" ");
        if (parse.length < 6)
            throw new RuntimeException(DataFileParser.class.getSimpleName() + " : Missing information");
        TopSecretPieceBE topSecretPieceBE = new TopSecretPieceBE(Long.parseLong(parse[1]),
                parse[2], parse[3],
                parse[4], parse[5].replace(TOP_SECRET_PIECE_INFO_ESC_CHAR, " "));
        topSecretPieceBE.setId(Long.parseLong(parse[0]));
        return topSecretPieceBE;
    }

    public static boolean lineMatchesPieceId(Long id, String piece) {
        return piece.startsWith(MARKER_PREFIX + String.valueOf(id));
    }

    public static String constructStringFromGroup(TopSecretGroupBE group) {
        return MARKER_PREFIX + group.getId().toString() + " " + group.getName() + " " + group.getDescription();
    }

    public TopSecretGroupBE construcGroupFromString(String s) {
        if (s.charAt(0) != MARKER_PREFIX)
            throw new RuntimeException(this.getClass().getSimpleName() + " : Format invalid");
        String[] parse = s.substring(1).split(" ");
        if (parse.length < 3)
            throw new RuntimeException(this.getClass().getSimpleName() + " : Missing information");
        TopSecretGroupBE topSecretPieceBE = new TopSecretGroupBE(Long.parseLong(parse[0]), parse[1], parse[2]);
        return topSecretPieceBE;
    }
}
