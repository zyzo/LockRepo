package dha.lockrepo.business;

import dha.lockrepo.core.domains.SecureGroup;
import dha.lockrepo.core.domains.SecurePiece;

public interface SecureService {

    /**
     * Add the secure information to a default group
     */
    void addSecurePiece(SecurePiece sPiece);

    void addSecurePiece(SecurePiece sPiece, SecureGroup sGroup);

    void removeSecurePieceById(Long sPieceId);

    /**
     * If the group is not empty, move all the secure pieces to default group
     */
    void removeSecureGroupById(Long sGroupId);
}
