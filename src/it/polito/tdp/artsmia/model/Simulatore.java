package it.polito.tdp.artsmia.model;

import java.util.*;
import java.util.PriorityQueue;


import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Simulatore {
	
	
	private PriorityQueue<Event> queue;
	private List<Exhibition> mostre;
	//private List<Exhibition> mostre;

	public Simulatore(){
		this.queue = new PriorityQueue<>();
		mostre = new ArrayList<Exhibition>();
	}

	public void newStudente(Studente s,Exhibition mostra){
	//Random random = new Random();
		Event e = new Event (s,mostra);
		queue.add(e);
//		for(Studente s: studenti){
//			for(ArtObject a: mostre2.get(c).getOpere()){
//				s.getOpere().put(a.getObjectId(),a);
//				}
		
	}
	
	public void newStudente2(List<Studente> studenti,Exhibition mostra){
		for(Studente s : studenti){
			Event e = new Event (s,mostra);
			queue.add(e);
		}
		
	}
		
	
	
	public void run(SimpleDirectedGraph<Exhibition, DefaultEdge> grafo){
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			for(ArtObject a:e.getMostra().getOpere()){
				e.getStudente().getOpere().put(a.getObjectId(),a);
			}
			while(grafo.outgoingEdgesOf(e.getMostra()).size()!=0){
				mostre.clear();
				for(DefaultEdge d : grafo.outgoingEdgesOf(e.getMostra())){
				mostre.add(grafo.getEdgeTarget(d));
				}
				int c = (int) (Math.random()*mostre.size());
				System.out.println(c);
				Event e2 = new Event (e.getStudente(),mostre.get(c));
				queue.add(e2);
			}
		}
	}
}
