package repository;

public interface IFileRepo<E>{
    /** Function storeToFile saves InMemoryRepo contents to JSON file
     */
    void storeToFile();

    /**Function loadFromFile loads data from JSON file
     */
    void loadFromFile(Class<E[]> c);

    /**
     * @param _filename von Dokument
     * wo man Daten speichern soll
     */
    void setFile(String _filename);
}
