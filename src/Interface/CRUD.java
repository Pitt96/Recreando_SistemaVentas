
package Interface;

import java.util.List;

public interface CRUD<C> {
    //C de clase cualquiera
    public boolean create(C clase);
    public List read();
    public C read(int id);
    public boolean update(C clase);
    public boolean delete(int id);
}
