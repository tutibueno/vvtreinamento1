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

	private List<Pedido> pedidoList = new ArrayList<>();

	public ArquivoDataSource() {
		restauraArquivo();
	}

	@Override
	public String getPedidoList() {
		String result = "";

		if (pedidoList.size() <= 0)
			return "\n\nNenhum pedido cadastrado.";

		for (int i = 0; i < pedidoList.size(); i++) {
			result += pedidoList.get(i).toString();
			result += "\n";
		}

		return result;
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
	public String excluiPedido(Pedido p) {

		if (pedidoList.remove(p)) {
			gravaArquivo();
			return "Pedido " + p.getCodigo() + " excluido com sucesso.";
		}

		return "Erro: Pedido " + p.getCodigo() + " nao encontrado!\n\n";

	}
	
	private void restauraArquivo() {

		try {
			File file = FileUtils.getFile("/pedido.ser");
			FileInputStream fileIn = FileUtils.openInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			pedidoList = (ArrayList) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Ocorreu um erro ao ler o arquivo de pedidos");
			c.printStackTrace();
			return;
		}

	}

	private void gravaArquivo() {
		// Adiciona no arquivo
		try {
			File file = FileUtils.getFile("/pedido.ser");
			FileOutputStream fileOut = FileUtils.openOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(pedidoList);
			out.close();
			fileOut.close();
			System.out.printf("Salvo no arquivo /pedido.ser \n");

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

}
