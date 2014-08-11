package dha.lockrepo.business;

import dha.lockrepo.core.domains.TopSecretGroup;
import dha.lockrepo.core.domains.TopSecretPiece;

public interface TopSecretService {

    /**
     * Add the TopSecret information to a default group
     */
    void addTopSecretPiece(TopSecretPiece sPiece);

    void addTopSecretPiece(TopSecretPiece sPiece, TopSecretGroup sGroup);

    void removeTopSecretPieceById(Long sPieceId);

    /**
     * If the group is not empty, move all the TopSecret pieces to default group
     */
    void removeTopSecretGroupById(Long sGroupId);
}
