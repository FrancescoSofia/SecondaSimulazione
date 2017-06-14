package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

public class ExhibitionIdMap {

		private Map<Integer,Exhibition> map ;

		public ExhibitionIdMap() {
			map = new HashMap<>() ;
		}

		public Exhibition get(Integer id) {
			return map.get(id) ;
		}


		public Exhibition put(Exhibition exhibition) {
			Exhibition old = map.get(exhibition.getId()) ;
			if(old==null) {
				map.put(exhibition.getId(), exhibition) ;
				return exhibition ;
			} else {
				return old ;
			}
		}


}
