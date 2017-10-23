import java.util.HashMap;
import java.util.Map;

/**
 * Stores the state of a puzzle at a specific time.
 * 
 * @author Richard Cook
 * @version 0.9 (2014-11-26)
 */
public class PuzzleState {
	
	// So we can access N and obstacles
	Puzzle puzzle;
	
	// Holds the characters in the puzzle and their locations
	Map<Character, Integer> map;
	
	public PuzzleState(Puzzle puzzle) {
		this.puzzle = puzzle;
		map = new HashMap<Character, Integer>();
	}
	
	public PuzzleState(PuzzleState p, char action) {
		this.puzzle = p.puzzle;
		this.map = new HashMap<Character, Integer>(p.map);
	
		// Takes a move based on the action provided
		// Breaks make it harder to simplify this code.
		switch(action) {
			case 'l':
				// Check the move is valid
				if( canTakeAction(p,'l') ) {
					// Get the agent's position
					int currentPos = this.map.get('Q');
					
					// Check no one is sitting in the agent's new position
					if( this.map.containsValue(currentPos-1)) {
						// If they are find them and move them to the agent's position.
						for( Character c : this.map.keySet() ) {
							if( this.map.get(c) == currentPos-1 ) {
								this.map.put(c, currentPos);
								break;
							}
						}
					}
					// else move the agent
					this.map.put('Q', currentPos-1);
				}
				break;
			case 'r':
				if( canTakeAction(p,'r') ) {
					int currentPos = this.map.get('Q');
					if( this.map.containsValue(currentPos+1)) {
						for( Character c : this.map.keySet() ) {
							if( this.map.get(c) == currentPos+1 ) {
								this.map.put(c, currentPos);
								break;
							}
						}
					}
					map.put('Q', currentPos+1);
				}
				break;
			case 'u':
				if( canTakeAction(p,'u') ) {
					int currentPos = this.map.get('Q');
					if( this.map.containsValue(currentPos-puzzle.N)) {
						for( Character c : this.map.keySet() ) {
							if( this.map.get(c) == currentPos-puzzle.N ) {
								this.map.put(c, currentPos);
								break;
							}
						}
					}
					this.map.put('Q', currentPos-puzzle.N);
				}				
				break;
			case 'd':
				if( canTakeAction(p,'d') ) {
					int currentPos = this.map.get('Q');
					if( this.map.containsValue(currentPos+puzzle.N)) {
						for( Character c : this.map.keySet() ) {
							if( this.map.get(c) == currentPos+puzzle.N ) {
								this.map.put(c, currentPos);
								break;
							}
						}
					}
					this.map.put('Q', currentPos+puzzle.N);
				}
				break;
		}
	}
		
	/**
	 * Puts a character into a certain position in the puzzle.
	 * 
	 * @param char ch
	 * @param int pos
	 */
	protected void setState(char ch, int pos){
		map.put(ch, pos);
	}	
	
	/**
	 * Checks whether a specific action can be taken in a given PuzzleState
	 * 
	 * @param PuzzleState p
	 * @param char action 
	 * @return boolean
	 */
	protected static boolean canTakeAction(PuzzleState p, char action) {
		switch(action) {
			case 'l':
				// Do maths to work out if the agent's current position allows them to move
				// And check there's no obstacle in the way
				if( p.map.get('Q') % p.puzzle.N != 0 && !obstacleBlocking(p, 'l') ) {
					return true;
				}
				return false;
			case 'r':
				if( p.map.get('Q') % p.puzzle.N != p.puzzle.N-1 && !obstacleBlocking(p, 'r')  ) {
					return true;
				}
				return false;
			case 'u':
				if( p.map.get('Q') >= p.puzzle.N && !obstacleBlocking(p, 'u')  ) {
					return true;
				}
				return false;
			case 'd':
				if( p.map.get('Q') < p.puzzle.N*(p.puzzle.N-1) && !obstacleBlocking(p, 'd')  ) {
					return true;
				}
				return false;
		}
		return false;
	}
	
	/**
	 * Checks whether an obstacle is blocking the destination square or not
	 * 
	 * @param PuzzleState p
	 * @param char action
	 * @return boolean
	 */
	public static boolean obstacleBlocking(PuzzleState p, char action) {
		// Get obstacle list
		Map<Character, Integer> obs = p.puzzle.obsMap;
		// No obstacles == no blocking
		if( obs == null || obs.isEmpty() )
			return false;
		
		int currentPos = p.map.get('Q');

		switch(action) {
			case 'l':
				// Check if the intended position has a obstacle in it
				if( obs.containsValue(currentPos-1)) 
					return true;
				break;
			case 'r':
				if( obs.containsValue(currentPos+1)) 
					return true;
				break;
			case 'u':
				if( obs.containsValue(currentPos-p.puzzle.N)) 
					return true;
				break;
			case 'd':
				if( obs.containsValue(currentPos+p.puzzle.N)) 
					return true;
				break;
		}
		
		return false;
	}
	
	/**
	 * Custom equality function as Map.equals() doesn't check the mappings, just the key sets
	 * 
	 * @param Map<Character, Integer> map1
	 * @param Map<Character, Integer> map2
	 * @return boolean
	 */
	protected static boolean mapEquals(Map<Character, Integer> map1, Map<Character, Integer> map2) {
		if( map1.size() == map2.size() ) {
			for( Character c : map1.keySet() ) {
				if( map1.get(c) != map2.get(c) )
					return false;
			}
			return true;
		}
		return false;
	
	}
	
	/**
	 * Calculates the Manhattan Distance of the PuzzleState
	 * 
	 * @param PuzzleState goal
	 * @return int ManhattanDistance
	 */
	protected int calculateManhattanDistance(PuzzleState goal){
		int md = 0;
		
		// For each tile, calculate the distance
		for( Character c : this.map.keySet() ) {
			int posThis = this.map.get(c);
			int posGoal = goal.map.get(c);
			
			int colThis = posThis % puzzle.N;
			int colGoal = posGoal % puzzle.N;
			int colDiff = Math.abs(colGoal - colThis);
			
			int rowThis = posThis / puzzle.N;
			int rowGoal = posGoal / puzzle.N;
			int rowDiff = Math.abs(rowGoal - rowThis);
						
			md += colDiff + rowDiff;
		}
				
		return md;
	}


	/**
	 * Calculates the Place distance  of the PuzzleState
	 * 
	 * @param PuzzleState goal
	 * @return int Place distance
	 */	
	public int calculatePlaces(PuzzleState goal) {
		int count = 0;
		
		// Check if each character is in place
		for( Character c : map.keySet() ) {
			if( map.get(c) == goal.map.get(c) ) 
				count++;
		}
		
		return count;
	}	
}
