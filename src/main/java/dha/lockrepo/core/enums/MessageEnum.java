package dha.lockrepo.core.enums;

public enum MessageEnum{
    EXC_ACCESS_RIGHT_SEC("exc.accessright.security"),
    TEST_RESOURCE_LOADER("test.message.DO_NOT_DELETE_OR_MODIFY");
    
    MessageEnum(String key) {
        this.key = key;
    }
    private String key;
    
    

    public String getKey() {
        return this.key;
    }
 
}
