package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Adiacenza;
import it.polito.tdp.seriea.model.Partita;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons(Map<Integer, Season> idMapSeason) {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Season s =new Season(res.getInt("season"), res.getString("description"));
				result.add(s);
				idMapSeason.put(s.getSeason(), s);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Adiacenza> getAdiacenti(Map<Integer, Season> idMapSeason){
		/*
		 * Il ragionamento è che sicuramente ogni squadra giocherà in casa almeno una volta 
		 * nella stagione contando tutte le squadre che giocano almeno una volta in casa trovo 
		 * le coppie che mi servono 
		 */
		
		String sql="SELECT m1.Season as s1, m2.Season as s2, COUNT(DISTINCT(m1.HomeTeam)) AS peso " + 
				"FROM matches m1, matches m2 " + 
				"WHERE m1.Season<m2.Season AND m1.HomeTeam=m2.HomeTeam " + 
				"GROUP BY m1.Season, m2.Season ";
		
		List<Adiacenza> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Season s1 = idMapSeason.get(res.getInt("s1"));
				Season s2 = idMapSeason.get(res.getInt("s2"));
				if(s1 != null && s2 != null)
					result.add(new Adiacenza(s1, s2, res.getInt("peso")));
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getSquadreBySeason(Season scelta){
		String sql = "SELECT distinct m1.HomeTeam as team " + 
				"FROM matches as m1 " + 
				"WHERE m1.Season=? ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, scelta.getSeason());
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("team"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Partita> getPartiteBySeason(Season scelta){
		String sql = "SELECT m1.Date as data, m1.HomeTeam as casa, m1.AwayTeam as ospite, m1.FTHG, m1.FTAG " + 
				"FROM matches m1 " + 
				"WHERE m1.Season=? ";
		List<Partita> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, scelta.getSeason());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Partita(res.getDate("data").toLocalDate(),
									   res.getString("casa"),
									   res.getString("ospite"),
									   res.getInt("FTHG"),
									   res.getInt("FTAG")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}

