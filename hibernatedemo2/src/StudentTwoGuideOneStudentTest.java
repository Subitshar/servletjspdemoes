import org.hibernate.Session;

import org.hibernate.Transaction;


public class StudentTwoGuideOneStudentTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			Guide guide1 = new Guide("2000XYZ003", "Robert Williams", 2000);
			Guide guide2 = new Guide("2000PQR004", "Ian Botham", 4000);
			Student student1 = new Student("SR001", "John Smith", guide2, 23);
			
			session.persist(guide1);
			session.persist(student1);
			
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