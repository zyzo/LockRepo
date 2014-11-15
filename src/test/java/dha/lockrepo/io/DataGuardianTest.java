package dha.lockrepo.io;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dha.lockrepo.core.domains.TopSecretPieceBE;

public class DataGuardianTest {

    DataGuardian dg;
    TopSecretPieceBE b1, b2, b3;

    @Before
    public void init() {
        dg = new DataGuardian();
        b1 = new TopSecretPieceBE("amazon", "shopaholic", "applle", 0L);
        b2 = new TopSecretPieceBE("insa", "lebotlan", "ias", 1L);
        b3 = new TopSecretPieceBE("insa", "lebotlan", "ias", 1L);
    }

    @Test
    public void testSavePiece() throws IOException {
        dg.savePiece(b1);
        dg.savePiece(b2);
        dg.savePiece(b3);
    }

    @Test
    public void testFindPieceById() throws IOException {
        TopSecretPieceBE b = dg.findPieceById(b1.hashCode());
        assertTrue(b.equals(b1));
        b = dg.findPieceById(b2.hashCode());
        assertTrue(b.equals(b2));
    }

    @Test
    public void testDeletePiece() {
    }

}
