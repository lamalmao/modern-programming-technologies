public abstract class CSVReadable<T> {

  abstract void parseCSVLine(String[] data, String[] titles);

  abstract T create();
}
