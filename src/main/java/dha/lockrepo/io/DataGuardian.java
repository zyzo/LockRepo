package dha.lockrepo.io;

import dha.lockrepo.core.domains.TopSecretGroup;
import dha.lockrepo.core.domains.TopSecretPiece;

/**
 * Communication class with the data storage file
 * 
 * @author zyzo
 * 
 */
public interface DataGuardian {
    
    void writeToFile(String content, Integer line);

    void deleteFromFile(Integer line);

    TopSecretPiece findPieceById(Long id);

    TopSecretGroup findGroupById(Long id);
}
