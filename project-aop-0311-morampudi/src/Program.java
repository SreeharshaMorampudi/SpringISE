
public class Program {
	public static void main(String[] args)
	{
		ComponentApp componentApp= new ComponentApp("COM");
		DataApp dataApp = new DataApp("DATA");
		ServiceApp serviceApp = new ServiceApp("Service");
		
		String comName = componentApp.getName();
		System.out.println("  COM NAME: "+comName);
		
		String dataName = dataApp.getName();
		System.out.println("  DAT NAME: "+dataName);
		
		String serviceName = serviceApp.getName();
		System.out.println("  SRC NAME: "+serviceName);
	}

}
