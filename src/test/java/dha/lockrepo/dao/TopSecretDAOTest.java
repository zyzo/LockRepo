package dha.lockrepo.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dha.lockrepo.core.domains.TopSecretPieceBE;

public class TopSecretDAOTest {
    TopSecretDAO dao;
    TopSecretPieceBE b1, b2, b3;

    @Before
    public void init() {
        dao = new TopSecretDAO();
        b1 = new TopSecretPieceBE(123L, "shopaholic", "applle", "amazon", "i love shopping");
        b2 = new TopSecretPieceBE(345L, "oraclemysql", "ias", "insa", "SELECT * FROM * WHERE *=*");
        b3 = new TopSecretPieceBE(678L, "stackoverflow", "ias", "insa", null);
    }

    @Test
    public void testSavePiece() throws IOException {
        dao.savePiece(b1);
        dao.savePiece(b2);
        dao.savePiece(b3);
    }

    @Test
    public void testFindPieceById() throws IOException {
        TopSecretPieceBE b = dao.findPieceById(0L);
        assertTrue(b1.equals(b));
    }

    @Test
    public void testDeletePiece() {
        throw new UnsupportedOperationException();
    }
}
