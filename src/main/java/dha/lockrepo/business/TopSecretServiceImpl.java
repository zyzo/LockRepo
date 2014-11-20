package dha.lockrepo.business;

import java.util.List;

import dha.lockrepo.core.domains.TopSecretGroupBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.dao.TopSecretDAO;

public class TopSecretServiceImpl implements TopSecretService {

    TopSecretDAO dao;

    public TopSecretServiceImpl() {
        dao = new TopSecretDAO();
    }

    @Override
    public void addTopSecretPiece(TopSecretPieceBE sPiece) {
        dao.savePiece(sPiece);
    }

    @Override
    public void addTopSecretPiece(TopSecretPieceBE sPiece, TopSecretGroupBE sGroup) {
        // TODO Auto-generated method stub

    }

    @Override
    public TopSecretPieceBE findPieceById(Long id) {
        return dao.findPieceById(id);
    }

    @Override
    public List<TopSecretPieceBE> findAll() {
        return dao.findAll();
    }


    @Override
    public TopSecretPieceBE removePieceById(Long sPieceId) {
        return dao.deletePieceById(sPieceId);
    }

    @Override
    public TopSecretPieceBE update(TopSecretPieceBE piece) {
        return dao.update(piece);
    }

    @Override
    public void removeTopSecretGroupById(Long sGroupId) {
        // TODO Auto-generated method stub

    }

}
