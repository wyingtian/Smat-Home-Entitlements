package cscie97.asn4.housemate.model.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The KnowledgeGraph manages the triples.
 * 
 * @author yingtian wang
 *
 */
public class KnowledgeGraph {
	/**
	 * This is to establish the only instance of the class --GRAPH
	 */
	private static final KnowledgeGraph GRAPH = new KnowledgeGraph();
	private final String qMark = "?";

	private KnowledgeGraph() {
	}

	/**
	 * Private association for maintaining the active set of Nodes (i.e.
	 * Subjects and/or Objects). Map key is the node identifier and value is the
	 * associated Node. Node identifiers are case insensitive.
	 */
	Map<String, Node> nodeMap = new HashMap<String, Node>();
	Map<String, Predicate> predicateMap = new HashMap<String, Predicate>();
	Map<String, Triple> tripleMap = new HashMap<String, Triple>();

	/**
	 * Private association for maintaining a fast query lookup map. Map key is
	 * the query string (e.g. “Bill ? ?”), and value is a Set of matching
	 * Triples.
	 */

	Map<String, Set<Triple>> queryMapSet = new HashMap<String, Set<Triple>>();

	public void removeTriples(String triple) {
		String[] str_array = triple.split(" ");
		Triple tri =tripleMap.get(triple);
		//if the triple tried to remove does not exist, return;
		if(tri == null){
			return;
		}
//		Node obj = nodeMap.get(str_array[2]);
//		Node sub = nodeMap.get(str_array[0]);
//		Predicate pre = predicateMap.get(str_array[1]);
		List<String> query_list = generateAllQuerys(tri);

		for (String str : query_list) {

			if (queryMapSet.containsKey(str)) {
				Set<Triple> trip_set = queryMapSet.get(str);
			trip_set.remove(tripleMap.get(triple))	;

			}
		}
//		tripleMap.remove(triple);
//		nodeMap.remove(str_array[2]);
//		nodeMap.remove(str_array[0]);
//		predicateMap.remove(str_array[1]);
	}
	/**
	 * The importTriples() method supports importing a set of Triple instances
	 * into the KnowledgeGraph.
	 *
	 * @param tripleList
	 */
	public void importTriples(List<Triple> tripleList) {
		for (Triple trip : tripleList) {
			List<String> query_list = generateAllQuerys(trip);
			for (String str : query_list) {
				if (queryMapSet.containsKey(str)) {
					Set<Triple> trip_set = queryMapSet.get(str);
					trip_set.add(trip);
				} else {
					Set<Triple> trip_set = new HashSet<Triple>();
					trip_set.add(trip);
					queryMapSet.put(str, trip_set);
				}
			}
		}
	}

	/**
	 * generate all possible querys from this trip for example, Joe has_friend
	 * Bill is the input trip output should be: ["? has_friend Bill",
	 * "Joe ? Bill", "Joe has_friend ?", "? ? Bill", " Joe ? ?",
	 * "? has_friend ?", "? ? ?"]
	 * 
	 * trip is the input Triple
	 * 
	 * @param trip
	 * @return
	 */
	private List<String> generateAllQuerys(Triple trip) {

		String possiQuery1 = trip.getSubIdentifier() + " " + qMark + " "
				+ trip.getObjIdentifier();
		String possiQuery2 = qMark + " " + trip.getPreIdentifier() + " "
				+ trip.getObjIdentifier();
		String possiQuery3 = trip.getSubIdentifier() + " "
				+ trip.getPreIdentifier() + " " + qMark;
		String possiQuery4 = qMark + " " + trip.getPreIdentifier() + " "
				+ qMark;
		String possiQuery5 = trip.getSubIdentifier() + " " + qMark + " "
				+ qMark;
		String possiQuery6 = qMark + " " + qMark + " "
				+ trip.getObjIdentifier();

		List<String> queries = new ArrayList<String>();
		queries.add(possiQuery1);
		queries.add(possiQuery2);
		queries.add(possiQuery3);
		queries.add(possiQuery4);
		queries.add(possiQuery5);
		queries.add(possiQuery6);
		return queries;
	}

	/**
	 * This is the functino to find the right query result.
	 * 
	 * @param query
	 * @return
	 */
	public Set<Triple> executeQuery(Triple query) {

		String que = query.getIdentifier();
		if (queryMapSet.containsKey(que)) {
			return queryMapSet.get(que);
		} else {
			return null;
		}
	}

	/**
	 * This method returns a reference to the single static instance of the
	 * KnowledgeGraph.
	 */
	public static KnowledgeGraph getInstance() {
		return GRAPH;
	}

	/**
	 * This method get the node from the node map
	 * 
	 * @param identifier
	 * @return
	 */
	public Node getNode(String identifier) {

		if (nodeMap.containsKey(identifier)) {
			return nodeMap.get(identifier);
		} else {
			Node node = new Node(identifier);
			nodeMap.put(identifier, node);
			return node;
		}
	}

	/**
	 * This method get the predicate from the node map
	 * 
	 * @param identifier
	 * @return
	 */
	public Predicate getPredicate(String identifier) {
		
		if (predicateMap.containsKey(identifier)) {
			return predicateMap.get(identifier);
		} else {
			Predicate predicate = new Predicate(identifier);
			predicateMap.put(identifier, predicate);
			return predicate;
		}
	}

	/**
	 *
	 * @param sub
	 * @param pre
	 * @param obj
	 * @return
	 */
	public Triple getTriple(Node sub, Predicate pre, Node obj) {
		String identifier = sub.getIdentifier() + " " + pre.getIdentifier()
				+ " " + obj.getIdentifier();
		if (tripleMap.containsKey(identifier)) {
			return tripleMap.get(identifier);
		} else {
			Triple triple = new Triple(sub, pre, obj);
			tripleMap.put(identifier, triple);
			return triple;
		}
	}
}
