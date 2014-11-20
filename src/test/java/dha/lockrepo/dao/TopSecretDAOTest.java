package dha.lockrepo.dao;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dha.lockrepo.core.domains.TopSecretPieceBE;

public class TopSecretDAOTest {
    TopSecretDAO dao;
    TopSecretPieceBE b1, b2, b3, b4, b5;

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
        dao.savePiece(b1);
        TopSecretPieceBE b = dao.findPieceById(b1.getId());
        assertTrue(b1.equals(b));
    }

    @Test
    public void testDeletePiece() {
        b4 = new TopSecretPieceBE(678L, "stackexchange", "ias", "insa", null);
        dao.savePiece(b4);
        assertTrue("Delete failed", dao.deletePieceById(b4.getId()).equals(b4));
    }

    @Test
    public void testUpdatePiece() {
        b5 = new TopSecretPieceBE(123L, "github", "zyzo", "passwd", "info_ver_1");
        dao.savePiece(b5);
        String info = "info_ver_2";
        b5.setInfo(info);
        dao.update(b5);
        assertTrue("Update failed : ", dao.findPieceById(b5.getId()).getInfo().get().equals(info));
    }
}
