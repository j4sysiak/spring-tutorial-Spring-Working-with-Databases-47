package pl.jaceksysiak.spring.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersDao")
public class OffersDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	 
 
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
 
	public List<Offer> getOffers(){
		 
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc.query("select * from offers", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

					Offer offer = new Offer();
					
					offer.setId(rs.getInt("id"));
					offer.setName(rs.getString("name"));
					offer.setText(rs.getString("text"));
					offer.setEmail(rs.getString("email"));
					
					return offer;
			}
			
		});
	}

	
	
public Offer  getOffer(int id){
 
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {
 
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

					Offer offer = new Offer();
					
					offer.setId(rs.getInt("id"));
					offer.setName(rs.getString("name"));
					offer.setText(rs.getString("text"));
					offer.setEmail(rs.getString("email"));
					
					return offer;
			}
			
		});
	}


public boolean delete(int id){
	
	MapSqlParameterSource params = new MapSqlParameterSource("id", id);
	
	return jdbc.update("delete from offers where id=:id", params)==1;
	 
}

public boolean create(Offer offer){
	
	BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
	
	return jdbc.update("insert into offers (name, text, email) values (:name, :text, :email)", params)==1;
}

public boolean update(Offer offer){
	
	BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
	
	return jdbc.update("update offers set name=:name, email=:email, text=:text where id=:id", params)==1;
	 
}

}







































