package dha.lockrepo.core.domains;

import dha.lockrepo.core.utils.IdGenerator;

public class SecurePiece {

    private Long   id;
    private String title;
    private String username;
    private String passwd;
    private String info;

    public SecurePiece(String title, String username, String passwd) {
        this.id = IdGenerator.generateSecurePieceId();
        this.title = title;
        this.username = username;
        this.passwd = passwd;
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

}
