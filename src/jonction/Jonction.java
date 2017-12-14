package jonction;

import java.util.Random;

import exception.JonctionException;
import exception.SegmentException;
import exception.VoitureException;
import route.Ligne;
import route.Segment;
import voiture.Voiture;

public abstract class Jonction extends Segment {

	protected Voiture occupant;
	protected int numArrivee;
	protected Ligne lignes[];
	protected String nom;
	Random rand = new Random();

	public Jonction() {
		super();
		this.nom = "quelquePart";
	}

	public Jonction(String nom) {

		super();
		this.nom = nom;

	}


	public void ajouterLigne(Ligne ligne) throws SegmentException {
		if (ligne == null) {
			throw new SegmentException("Cette ligne n'existe pas dans ajouterLigne !! \n");
		} else {
			int i = 0;
			while (i < lignes.length && lignes[i] != null)
				++i;
			if (i < lignes.length)
				lignes[i] = ligne;
			else
				System.out.println("On ne peut peut plus ajouter de ligne a " + this);
		}

	}

	@Override
	public void finirConstruction() throws SegmentException, JonctionException {
		// On cree des routes pour qu'il n'y ait pas de jonctions avec des sorties
		// nulles.
		for (Ligne ln : lignes)
			if (ln == null) {
				Barriere qqpart = new Barriere();
				ln = new Ligne(this, qqpart, 2);
			}

	}

	public Voiture getOccupant() {
		return occupant;
	}

	@Override
	public Segment sortiePour(Voiture occupant) throws VoitureException {

			if (occupant.getSegPrec() == null)
				return lignes[rand.nextInt(lignes.length)];

			int temp = rand.nextInt(lignes.length - 1);
			if (temp >= numArrivee)
				++temp;
			return lignes[temp];
		}

	@Override
	public boolean estDirigeVers(Segment segment) throws SegmentException {

		return !segment.estDirigeVers(this);

	}

	@Override
	public int getLong() {
		return 1;
	}

	@Override
	public void setSegmentArrivee(Segment segment) throws SegmentException {

		if (segment == null)
			throw new SegmentException("On tente de mettre un segment null en bout de Jonction");
		for(int i=0; i<lignes.length; ++i)
			if (segment == lignes[i])
				numArrivee = i;

	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean containsSemaphore() {
		return false;
	}


	@Override
	public void activerCapteurs(Voiture voiture, int pos1, int pos2) {
		// Rien a faire ici car une voiture ne peut pas se deplacer a l'interieur d'un segment
		
	}

	@Override
	public void activerCapteurs(Voiture voiture) {
		if (posCapteurs == null)
			return;
		for (int i = 0; i < posCapteurs.size(); ++i) {
			capteurs.get(i).detecter(voiture);
		}
	}


}
