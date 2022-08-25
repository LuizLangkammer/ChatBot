package serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialyze {

	
	//Código retirado de https://www.devmedia.com.br/serializacao-de-objetos-em-java/23413 e adaptado para serialização de um objeto
	
	public static void saveObjects(ArrayList<Object> lista, String nomeArq) throws IOException {
		File arq = new File(nomeArq);

		arq.delete();
		arq.createNewFile();

		ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
		objOutput.writeObject(lista);
		objOutput.close();

	}

	public static void saveObject(Object object, String nomeArq) throws IOException {
		File arq = new File(nomeArq);

		arq.delete();
		arq.createNewFile();

		ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
		objOutput.writeObject(object);
		objOutput.close();

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Object> readObjects(String nomeArq) throws IOException, ClassNotFoundException {
		ArrayList<Object> lista = new ArrayList<Object>();
		File arq = new File(nomeArq);
		if (arq.exists()) {
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
			lista = (ArrayList<Object>) objInput.readObject();
			objInput.close();
		}

		return (lista);
	}
	
	public static Object readObject(String nomeArq) throws IOException, ClassNotFoundException {
		Object obj = null; 
		File arq = new File(nomeArq);
		if (arq.exists()) {
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
			obj = (Object) objInput.readObject();
			objInput.close();
		}

		return obj;
	}

}