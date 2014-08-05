package dha.lockrepo.core.enums;

public enum MessageEnum{
    EXC_ACCESS_RIGHT_SEC("exc.accessright.security"), ;
    
    MessageEnum(String key) {
        this.key = key;
    }
    private String key;
    
    

    public String getKey() {
        return this.key;
    }
 
}
