package agents;

public class Equipment {
	private String Id_Equipment;
	private String Type;
	
	public Equipment(String id_Equipment, String type) {
		Id_Equipment = id_Equipment;
		Type = type;
	}
	
	public String getId_Equipment() {
		return Id_Equipment;
	}
	
	public void setId_Equipment(String id_Equipment) {
		Id_Equipment = id_Equipment;
	}
	
	public String getType() {
		return Type;
	}
	
	public void setType(String type) {
		Type = type;
	}
	
	@Override
	public String toString() {
		return "Equipment [Id_Equipment=" + Id_Equipment + ", Type=" + Type + "]";
	}
}
