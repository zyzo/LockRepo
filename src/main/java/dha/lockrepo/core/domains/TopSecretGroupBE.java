package dha.lockrepo.core.domains;

import java.util.ArrayList;
import java.util.List;

public class TopSecretGroupBE extends TopSecretBE {

    private String name;
    private List<TopSecretPieceBE> securePieces;

    public TopSecretGroupBE() {
        this.id = (long) this.hashCode();
        this.setSecurePieces(new ArrayList<TopSecretPieceBE>());
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TopSecretPieceBE> getSecurePieces() {
        return securePieces;
    }

    public void setSecurePieces(List<TopSecretPieceBE> securePieces) {
        this.securePieces = securePieces;
    }

}
