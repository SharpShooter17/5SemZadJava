
class Service {
	private final int idOwner;
	private final int idService;
	private String name;
	
	Service(int idOwner, int idService, String name){
		this.idOwner = idOwner;
		this.idService = idService;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getIdOwner() {
		return idOwner;
	}
	
	public int getIdService() {
		return idService;
	}
	
}