package dha.lockrepo.core.domains;


public class TopSecretGroupBE extends TopSecretBE {

    private String name;
    private String description;

    public TopSecretGroupBE(Long id, String name, String description) {
        super();
        this.setId(id);
        this.name = name;
        this.description = description;
    }

    @Override
    protected String[] getHashCodeFields() {
        return new String[] {"name", "description"};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
