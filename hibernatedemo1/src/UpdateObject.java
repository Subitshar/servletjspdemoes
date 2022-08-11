

import org.hibernate.Session;

import org.hibernate.Transaction;



public class UpdateObject {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			Message message = session.get(Message.class, 2);

			System.out.println(message);
			
			message.setText("modified text for object 2");

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