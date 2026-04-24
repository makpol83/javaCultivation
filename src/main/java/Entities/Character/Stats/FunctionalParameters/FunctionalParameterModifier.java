package Entities.Character.Stats.FunctionalParameters;

public interface FunctionalParameterModifier {
    String getDescription();
    boolean apply(FunctionalParameter parameter);
    boolean remove(FunctionalParameter parameter);
}
