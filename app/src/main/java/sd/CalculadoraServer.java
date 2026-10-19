package sd;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraServer  implements ICalculadora {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;

	public int soma(int a, int b) throws RemoteException {
		System.out.println("Método soma chamado " + ++chamadas);
		return a + b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		CalculadoraServer calculadora = new CalculadoraServer();		
		Registry reg = null;
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Criando o registro...");
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Registro rodando em: "+Registry.REGISTRY_PORT);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
				System.out.println("Registro rodando em: "+Registry.REGISTRY_PORT);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub);
	}

	@Override
	public int sub(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Método subtração chamado " + chamadas++);
		
		return a-b;
	}
	@Override
	public int div(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Método divisão chamado " + chamadas++);
		
		return a/b;
	}
	@Override
	public int mult(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Método multiplicação chamado " + chamadas++);
		
		return a*b;
	}
	
}
