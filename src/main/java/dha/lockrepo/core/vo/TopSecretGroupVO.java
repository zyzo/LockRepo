package dha.lockrepo.core.vo;

import java.util.List;

import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;

public class TopSecretGroupVO extends TopSecretGroupBE {

    private List<TopSecretPieceBE> securePieces;

    public TopSecretGroupVO(Long id, String name, String description, List<TopSecretPieceBE> securePieces) {
        super(id, name, description);
        this.securePieces = securePieces;
    }

    public List<TopSecretPieceBE> getSecurePieces() {
        return securePieces;
    }

    public void setSecurePieces(List<TopSecretPieceBE> securePieces) {
        this.securePieces = securePieces;
    }

}
