package it.polito.tdp.artsmia.db;


import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ObjectIdMap;

public class TestArtsmiaDAO {



	public static void main(String[] args) {

		 ObjectIdMap objectIdMap= new ObjectIdMap();

		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		Exhibition e = new Exhibition(10, null, null, 0, 0);
		dao.getOperePerMostra(e, objectIdMap);

	}

}
