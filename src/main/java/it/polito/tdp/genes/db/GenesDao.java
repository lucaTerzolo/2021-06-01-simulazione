package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenti;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getAllGenes(Map<String,Genes> idMap){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!idMap.containsKey(res.getString("GeneID"))) {
					Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
						idMap.put(res.getString("GeneID"),genes);
				}
			}
			
			res.close();
			st.close();
			conn.close();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Genes> getVertici(Map<String,Genes> idMap){
		String sql="Select distinct geneID "
				+ "From genes "
				+ "where essential='essential' ";
		
		Connection conn = DBConnect.getConnection();
		List<Genes> result=new ArrayList<>();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {				
				result.add(idMap.get(res.getString("geneID")));
			}
			
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Adiacenti> getArchi(Map<String,Genes> idMap){
		String sql="Select distinct i.GeneID1 as g1, i.GeneID2 as g2, i.Expression_Corr as corr, "
				+ " g1.Chromosome as c1, g2.Chromosome as c2 "
				+ "From interactions i, genes g1, genes g2 "
				+ "where GeneID1<>GeneID2 "
				+ "and g1.GeneID=i.GeneID1 "
				+ "and g2.GeneID=i.GeneID2 "
				+ "and g1.essential='essential' "
				+ "and g2.essential=g1.essential ";
		
		Connection conn = DBConnect.getConnection();
		List<Adiacenti> result=new ArrayList<>();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {	
				if(res.getInt("c1")==res.getInt("c2")) {
					double peso=res.getDouble("corr")*2;
					result.add(new Adiacenti(idMap.get(res.getString("g1")),idMap.get(res.getString("g2")),peso));
				}else {
					double peso=Math.abs(res.getDouble("corr"));
					result.add(new Adiacenti(idMap.get(res.getString("g1")),idMap.get(res.getString("g2")),peso));
				}
			}
			
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
