package br.com.vv.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.vv.model.Pedido;

public class ArquivoDataSource implements IDataSource {

	private ArrayList<Pedido> pedidoList = new ArrayList<>();

	public ArquivoDataSource() {
		restauraArquivo();
	}

	@Override
	public void addPedido(Pedido pedido) {

		pedidoList.add(pedido);
		gravaArquivo();

	}

	@Override
	public Pedido consultaPedido(Pedido p) {
		
		if(pedidoList.contains(p))
		{
			int i = pedidoList.indexOf(p);
			return pedidoList.get(i);
		}
		return null;
		
	}

	@Override
	public boolean excluiPedido(Pedido p) {
		if(pedidoList.remove(p))
		{
			gravaArquivo();
			return true;
		}
		return false;
	}
	
	private void restauraArquivo() {

		try {
			File file = FileUtils.getFile("pedido2.dat");
			if(!file.exists()) {
				file.createNewFile();
				System.err.println("Arquivo de dados criado: pedido.dat");
				return;
			}
			FileInputStream fileIn = FileUtils.openInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			pedidoList = (ArrayList) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			//i.printStackTrace();
			System.err.println("Ocorreu um erro ao ler o arquivo de pedidos");
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

	}

	private void gravaArquivo() {
		// Adiciona no arquivo
		try {
			File file = FileUtils.getFile("pedido2.dat");
			FileOutputStream fileOut = FileUtils.openOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(pedidoList);
			out.close();
			fileOut.close();
			System.out.printf("Salvo no arquivo pedido.dat \n");

		} catch (IOException i) {
			System.err.print("Ocorreu um erro ao salvar o arquivo de pedidos");
			i.printStackTrace();
		}
	}

	@Override
	public void editaPedido(Pedido p) {
		
		int i = pedidoList.indexOf(p);
		
		Pedido o = pedidoList.get(i);
		
		o = p;
		
		gravaArquivo();
		
	}

	@Override
	public ArrayList<Pedido> getPedidoList() {
		return pedidoList;
	}

}
