package br.com.vv.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.vv.model.Pedido;

public interface IDataSource {
	
	final List<Pedido> pedidoList = null;

	/**
	 * Retorna uma lista de pedidos no formato texto. Utiliza o toString() da classe
	 * Pedido.
	 */
	String getPedidoList();

	/**
	 * Adiciona o pedido na lista de pedidos em mem�ria.
	 * 
	 * @param pedido
	 *            - Pedido a ser adicionado
	 */
	void addPedido(Pedido pedido);

	/**
	 * Obtem um pedido a partir da lista de pedidos em memoria.
	 * 
	 * @param pedido
	 *             pedido a ser consultado
	 * @return um objeto Pedido
	 */
	Pedido consultaPedido(Pedido p);

	/**
	 * Exclui um pedido da lista de pedidos em memoria.
	 * 
	 * @param codigo
	 *            do pedido a ser excluido
	 * @return retorna se a exclusao foi bem sucedida em String
	 */
	String excluiPedido(Pedido p);
	
	/**
	 * Edita um pedido da lista de pedidos em memoria.
	 * 
	 * @param codigo
	 *            do pedido a ser editado
	 * 
	 */
	void editaPedido(Pedido p);

}