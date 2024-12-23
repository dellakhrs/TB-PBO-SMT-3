import java.util.List;

public interface CRUDService<T> {
    void create(T obj);
    List<T> read();
    void update(int id, T obj);
    void delete(int id);
}