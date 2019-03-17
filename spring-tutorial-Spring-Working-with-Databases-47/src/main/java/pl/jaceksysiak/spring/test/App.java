package pl.jaceksysiak.spring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("pl/jaceksysiak/spring/test/beans/beans.xml");
 
		OffersDAO offersDao  = (OffersDAO)context.getBean("offersDao");
		
		try{
			//insert offer
			Offer offer1 = new Offer("ula", "p9@wp.pl", "uuuuuuuuuuuuuuuuu");
			Offer offer2 = new Offer("ola", "p10@wp.pl", "oooooooooooooooooooo");
			Offer offer3 = new Offer("kola", "p11@wp.pl", "kkkkkkkkkk");
			
			if(offersDao.create(offer1)){
				System.out.println("offer1 created.");
			}
			
			if(offersDao.create(offer2)){
				System.out.println("offer2 created.");
			}
			
			if(offersDao.create(offer3)){
				System.out.println("offer3 created.");
			}
			
			
			//delete record
			//offersDao.delete(4);
			
			//updaterecord
			Offer upd_offer= new Offer(1, "to updated name ......", "to updated email ......", "to updated text ......");
			if(offersDao.update(upd_offer)){
			  System.out.println("Object id=1 updated.");	
			} else {
				System.out.println("Cannot update object.");
			}
			
			
		List<Offer> offers = offersDao.getOffers();
		
		for(Offer offer : offers){
			
			 System.out.println(offer);
		} 
		
		Offer offer = offersDao.getOffer(1);
		System.out.println("Should be record with id=1: "+ offer);
		
			} catch(CannotGetJdbcConnectionException ex){
				System.out.println("Cannot get database connection."); 
			}
			  catch(DataAccessException ex){
				System.out.println(ex.getMessage());
				System.out.println(ex.getClass());
			}
	 
		((ClassPathXmlApplicationContext)context).close();

	}

}




























































