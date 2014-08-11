package dha.lockrepo.core.domains;

import java.util.ArrayList;
import java.util.List;

import dha.lockrepo.business.IdGenerator;

public class TopSecretGroup {

    private Long              id;
    private List<TopSecretPiece> securePieces;

    public TopSecretGroup() {
        this.id = IdGenerator.generateSecureGroupId();
        this.securePieces = new ArrayList<TopSecretPiece>();
    }

    public Long getId() {
        return this.id;
    }
    public void addPiece(TopSecretPiece sp) {
        this.securePieces.add(sp);
    }

    /**
     * For security reasons, only a clone of the actual list is returned
     */
    public List<TopSecretPiece> getCloneListOfSecurePieces() {
        return new ArrayList<TopSecretPiece>(this.securePieces);
    }
}
