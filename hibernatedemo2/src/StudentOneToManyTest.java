import org.hibernate.Session;

import org.hibernate.Transaction;



public class StudentOneToManyTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			Guide guide = new Guide("2000XYZ003", "Robert Williams", 2000);
			Student student1 = new Student("SR001", "John Smith", guide, 23);
			Student student2 = new Student("SR002", "Amy Jackson", guide, 25);
			
			guide.getStudents().add(student1);
			guide.getStudents().add(student2);
			
			session.persist(guide);
			
			txn.commit();

		} catch (Exception ex) {
			if (txn != null) {
				txn.rollback();
			}
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}
	
}