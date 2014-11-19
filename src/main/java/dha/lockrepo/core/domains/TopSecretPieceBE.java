package dha.lockrepo.core.domains;

import java.util.Optional;

public class TopSecretPieceBE extends TopSecretBE {

    private String title;
    private String username;
    private String passwd;
    private Optional<String> info;
    private Long groupId;

    public TopSecretPieceBE(Long groupId, String title, String username, String passwd, String info) {
        super();
        this.title = title;
        this.username = username;
        this.passwd = passwd;
        this.groupId = groupId;
        this.info = Optional.ofNullable(info);
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public Optional<String> getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = Optional.of(info);
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    protected String[] getHashCodeFields() {
        return new String[] {"title", "groupId"};
    }
}
