import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class BaseDOD {
  private Session session;

  public BaseDOD(Session session) {
    this.session = session;
  }

  public void loadObject(Object target) {
    session.beginTransaction();
    session.persist(target);
    session.getTransaction().commit();
  }

  public void loadObjects(List<Object> targets) {
    session.beginTransaction();
    for (Object target : targets) {
      session.persist(target);
    }

    session.getTransaction().commit();
  }

  public ArrayList<Frame> getAllFrames() {
    session.beginTransaction();
    ArrayList<Frame> result = (ArrayList<Frame>) session.createQuery("FROM Frame", Frame.class).getResultList();
    session.getTransaction().commit();
    return result;
  }

  public ArrayList<Frame> getFramesFromOneStorageAndVendor(Storage storage, Vendor vendor) {
    ArrayList<Frame> result = (ArrayList<Frame>) session
        .createQuery("FROM Frame WHERE storage.id = :storage_id and vendor.id = :vendor_id", Frame.class)
        .setParameter("storage_id", storage.getId())
        .setParameter("vendor_id", vendor.getId())
        .getResultList();

    return result;
  }

  public Storage findStorageById(int id) {
    return session.createQuery("FROM Storage WHERE id = :id", Storage.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Vendor findVendorById(int id) {
    return session.createQuery("FROM Vendor WHERE id = :id", Vendor.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public ArrayList<Object> getAllObjects() {
    ArrayList<Object> result = new ArrayList<>();
    result.addAll(session.createQuery("FROM Vendor", Vendor.class).getResultList());
    result.addAll(session.createQuery("FROM Storage", Storage.class).getResultList());
    result.addAll(session.createQuery("FROM Frame", Frame.class).getResultList());

    return result;
  }
}
