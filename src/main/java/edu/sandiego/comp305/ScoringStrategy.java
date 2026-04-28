package edu.sandiego.comp305;


public interface ScoringStrategy {


    public abstract double calculateScore(
            final Politician politician, final int week);

}