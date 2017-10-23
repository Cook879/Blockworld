import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Puzzle class stores details about the Blocksworld problem
 * 
 * @author Richard Cook
 * @version 0.9 (2014-11-26)
 */
public class Puzzle {
	// Size of the grid
	int N;
	
	// To save constantly creating it
	PuzzleState goalState;
	
	// For puzzles with obstacles.
	int obstacleNum;
	Map<Character, Integer> obsMap;
	
	int tileNum;
	
	public Puzzle(int N) {
		this.N = N;
		obstacleNum = 0;
		tileNum = 3;

		this.goalState = createGoalState();
	}
	
	public Puzzle(int N, int obstacles, int tileNum) {
		this.N = N;
		this.obstacleNum = obstacles;
		this.tileNum = tileNum;
		this.goalState = createGoalState();
	}
	
	/**
	 * Creates the goal state.
	 * 
	 * @return PuzzleState goalState
	 */
	private PuzzleState createGoalState() {
		PuzzleState p = new PuzzleState(this);
		
		for( int i = 0; i < tileNum; i++) {
			// Complex maths for positioning 
			p.setState((char)(65+(tileNum-1-i)), (N*(N-(i+1)))+1);
		}
		
		// The agent is always in the bottom right corner
		p.setState('Q', (N*N)-1);
		
		// Add obstacles if needed
		if( obstacleNum > 0 ) {
			int numberOfSquares = N*N;
			
			// Get a list of places we CAN'T put obstacles
			List<Integer> forbiddenSquares = new ArrayList<Integer>();
			
			// Add goal tiles
			for( int i = 0; i < tileNum; i++) 
				forbiddenSquares.add((N*(N-(i+1)))+1);
			
			// Add intial tiles
			if( N == tileNum) {
				forbiddenSquares.add((N*(N-2)));
				for( int i = 1; i < tileNum; i++) 
					forbiddenSquares.add((N*(N-1)+tileNum-i-1));
			} else {
				for( int i = 0; i < tileNum; i++) 
					forbiddenSquares.add((N*(N-1)+tileNum-i-1));
			}

			// Add agent's corner
			forbiddenSquares.add((N*N)-1);

			List<Integer> obstacles = new ArrayList<Integer>();
			
			// Randomly generate the obstacles
			Random r = new Random();
			for( int i = 0; i < obstacleNum; i++) {
				int rand = r.nextInt(numberOfSquares);
				while( forbiddenSquares.contains(rand) || obstacles.contains(rand)) {
					rand = r.nextInt(numberOfSquares);
				}
				obstacles.add(rand);
			}
			
			// Add them to a new map for obstacles (to be used by other methods)
			// and to the goal state.
			obsMap = new HashMap<Character, Integer>();
			for( Integer obs : obstacles ){
				obsMap.put((char)(97+obs), obs);
				p.setState((char)(97+obs), obs);
			}
		}
		
		System.out.println("Goal state: " + p.map.toString());
		return p;
	}

	/**
	 * Creates the initial node for the search trees
	 * 
	 * Creates an initial PuzzleState and adds it to a new Node.
	 * 
	 * @return Node<PuzzleState> initialNode
	 */
	public Node<PuzzleState> initialState() {
		PuzzleState p = new PuzzleState(this);
		
		// Maths to place the tiles in the right place
		if( N == tileNum) {
			p.setState((char)(65+(tileNum-1)), (N*(N-2)));
			for( int i = 1; i < tileNum; i++) 
				p.setState((char)(65+(tileNum-1-i)), (N*(N-1)+tileNum-i-1));
		} else {
			for( int i = 0; i < tileNum; i++) 
				p.setState((char)(65+(tileNum-1-i)), (N*(N-1)+tileNum-i-1));
		}
		
		// Add agent
		p.setState('Q',(N*N)-1);
			
		// Don't forget the obstacles!
		if( obstacleNum > 0) {
			for( Character o : obsMap.keySet() ){
				p.setState(o, obsMap.get(o));
			}
		}
		
		System.out.println("Intial state: " + p.map.toString());
	
		// Create a node for it
		return new Node<PuzzleState>(p, null, null, 0, 0);
	}
	
	/**
	 * Checks if a given state is the goal state
	 * 
	 * @param p PuzzleState
	 * @return boolean
	 */
	public boolean isGoalState(PuzzleState p) {		
		Map<Character, Integer> map1 = p.map;
		Map<Character, Integer> map2 = goalState.map;
		
		return PuzzleState.mapEquals(map1, map2);
	}
}