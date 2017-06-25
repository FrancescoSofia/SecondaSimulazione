package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.Graphs;
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
	private List<Studente> studenti;
	private Simulatore sim;
	private List<Exhibition> mostre2;
	//private List<Exhibition> mostre3;


	public Model(){
		exhibitionIdMap = new ExhibitionIdMap();
		dao = new ArtsmiaDAO();
		objectIdMap  =  new ObjectIdMap();
		sim = new Simulatore();
		//mostre3= new ArrayList<Exhibition>();

		//mostre = new ArrayList<Exhibition>();

	}

	

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

//	public List<Studente> creaStudente(int k,int anno){
//		
//		List<Exhibition> mostre2 = new ArrayList<Exhibition>();
//		studenti = new ArrayList<Studente>();
//		for(int i=0;i<k;i++){
//			studenti.add(new Studente(i+1));
//		}
//		while(mostre2.size()==0){
//			mostre2 =dao.getMostrePerAnno2(anno, exhibitionIdMap);
//		}
//		Random random = new Random();
//		int c = random.nextInt(mostre2.size());
//		for(Studente s: studenti){
//			for(ArtObject a: mostre2.get(c).getOpere()){
//				s.getOpere().put(a.getObjectId(),a);
//				}
//
//
//		List<Exhibition> a = Graphs.neighborListOf( grafo,mostre.get(c));
//		Exhibition b = a.get((int) (Math.random()*a.size()));
//		while (Graphs.neighborListOf( grafo,b).size()!=0){
//			int nuovo =(int) (Math.random()*Graphs.neighborListOf( grafo,b).size());
//			b=Graphs.neighborListOf( grafo,b).get(nuovo);
//			for(ArtObject a2: b.getOpere()){
//				
//				s.getOpere().put(a2.getObjectId(),a2);
//				
//			}
//			}
//		}
//		Collections.sort(studenti);
//		return studenti;
//
//
//
//		}

//		Random random = new Random();
//		random.nextInt(mostre.size());


		//in base alle mostre raggiungibili creo il numero random

	public void calcolo(){

	}
	
	public List<Studente> creaStudente(int k,int anno){
		//Random random = new Random();
		mostre2 = dao.getMostrePerAnno2(anno, exhibitionIdMap);
		int c = (int) (Math.random()*(mostre2.size()));
		
		studenti = new ArrayList<Studente>();
		for(int i=0;i<k;i++){
			Studente s =new Studente(i+1);
			studenti.add(s);
//			for(ArtObject a: mostre2.get(c).getOpere()){
//				s.getOpere().put(a.getObjectId(),a);
//				}			
			
			sim.newStudente(s, mostre2.get(c));
			sim.run(this.grafo);
			
			}
		
		Collections.sort(studenti);
		return studenti;
		
		
	}
	
	public List<Studente> creaStudente2(int k, int anno){
		mostre2 = dao.getMostrePerAnno2(anno, exhibitionIdMap);
		int c = (int) (Math.random()*(mostre2.size()));
		
		studenti = new ArrayList<Studente>();
		for(int i=0;i<k;i++){
			Studente s =new Studente(i+1);
			studenti.add(s);
//			for(ArtObject a: mostre2.get(c).getOpere()){
//				s.getOpere().put(a.getObjectId(),a);
//				}						
			}
		
		sim.newStudente2(studenti, mostre2.get(c));
		sim.run(this.grafo);
		
		Collections.sort(studenti);
		return studenti;
		
	}
	
	public List<Studente> chiama(int k,int anno){
		mostre2 = dao.getMostrePerAnno2(anno, exhibitionIdMap);
		int c = (int) (Math.random()*(mostre2.size()));
		studenti = new ArrayList<Studente>();
		for(int i=0;i<k;i++){
			Studente s =new Studente(i+1);
			studenti.add(s);
			this.ricorsione(mostre2.get(c),s);
			
	}
		Collections.sort(studenti);
		return studenti;
		
	}
	
	public void ricorsione(Exhibition mostra,Studente s){
		
		for(ArtObject a: mostra.getOpere()){
			s.getOpere().put(a.getObjectId(),a);
			}
		while(grafo.outgoingEdgesOf(mostra).size()!=0){
			mostre.clear();
			for(DefaultEdge d : grafo.outgoingEdgesOf(mostra)){
			mostre.add(grafo.getEdgeTarget(d));
			}
			int c = (int) (Math.random()*mostre.size());
			System.out.println(c);
			
			this.ricorsione(mostre.get(c),s);
		}
		
		
	}
	
}
