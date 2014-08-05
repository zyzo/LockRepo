package dha.lockrepo.core.domains;

import java.util.ArrayList;
import java.util.List;

import dha.lockrepo.core.utils.IdGenerator;

public class SecureGroup {

    private Long              id;
    private List<SecurePiece> securePieces;

    public SecureGroup() {
        this.id = IdGenerator.generateSecureGroupId();
        this.securePieces = new ArrayList<SecurePiece>();
    }

    public Long getId() {
        return this.id;
    }
    public void addPiece(SecurePiece sp) {
        this.securePieces.add(sp);
    }

    /**
     * For security reasons, only a clone of the actual list is returned
     */
    public List<SecurePiece> getCloneListOfSecurePieces() {
        return new ArrayList<SecurePiece>(this.securePieces);
    }
}
