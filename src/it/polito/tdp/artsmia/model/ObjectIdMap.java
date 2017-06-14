package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

public class ObjectIdMap {

	private Map<Integer,ArtObject> map ;

	public ObjectIdMap() {
		map = new HashMap<>() ;
	}

	public ArtObject get(Integer id) {
		return map.get(id) ;
	}


	public ArtObject put(ArtObject object) {
		ArtObject old = map.get(object.getObjectId()) ;
		if(old==null) {
			map.put(object.getObjectId(), object) ;
			return object ;
		} else {
			return old ;
		}
	}

}
