package br.com.vv.dados;

import java.util.ArrayList;

import br.com.vv.model.Pedido;

public class PedidoDatasource {
	
	private static ArrayList<Pedido> pedidoList = new ArrayList<>();
	
	/**
	 * Retorna uma lista de pedidos no formato texto. Utiliza o toString() da classe Pedido.
	 */
	public static String getPedidoList()
	{
		String result = "";
		
		if(pedidoList.size() <= 0)
			return "\n\nNenhum pedido cadastrado.";
		
		for (int i = 0; i < pedidoList.size(); i++) {
			result += pedidoList.get(i).toString();
			result += "\n"; 
		}
		
		return result;
	}
		
	/**
	 * Adiciona o pedido na lista de pedidos em mem�ria.
	 * @param pedido - Pedido a ser adicionado
	 */
	public static void criaPedido(Pedido pedido)
	{
		pedidoList.add(pedido);
	}
	
	/**
	 * Obtem um pedido a partir da lista de pedidos em memoria.
	 * @param codigo do pedido a ser consultado
	 * @return um objeto Pedido
	 */
	public static Pedido consultaPedido(Pedido p)
	{
		int i = pedidoList.indexOf(p);
		
		return pedidoList.get(i);
		
	}
	
	/**
	 * Exclui um pedido da lista de pedidos em memoria.
	 * @param codigo do pedido a ser excluido
	 * @return retorna se a exclusao foi bem sucedida em String
	 */
	public static String excluiPedido(int codigo)
	{
		
		for (int i = 0; i < pedidoList.size(); i++) {
			
			if(pedidoList.get(i).getCodigo() == codigo)
			{
				pedidoList.remove(i);
				return "Pedido " + codigo + " excluido.";
			}
			
		}
		
		return "Erro: Pedido " + codigo + " nao encontrado!\n\n\n";
	}
	
	

}
