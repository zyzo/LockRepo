package dha.lockrepo.core.domains;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TopSecretBE {

    private Long id;

    protected abstract String[] getHashCodeFields();

    @Override
    public int hashCode() {
        int res = 0;
        for ( Field field : this.getClass().getDeclaredFields() ) {
            if (Modifier.isStatic(field.getModifiers()) || !this.isHashCodeFields(field.getName()))
                continue;
            try {
                Object obj = this.getClass().getMethod(constructGetterMethodName(field), new Class[0]).invoke(
                        this, new Object[0]);
                res += obj.hashCode();
            } catch (Exception e) {
                throw new RuntimeException("get hashcode failed for the class "
                        + this.getClass().getCanonicalName(), e);
            }
        }
        return res;
    }

    private String constructGetterMethodName(Field field) {
        return "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
    }

    private boolean isHashCodeFields(String fieldName) {
        for ( String s : getHashCodeFields() ) {
            if (s.equals(fieldName))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object that) {
        return this.hashCode() == that.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String fields = "";
        String prefix = "";
        for ( Field field : getAllFields(new ArrayList<Field>(), this.getClass()) ) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;
            else
                try {
                    Object obj = this.getClass().getMethod(constructGetterMethodName(field), new Class[0]).invoke(this,
                            new Object[0]);
                    fields += prefix + field.getName() + "=>" + obj;
                    prefix = ", ";
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
        }
        return "[" + this.getClass().getSimpleName() + " : "+ fields + "]";
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        return fields;
    }

    public static void main(String[] args) {
        System.out.println(new TopSecretGroupBE(0L, "asd", "Asd"));
        System.out.println(new TopSecretPieceBE(0L, "asd", "Asd", "asd", "a"));
    }
}
