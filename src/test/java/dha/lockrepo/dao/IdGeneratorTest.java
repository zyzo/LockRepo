package dha.lockrepo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IdGeneratorTest {

    IdGenerator idGen = new IdGenerator();

    @Test
    public void getKey() {
        System.out.println(idGen.getPieceId());
        System.out.println(idGen.getGroupId());
    }

    @Test
    public void setKey() {
        Long prevId = idGen.getPieceId();
        idGen.incrementPieceId(30);
        assertTrue("Increment failed", 30 + prevId == idGen
                .getPieceId());
        Long prevGroupId = idGen.getGroupId();
        idGen.incrementGroupId(10);
        assertTrue("Increment failed", 10 + prevGroupId == idGen
                .getGroupId());
    }
}
