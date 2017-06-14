package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private SimpleDirectedGraph<Exhibition,DefaultEdge> grafo;
	private ArtsmiaDAO dao;
	private List<Exhibition> mostre;
	private ExhibitionIdMap exhibitionIdMap;
	private ObjectIdMap objectIdMap;
	private Exhibition top;

	public Model(){
		exhibitionIdMap = new ExhibitionIdMap();
		dao = new ArtsmiaDAO();
		objectIdMap  =  new ObjectIdMap();

		//mostre = new ArrayList<Exhibition>();

	}

	// se è maggiore il primo numero del primo %% minore del secondo del primo

	public List<Integer> getDate(){
		return dao.getDate();
	}

//	public List<Exhibition> getMostre(){
//		if(mostre ==null){
//			mostre = dao.getMostre();
//		}
//		return mostre;
//	}


	public Exhibition creaGrafo(int anno){

		this.grafo = new SimpleDirectedGraph<Exhibition,DefaultEdge>(DefaultEdge.class);
		mostre = dao.getMostrePerAnno(anno,exhibitionIdMap);
		int numopere = 0;
		for(Exhibition e: mostre){
			grafo.addVertex(e);
			dao.getOperePerMostra(e, objectIdMap);
			if(numopere<= e.getOpere().size()){
				numopere = e.getOpere().size();
				top = e;
		}}
		for(Exhibition e1: mostre){
			for(Exhibition e2: mostre){
				if(!e1.equals(e2)){
					if(e2.getInizio()>e1.getInizio() && e2.getInizio()<e1.getFine()){
						grafo.addEdge(e1, e2);
					}
				}

			}

		}


	System.out.println(grafo);
	return top;

	}

//	public Exhibition maggioriOpere(List<Exhibition> mostre2){
//		int numopere = 0;
//		for(Exhibition e: mostre){
//			if(numopere<= e.getOpere().size()){
//				top = e;
//			}
//
//		}
//		return top;
//	}


	public boolean isConnected(){
		ConnectivityInspector<Exhibition,DefaultEdge> con = new ConnectivityInspector<Exhibition,DefaultEdge>(grafo);
		return con.isGraphConnected();
	}
}
