package digytal.desktop.app.utils.report;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Expressions {

    public static <T> void with(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
    }

    public static <T> T apply(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
        return obj;
    }

    public static <T> T create(Class<T> clazz, Consumer<T> consumer) {
        try {
            T obj = clazz.newInstance();
            return apply(obj, consumer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, R> R map(T obj, Function<T, R> func) {
        return func.apply(obj);
    }

    /**
     * Executa uma operação e se houver NullPointerException retorna null.
     */
    public static <T> T safe(Supplier<T> operation) {
        return safe(operation, (T)null);
    }

    /**
     * Executa uma operação e se houver NullPointerException retorna o defaultValue.
     */
    public static <T> T safe(Supplier<T> operation, T defaultValue) {
        try {
            return operation.get();
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    /**
     * Garante que o value não seja nulo. Se value for nulo, returna o defaultValue.
     */
    public static <T> T safeValue(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static <T> T ifNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static <T> T ifPresent(T obj, Consumer<T> consumer) {
        if (obj != null) {
            consumer.accept(obj);
        }
        return obj;
    }

    public static <T> void ifNotBlank(T value, Consumer<T> callback) {
        if (value != null && !value.toString().trim().isEmpty()) {
            callback.accept(value);
        }
    }

    public static <T, R> R mapSafe(T obj, Function<T, R> func) {
        if (obj != null) {
            return func.apply(obj);
        }
        return null;
    }

}

