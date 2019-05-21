package interfaces;

public interface ISerializable {
    String serialize();
    boolean deserialize(String properties);
}
