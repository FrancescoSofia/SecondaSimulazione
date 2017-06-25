package it.polito.tdp.artsmia.db;


import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionIdMap;
import it.polito.tdp.artsmia.model.ObjectIdMap;

public class TestArtsmiaDAO {



	

	public static void main(String[] args) {
		ExhibitionIdMap exhibitionIdMap = new ExhibitionIdMap();
		 ObjectIdMap objectIdMap= new ObjectIdMap();

		ArtsmiaDAO dao = new ArtsmiaDAO() ;
//		Exhibition e = new Exhibition(10, null, null, 0, 0);
//		dao.getOperePerMostra(e, objectIdMap);
		System.out.println(dao.getMostrePerAnno2(1913, exhibitionIdMap));
		

	}

}
