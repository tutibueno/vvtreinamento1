package br.com.vv.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.vv.model.Pedido;

public class PedidoDatasource implements IDataSource {

	private ArrayList<Pedido> pedidoList = new ArrayList<>();

	/* (non-Javadoc)
	 * @see br.com.vv.dados.IDataSource#getPedidoList()
	 */
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

	/* (non-Javadoc)
	 * @see br.com.vv.dados.IDataSource#addPedido(br.com.vv.model.Pedido)
	 */
	@Override
	public void addPedido(Pedido pedido) {
		pedidoList.add(pedido);

		// Adiciona no arquivo
		try {
			FileOutputStream fileOut = new FileOutputStream("/pedido.ser");
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

	/* (non-Javadoc)
	 * @see br.com.vv.dados.IDataSource#consultaPedido(br.com.vv.model.Pedido)
	 */
	@Override
	public Pedido consultaPedido(Pedido p) {
		int i = pedidoList.indexOf(p);

		return pedidoList.get(i);

	}

	/* (non-Javadoc)
	 * @see br.com.vv.dados.IDataSource#excluiPedido(int)
	 */
	@Override
	public String excluiPedido(int codigo) {

		for (int i = 0; i < pedidoList.size(); i++) {

			if (pedidoList.get(i).getCodigo() == codigo) {
				pedidoList.remove(i);
				return "Pedido " + codigo + " excluido.";
			}

		}

		return "Erro: Pedido " + codigo + " nao encontrado!\n\n\n";
	}

	/* (non-Javadoc)
	 * @see br.com.vv.dados.IDataSource#restauraArquivo()
	 */
	@Override
	public void restauraArquivo() {
		try {
			FileInputStream fileIn = new FileInputStream("/pedido.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			pedidoList = (ArrayList<Pedido>) in.readObject();
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

}
