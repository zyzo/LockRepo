package dha.lockrepo.core.domains;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public abstract class TopSecretBE {

    protected Long id;

    @Override
    public int hashCode() {
        int res = 0;
        for ( Field field : this.getClass().getDeclaredFields() ) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;
            try {
                Object obj = this.getClass().getMethod(constructGetterMethodName(field), new Class[0]).invoke(
                        this, new Object[0]);
                res += obj.hashCode();
            } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException e) {
                throw new RuntimeException("get hashcode failed for the class "
                        + this.getClass().getCanonicalName(), e);
            }
        }
        return res;
    }

    private String constructGetterMethodName(Field field) {
        return "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
    }

    @Override
    public boolean equals(Object that) {
        return this.hashCode() == that.hashCode();
    }
}
