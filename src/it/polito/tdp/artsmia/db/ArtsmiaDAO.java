package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionIdMap;
import it.polito.tdp.artsmia.model.ObjectIdMap;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {

		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {

				result.add(new ArtObject(res.getInt("object_id"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getDate(){
		String sql = "SELECT begin from exhibitions GROUP BY begin";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("begin"));

			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<Exhibition> getMostrePerAnno(int anno, ExhibitionIdMap exhibitionIdMap) {
		String sql = "SELECT * from exhibitions WHERE begin>= ? ";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Exhibition mostra = new Exhibition(res.getInt("exhibition_id"),res.getString("exhibition_department"),
						res.getString("exhibition_title"),res.getInt("begin"),res.getInt("end"));
				mostra = exhibitionIdMap.put(mostra);
				result.add(mostra);

			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		public void getOperePerMostra(Exhibition mostra, ObjectIdMap objectIdMap) {
			String sql = "SELECT eo.object_id,title From exhibition_objects eo,objects o "
					+ " WHERE eo.exhibition_id =  ? and eo.object_id = o.object_id ";

			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, mostra.getId());

				ResultSet res = st.executeQuery();

				while (res.next()) {
					ArtObject opera = new ArtObject(res.getInt("object_id"),res.getString("title"));
					opera = objectIdMap.put(opera);
					mostra.getOpere().add(opera);
				}

				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
	}

		public List<Exhibition> getMostrePerAnno2(int anno, ExhibitionIdMap exhibitionIdMap) {
			String sql = "SELECT e.exhibition_id, exhibition_department, exhibition_title, begin, end "
					+ "FROM exhibitions e, exhibition_objects eo WHERE begin= ? && eo.exhibition_id = e.exhibition_id ";
			//&& eo.exhibition_id =e.exhibition_id

			List<Exhibition> result = new ArrayList<>();

			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, anno);

				ResultSet res = st.executeQuery();

				while (res.next()) {
					Exhibition mostra = new Exhibition(res.getInt("exhibition_id"),res.getString("exhibition_department"),
							res.getString("exhibition_title"),res.getInt("begin"),res.getInt("end"));
					mostra = exhibitionIdMap.put(mostra);
					result.add(mostra);
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
