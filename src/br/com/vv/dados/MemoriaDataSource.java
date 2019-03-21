package br.com.vv.dados;

import java.util.ArrayList;

import br.com.vv.model.Pedido;

public class MemoriaDataSource implements IDataSource{
	
	private ArrayList<Pedido> pedidoList = new ArrayList<>();

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
	}

	@Override
	public Pedido consultaPedido(Pedido p) {
		int i = pedidoList.indexOf(p);
		return pedidoList.get(i);
	}

	@Override
	public String excluiPedido(Pedido p) {
		if(pedidoList.remove(p))
			return "Pedido " + p.getCodigo() + " excluido com sucesso.";
		return "Erro: Pedido " + p.getCodigo() + " nao encontrado!\n\n";
	}
	
	@Override
	public void editaPedido(Pedido p) {
		
		int i = pedidoList.indexOf(p);
		
		Pedido o = pedidoList.get(i);
		
		o = p;	
	}
	
	
}
