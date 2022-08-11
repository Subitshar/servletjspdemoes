

import org.hibernate.Session;

import org.hibernate.Transaction;


public class DeleteObjectTest {

public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			Message message = session.get(Message.class, 2);

			session.delete(message);
			
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