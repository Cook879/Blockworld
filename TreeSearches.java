import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Main class of the coursework
 * 
 * @author Richard Cook
 * @version 0.8 (2014-11-26)
 */
public class TreeSearches {
	
	// Need count global for ids - might as well make use in all methods
	int count;
	
	public static void main(String[] args) {		
		try {
			TreeSearches ts = new TreeSearches();
			
			// Get a new output file
			File file = new File("output.txt");
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// List of all puzzles to try 
			ArrayList<Puzzle> problems = new ArrayList<Puzzle>();
			problems.add(new Puzzle(4, 0, 3));
			//problems.add(new Puzzle(5, 0, 3));
			/*problems.add(new Puzzle(6, 0, 3));
			problems.add(new Puzzle(7, 0, 3));
			problems.add(new Puzzle(4, 1, 3));
			problems.add(new Puzzle(4, 1, 3));
			problems.add(new Puzzle(4, 1, 3));
			problems.add(new Puzzle(4, 2, 3));
			problems.add(new Puzzle(4, 2, 3));
			problems.add(new Puzzle(4, 2, 3));
			problems.add(new Puzzle(4, 3, 3));
			problems.add(new Puzzle(4, 3, 3));
			problems.add(new Puzzle(4, 3, 3));
			problems.add(new Puzzle(5, 1, 3));
			problems.add(new Puzzle(5, 1, 3));
			problems.add(new Puzzle(5, 1, 3));
			problems.add(new Puzzle(5, 2, 3));
			problems.add(new Puzzle(5, 2, 3));
			problems.add(new Puzzle(5, 2, 3));
			problems.add(new Puzzle(5, 3, 3));
			problems.add(new Puzzle(5, 3, 3));
			problems.add(new Puzzle(5, 3, 3));
			problems.add(new Puzzle(6, 1, 3));
			problems.add(new Puzzle(6, 1, 3));
			problems.add(new Puzzle(6, 1, 3));
			problems.add(new Puzzle(6, 2, 3));
			problems.add(new Puzzle(6, 2, 3));
			problems.add(new Puzzle(6, 2, 3));
			problems.add(new Puzzle(6, 3, 3));
			problems.add(new Puzzle(6, 3, 3));
			problems.add(new Puzzle(6, 3, 3));			
			problems.add(new Puzzle(7, 1, 3));
			problems.add(new Puzzle(7, 1, 3));
			problems.add(new Puzzle(7, 1, 3));
			problems.add(new Puzzle(7, 2, 3));
			problems.add(new Puzzle(7, 2, 3));
			problems.add(new Puzzle(7, 2, 3));
			problems.add(new Puzzle(7, 3, 3));
			problems.add(new Puzzle(7, 3, 3));
			problems.add(new Puzzle(7, 3, 3));
			
			problems.add(new Puzzle(4, 0, 4));
			problems.add(new Puzzle(5, 0, 4));
			problems.add(new Puzzle(5, 0, 5));
			problems.add(new Puzzle(6, 0, 4));
			problems.add(new Puzzle(6, 0, 5));
			problems.add(new Puzzle(7, 0, 4));
			problems.add(new Puzzle(7, 0, 5));
			problems.add(new Puzzle(4, 1, 4));
			problems.add(new Puzzle(4, 1, 4));
			problems.add(new Puzzle(4, 1, 4));
			problems.add(new Puzzle(5, 1, 5));
			problems.add(new Puzzle(5, 1, 5));
			problems.add(new Puzzle(5, 1, 5));
			problems.add(new Puzzle(5, 2, 5));
			problems.add(new Puzzle(5, 2, 5));
			problems.add(new Puzzle(5, 2, 5));*/
			
			// For each of the puzzles, let's use each of the methods
			for( Puzzle problem : problems ) {
				
				bw.write("problem is:" + problem.goalState.puzzle.toString() + "\n");
				
				bw.write("aStarMan ");
				List<String> result = ts.aStarMan(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();
				
				bw.write("hsMan ");
				result = ts.hsMan(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();					
				
				bw.write("hsPlace ");
				result = ts.hsPlace(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();	

				bw.write("ids ");
				result = ts.ids(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();		
				
				// Repeat three times so we can average
				for( int i = 0; i < 3; i++ ) {
					bw.write("dfsGraph ");
					result = ts.dfsGraph(problem);
					if( result == null )
						bw.write("No solution for problem ");
					else {
						bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
					}
					bw.write("\n");
					bw.flush();	
				}
						
				// Repeat for average
				for( int i = 0; i < 3; i++ ) {
					bw.write("dfs ");
					result = ts.dfs(problem);
					if( result == null )
						bw.write("No solution for problem ");
					else {
						bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
					}
					bw.write("\n");
					bw.flush();	
				}
								
				bw.write("bfsGraph ");
				result = ts.bfsGraph(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();	
				
				bw.write("bfs");
				result = ts.bfs(problem);
				if( result == null )
					bw.write("No solution for problem ");
				else {
					bw.write("node count and solution:" + result  +  " depth: " + (result.size()-1));
				}
				bw.write("\n");
				bw.flush();	
			}
			
			bw.close();
		}
		catch ( Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Breadth-first search method
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> bfs(Puzzle problem) throws Exception {
		Queue<Node<PuzzleState>> fringe = new LinkedList<Node<PuzzleState>>();
		Node<PuzzleState> root = problem.initialState();
		fringe.add(root);
		
		count = 0;
		
		while( !fringe.isEmpty() ) {
			Node<PuzzleState> n = fringe.poll();
			
			if( problem.isGoalState(n.state) ) {
				return solution(n, count++);
			}

			fringe.addAll(expand(n));
			count++;
		}
		
		return null;	
	}
	
	/**
	 * Breadth-first graph search method.
	 * Same as bfs() but with seen Set.
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> bfsGraph(Puzzle problem) throws Exception {
		Queue<Node<PuzzleState>> fringe = new LinkedList<Node<PuzzleState>>();
		Node<PuzzleState> root = problem.initialState();
		fringe.add(root);
		
		Set<Map<Character, Integer>> seen = new HashSet<Map<Character, Integer>>();
		count = 0;		
		
		while( !fringe.isEmpty() ) {
			Node<PuzzleState> n = fringe.poll();
						
			if( notSeen(n.state, seen) ) {

				if( problem.isGoalState(n.state) ) {
					return solution(n, count);
				}

				seen.add(n.state.map);
				
				fringe.addAll(expand(n));
				count++;
			}
		}
		
		return null;	
	}
	
	/**
	 * Depth-first search method
	 * Same as bfs() but with Stack instead of Queue.
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> dfs(Puzzle problem)throws Exception {
		Stack<Node<PuzzleState>> fringe = new Stack<Node<PuzzleState>>();
		Node<PuzzleState> root =  problem.initialState();
		fringe.push(root);
		
		count = 0;
		
		while( !fringe.isEmpty() ) {
			Node<PuzzleState> n = fringe.pop();
			
			if( problem.isGoalState(n.state) ) {
				return solution(n, count);
			}
			
			fringe.addAll(expandRandom(n));
			count++;
		}
		
		return null;	
	}
	
	/**
	 * Depth-first graph search method
	 * Same as bfsGraph() but with a Stack instead of a Queue. 
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */	
	public List<String> dfsGraph(Puzzle problem)throws Exception {
		Stack<Node<PuzzleState>> fringe = new Stack<Node<PuzzleState>>();
		Node<PuzzleState> root =  problem.initialState();
		fringe.push(root);
		
		Set<Map<Character, Integer>> seen = new HashSet<Map<Character, Integer>>();
		count = 0;
		
		while( !fringe.isEmpty() ) {
			Node<PuzzleState> n = fringe.pop();
			
			if( notSeen(n.state, seen) ) {
			
				if( problem.isGoalState(n.state) ) {
					return solution(n, count);
				}
				seen.add(n.state.map);

				fringe.addAll(expandRandom(n));
				count++;
			}
		}
		return null;	
	}
	
	/**
	 * Takes a PuzzleState and a Set of seen states and checks if the node has been seen
	 * 
	 * @param PuzzleState n
	 * @param Set<Map<Character, Integer>> seen
	 * @return boolean
	 * @throws Exception
	 */
	public boolean notSeen(PuzzleState n, Set<Map<Character, Integer>> seen) throws Exception{
		for(Map<Character, Integer> m : seen) {
			if( PuzzleState.mapEquals(n.map, m) )
				return false;
		}
		return true;
	}

	/**
	 * Iterative-deepening search
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> ids(Puzzle problem) throws Exception{
		List<String> result = new ArrayList<String>();
		
		result.add("cutoff");
		int depth = 0;
		
		// Hacky but working
		while( result.get(0) == "cutoff" ) {
			result = dls(problem, depth);
			depth++;
		}
		return result;
	}
	
	/**
	 * Depth-limiting search
	 * 
	 * @param Puzzle problem
	 * @param int limit
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> dls(Puzzle problem, int limit) throws Exception {
		Node<PuzzleState> root = problem.initialState();
		count = 0;
		return dlsRec(root, limit);
	}
	
	/**
	 * Iterative-deepening search recursive method
	 * 
	 * @param Node<PuzzleState> n
	 * @param int limit
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> dlsRec(Node<PuzzleState> n, int limit) throws Exception{
		count++;
		boolean cutoff = false;
		
		// Goal == yay! == end
		if( n.state.puzzle.isGoalState(n.state) ) {
			return solution(n, count);
		} else if( n.depth == limit ) {
			// Limit == boo == up the depth
			List<String> fail = new ArrayList<String>();
			fail.add("cutoff");
			return fail;
		} else {
			// Expand and recurse
			for(Node<PuzzleState> suc : expand(n) ) {
				List<String> result = dlsRec(suc, limit);
				
				if( result != null && result.get(0) == "cutoff" ) {
					cutoff = true;
				} else if( result != null){
					return result;
				}
			}
		}
		
		// Pass up the cutoff
		if( cutoff == true) {
			List<String> fail =  new ArrayList<String>();
			fail.add("cutoff");
			return fail; 
		} else {
			// No solution
			return null;
		}
	}	
	
	/**
	 * Heuristic search method
	 * Same as bfsGraph() but takes a custom Comparator to sort the fringe by
	 * 
	 * @param Puzzle problem
	 * @param Comparator<Node<PuzzleState>> heuristic
	 * @return List<String> solution
	 * @throws Exception
	 */	
	public List<String> hs(Puzzle problem, Comparator<Node<PuzzleState>> heuristic) throws Exception {
		LinkedList<Node<PuzzleState>> fringe = new LinkedList<Node<PuzzleState>>();
		Node<PuzzleState> root = problem.initialState();
		fringe.add(root);
		
		Set<Map<Character, Integer>> seen = new HashSet<Map<Character, Integer>>();
		count = 0;
		
		while( !fringe.isEmpty() ) {
			Node<PuzzleState> n = fringe.pop();
			
			if( notSeen(n.state, seen) ) {
				
				if( problem.isGoalState(n.state) ) {
					return solution(n, count);
				}
				seen.add(n.state.map);
				
				fringe.addAll(expand(n));	
				count++;
				
				Collections.sort(fringe, heuristic);
			}
		}
		
		return null;	
	}
	
	/**
	 * Greedy heuristic search method using Manhattan Distance
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */	
	public List<String> hsMan(Puzzle problem) throws Exception{
		return hs(problem, new ManhattenHeuristicComparator());
	}

	/**
	 * Greedy heuristic search method using Manhattan Distance
	 * 
	 * Based off of Wikipedia psuedo-code (https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode)
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */
	public List<String> aStarMan(Puzzle problem) throws Exception{
		// Create a closed set and an open queue
		Set<Node<PuzzleState>> closed = new HashSet<Node<PuzzleState>>();
		LinkedList<Node<PuzzleState>> open = new LinkedList<Node<PuzzleState>>();
		
		Node<PuzzleState> startState = problem.initialState();
		PuzzleState goalState = problem.goalState;
		open.add(startState);
				
		NodeCostSort ncs = new NodeCostSort();
		count = 0;
		
		while( !open.isEmpty() ) {
			// Sort by NodeCostSort()
			Collections.sort(open, ncs);
			Node<PuzzleState> n = open.pop();
			
			if( problem.isGoalState(n.state) )
				return solution(n, count);
			
			closed.add(n);
			
			for( Node<PuzzleState> m : expand(n) ) {
				// Check not in closed
				if( TreeSearches.iterableContainsMap(m, closed) )
					continue;
				
				int tentativeCost = n.pathCost + m.state.calculateManhattanDistance(goalState);
				
				// If not in open || tentativeCost cheaper than current cost
				if( !TreeSearches.iterableContainsMap(m, open) || tentativeCost < m.pathCost ) {
					
					// Update and (if not in the list) add to open
					m.pathCost = tentativeCost;
					if( !TreeSearches.iterableContainsMap(m, open) )
						open.add(m);
				}
			}
			count++;
		}
		
		return null;
	}
	
	/**
	 * Loops through an Iterable looking for a matching node
	 * 
	 * @param Node<PuzzleState> n
	 * @param Iterable<Node<PuzzleState>> itr
	 * @return boolean
	 */
	public static boolean iterableContainsMap(Node<PuzzleState> n, Iterable<Node<PuzzleState>> itr) {
		for( Node<PuzzleState> m : itr ) {
			if( PuzzleState.mapEquals(m.state.map, n.state.map) )
				return true;
		}
		return false;
	}
	
	/**
	 * Greedy heuristic search method using a Place distance
	 * 
	 * @param Puzzle problem
	 * @return List<String> solution
	 * @throws Exception
	 */	
	public List<String> hsPlace(Puzzle problem) throws Exception{
		return hs(problem, new PlaceHeuristicComparator());	
	}

	/**
	 * Takes a solution node and iterates back to get the solution
	 * 
	 * @param Node<PuzzleState> solution node
	 * @param int count
	 * @return List<String> solution
	 */
	public List<String> solution(Node<PuzzleState> n, int count) {
		List<String> solution = new ArrayList<String>();
		
		// While the node has a parent, let's get the action
		while( n.parent != null ) {
			solution.add(n.action);
			n = n.parent;
		}
		
		// Send back the number of nodes traversed as well 
		// Hacky but gets the job done
		solution.add(Integer.toString(count));
		
		// Reverse list so actions are in order (and count is first)
		Collections.reverse(solution);
		
		return solution;
	}
	
	/**
	 * Expands a node
	 * 
	 * @param Node<PuzzleState> n
	 * @return List<Node<PuzzleState>> children on n
	 * @throws Exception
	 */
	public List<Node<PuzzleState>> expand(Node<PuzzleState> n) throws Exception{
		List<Node<PuzzleState>> results = new ArrayList<Node<PuzzleState>>();

		char[] actions = {'l','r','u','d'};
	
		for( char c : actions ) {
			// Check the move is valid
			if( PuzzleState.canTakeAction(n.state, c) ) {	
				// Create the PuzzleState
				PuzzleState p = new PuzzleState(n.state, c);
				
				// Check it isn't the same as it's grandparent
				if( n.parent == null || !PuzzleState.mapEquals(n.parent.state.map, p.map))
					results.add(new Node<PuzzleState>(p, n, Character.toString(c), n.pathCost+1, n.depth+1));			
			}
		}
		
		return results;
	}
	
	/**
	 * Expands a node in a random order
	 * 
	 * @param Node<PuzzleState> n
	 * @return List<Node<PuzzleState>> children on n
	 * @throws Exception
	 */
	public List<Node<PuzzleState>> expandRandom(Node<PuzzleState> n) throws Exception{
		List<Node<PuzzleState>> results = expand(n);
	
		Collections.shuffle(results);
		
		return results;
	}

}

/**
 * Custom comparators 
 * 
 * @author Richard Cook
 * @version 1.0 (2014-11-26)
 */
class ManhattenHeuristicComparator implements Comparator<Node<PuzzleState>> {

	@Override
	public int compare(Node<PuzzleState> arg0, Node<PuzzleState> arg1) {
		PuzzleState goal = arg0.state.puzzle.goalState;
		
		int man0 = arg0.state.calculateManhattanDistance(goal);
		int man1 = arg1.state.calculateManhattanDistance(goal);
		
		if( man0 < man1 )
			return -1;
		else if( man0 == man1 )
			return 0;
		else
			return 1;
	}
	
}

class PlaceHeuristicComparator implements Comparator<Node<PuzzleState>> {

	@Override
	public int compare(Node<PuzzleState> arg0, Node<PuzzleState> arg1) {
		PuzzleState goal = arg0.state.puzzle.goalState;
		
		int man0 = arg0.state.calculatePlaces(goal);
		int man1 = arg1.state.calculatePlaces(goal);
		
		if( man0 > man1 )
			return -1;
		else if( man0 == man1 )
			return 0;
		else
			return 1;
	}	
}

class NodeCostSort implements Comparator<Node<PuzzleState>> {

	@Override
	public int compare(Node<PuzzleState> arg0, Node<PuzzleState> arg1) {
		int cost0 = arg0.pathCost;
		int cost1 = arg1.pathCost;
		
		if( cost0 < cost1 )
			return -1;
		else if( cost0 == cost1 )
			return 0;
		else
			return 1;
	}
}