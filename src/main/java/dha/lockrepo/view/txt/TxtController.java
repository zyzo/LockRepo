package dha.lockrepo.view.txt;

import java.util.Scanner;

import dha.lockrepo.business.SystemConfigService;
import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.domains.TopSecretPieceBE;

public class TxtController {

    private static final String FIND_ERROR_MSG = "Error syntax : find id (id must be positive)";
    private static final String ADD_ERROR_MSG = "Error syntax : add title username passwd <info>";
    private static final String UPDATE_ERROR_MSG = "Error syntax : update id (--(title|groupId|info) data)+ (id must be positive)";
    private static final String PIECE_NOT_FOUND_MSG = "Piece not found";
    private static final String REMOVE_ERROR_MSG = "Error syntax : deletePiece id (id must be positive)";
    private static final String HELP_MSG = "Possible commands are help|add|update|find|delete|list|version";
    private TopSecretService secretService;
    private Scanner scan;
    private SystemConfigService configService;

    public TxtController() {
        secretService = new TopSecretServiceImpl();
        configService = new SystemConfigService();
        scan = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.print("> ");
            String input = scan.nextLine();
            String[] tokens = input.split(" ");
            if (tokens.length < 1)
                continue;
            switch (tokens[0].trim()) {
            case "help":
                System.out.println(HELP_MSG);
                break;
            case "add":
            case "addPiece":
                doAddPiece(tokens);
                break;
            case "update":
            case "updatePiece":
                doUpdate(tokens);
                break;
            case "find":
            case "findPiece":
                doFindPiece(tokens);
                break;
            case "delete":
            case "deletePiece":
                doRemove(tokens);
                break;
            case "list":
            case "listPieces" :
                secretService.findAll().forEach(System.out::println);
                break;
            case "version":
                System.out.println(configService.get(tokens[0]));
                break;
            default:
                System.out.println("Unknown requests. " + HELP_MSG);
            }
        }
    }

    private boolean doRemove(String[] tokens) {
        TopSecretPieceBE piece = null;
        try {
            piece = secretService.removePieceById(Long.parseUnsignedLong(tokens[1]));
        } catch (Exception e) {
            System.out.println(REMOVE_ERROR_MSG);
        }
        System.out.println(piece == null ? PIECE_NOT_FOUND_MSG : "Piece deleted : " + piece.toString());
        return piece != null;
    }

    private boolean doFindPiece(String[] tokens) {
        TopSecretPieceBE piece = null;
        try {
            piece = secretService.findPieceById(Long.parseUnsignedLong(tokens[1]));
        } catch (Exception e) {
            System.out.println(FIND_ERROR_MSG);
            return false;
        }
        System.out.println(piece == null ? PIECE_NOT_FOUND_MSG : "Piece found : " + piece.toString());
        return true;
    }

    private boolean doUpdate(String[] tokens) {
        TopSecretPieceBE piece = null;
        try {
            piece = secretService.findPieceById(Long.parseUnsignedLong(tokens[1]));
            if (piece == null) {
                System.out.println(PIECE_NOT_FOUND_MSG);
                return false;
            } else {
                for ( int i = 2; i < tokens.length; i += 2 ) {
                    updateFields(piece, tokens[i].trim(), tokens[i + 1].trim());
                }
            }
        } catch (Exception e) {
            System.out.println(UPDATE_ERROR_MSG);
            e.printStackTrace();
            return false;
        }
        System.out.println("Piece updated : " + secretService.update(piece));
        return true;
    }

    private void updateFields(TopSecretPieceBE piece, String key, String value) {
        switch (key) {
        case "--info":
            piece.setInfo(value);
            break;
        case "--title":
            piece.setTitle(value);
            break;
        case "--groupId":
            piece.setGroupId(Long.parseLong(value));
            break;
        case "--user":
            piece.setUsername(value);
            break;
        case "--passwd":
            piece.setPasswd(value);
            break;
        default:
            throw new UnsupportedOperationException();
        }
    }

    private boolean doAddPiece(String[] tokens) {
        TopSecretPieceBE piece = null;
        try {
            piece = new TopSecretPieceBE(0L, tokens[1], tokens[2], tokens[3], tokens.length >= 5 ? tokens[4] : null);
        } catch (Exception e) {
            System.out.println(ADD_ERROR_MSG);
            return false;
        }
        secretService.addTopSecretPiece(piece);
        System.out.println("Information added : " + piece.toString());
        return true;
    }
}
