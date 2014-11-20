package dha.lockrepo.business;

import java.util.List;

import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;

public interface TopSecretService {

    /**
     * Add the TopSecret information to a default group
     */
    void addTopSecretPiece(TopSecretPieceBE sPiece);

    void addTopSecretPiece(TopSecretPieceBE sPiece, TopSecretGroupBE sGroup);

    TopSecretPieceBE findPieceById(Long id);

    List<TopSecretPieceBE> findAll();

    TopSecretPieceBE removePieceById(Long sPieceId);

    public TopSecretPieceBE update(TopSecretPieceBE piece);

    /**
     * If the group is not empty, move all the TopSecret pieces to default group
     */
    void removeTopSecretGroupById(Long sGroupId);
}
