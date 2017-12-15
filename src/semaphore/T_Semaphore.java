package semaphore;

// enum permettant de verifier sans recourrir a l'introspection 
// qu'il n'y a pas 2 semaphore de meme type entrant en concurrence sur un meme segment 
public enum T_Semaphore {
	t_feu,
	t_limite,
	t_radar;
}
