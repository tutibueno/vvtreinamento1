package br.com.vv.model;

import java.util.ArrayList;

public class Pedido extends PedidoBase {
	
	private String nomeCliente;
	private String nomeFilial;
	private ArrayList<ItemPedido> itemPedidoList = new ArrayList<>();
	
	private int codigoItemNextNumber;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeFilial() {
		return nomeFilial;
	}
	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}
	public ArrayList<ItemPedido> getItemPedidoList() {
		return itemPedidoList;
	}
	public void setItemPedidoList(ArrayList<ItemPedido> itemPedidoList) {
		this.itemPedidoList = itemPedidoList;
	}
	
	@Override
	public String toString()
	{
		StringBuilder itens = new StringBuilder();
		StringBuilder sbPedido = new StringBuilder();
		
		itens.append("\t -------Itens do Pedido------------\n");
		
		for (int i = 0; i < itemPedidoList.size(); i++) {
			itens.append(itemPedidoList.get(i).toString());
		}
		
		sbPedido.append("Codigo: ").append(codigo).append(" | Filial: ").append(nomeFilial)
		.append(" | Data Hora Inclusao: ").append(dataHoraInclusao).append(" | Data Hora Alteracao: ")
		.append(dataHoraEdicao).append(" | Nome Cliente: ").append(nomeCliente)
		.append(" | Valor Total: ").append(getValor()).append("\n");
		
		sbPedido.append(itens);
		
		return sbPedido.toString();
		
	}
	
	/**
	 * Adiciona um item de pedido na lista de item de pedidos dentro de um pedido.
	 * @param ItemPedido a ser adicionado
	 */
	public void adicionaItemPedido(ItemPedido ip)
	{
		ip.setCodigo(++codigoItemNextNumber);
		itemPedidoList.add(ip);
	}
	
	/**
	 * @return calcula o valor total dos itens do pedido
	 */
	public double getValor()
	{
		double total = 0;
		for (int i = 0; i < itemPedidoList.size(); i++) {
			total += itemPedidoList.get(i).getValorTotal();
		}
		
		return total;
	}
	
	
}
