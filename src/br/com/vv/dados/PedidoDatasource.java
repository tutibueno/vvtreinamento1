package br.com.vv.dados;

import br.com.vv.model.Pedido;

public class PedidoDatasource implements IDataSource {

	private IDataSource iDataSource;

	public PedidoDatasource(IDataSource iDataSource){
		this.iDataSource = iDataSource;
	}

	@Override
	public String getPedidoList() {
		return iDataSource.getPedidoList();
	}

	@Override
	public void addPedido(Pedido pedido) {
		iDataSource.addPedido(pedido);
	}

	@Override
	public Pedido consultaPedido(Pedido p) {
		return iDataSource.consultaPedido(p);
	}

	@Override
	public String excluiPedido(Pedido p) {
		return iDataSource.excluiPedido(p);
	}

	@Override
	public void editaPedido(Pedido p) {
		iDataSource.editaPedido(p);
	}
	
}
