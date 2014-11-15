package dha.lockrepo.core.domains;


public class TopSecretPieceBE extends TopSecretBE {

    private String title;
    private String username;
    private String passwd;
    private String info;
    private Long groupId;

    public TopSecretPieceBE(String title, String username, String passwd, Long groupId) {
        this(groupId, title, username, passwd, "_");
    }

    public TopSecretPieceBE(Long groupId, String title, String username, String passwd, String info) {
        super();
        this.title = title;
        this.username = username;
        this.passwd = passwd;
        this.info = info;
        this.groupId = groupId;
        this.id = (long) this.hashCode();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
