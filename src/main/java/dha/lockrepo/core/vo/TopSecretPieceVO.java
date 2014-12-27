package dha.lockrepo.core.vo;

import dha.lockrepo.core.domains.TopSecretPieceBE;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for displaying TopSecretPieceBE in graphic mode
 */
public class TopSecretPieceVO extends TopSecretPieceBE {

    public TopSecretPieceVO(Long groupId, String title, String username, String passwd, String info) {
        super(groupId, title, username, passwd, info);
    }

    public TopSecretPieceVO(TopSecretPieceBE data) {
        this(data.getGroupId(),
                data.getTitle(),
                data.getUsername(),
                data.getPasswd(),
                data.getInfo().get());
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public static List<TopSecretPieceVO> convert(List<TopSecretPieceBE> list) {
        List<TopSecretPieceVO> convertedList = new ArrayList<TopSecretPieceVO>();
        list.forEach(e -> convertedList.add(new TopSecretPieceVO(e)));
        return convertedList;
    }
}
