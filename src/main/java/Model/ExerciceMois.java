package Model;

public class ExerciceMois{
    private Exercice exercice;
    private int mois;
    private Journal journal;
    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public ExerciceMois(Exercice exercice, int mois, Journal journal) {
        this.exercice = exercice;
        this.mois = mois;
        this.journal = journal;
    }
}
