package service;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Book;
import service.HibernateUtil;

public class BookstoreService {

	public void addBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			session.save(book);

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

	public List<Book> getBooks() {
		List<Book> books = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			books = session.createQuery("from Book").list();

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
		
		return books;
	}

}