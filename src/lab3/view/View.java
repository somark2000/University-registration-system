package lab3.view;

public interface View <E>{
    /**
     * prints the informations of a given entity on the screen
     * @param entity is a given entity (student, teacher or course)
     */
    void printDetails(E entity);
}
