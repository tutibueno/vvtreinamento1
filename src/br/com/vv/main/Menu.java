package br.com.vv.main;
import java.util.Scanner;

import br.com.vv.dados.*;
import br.com.vv.model.ItemPedido;
import br.com.vv.model.Pedido;

public class Menu {
	
	private static Scanner scanner;
	private static PedidoDatasource pedidoDatasource;
	
	public static void main (String[] args)
	{
		scanner = new Scanner(System.in);
		decideFormaGravacao();
	}
	
	private static void decideFormaGravacao()
	{
		System.out.println("Deseja armazenar os pedido em arquivo ou memoria (A/M) ?");
		String s = scanner.next();
		s = s.toUpperCase();
		ArquivoDataSource a = new ArquivoDataSource();
		if(s.equals("M"))
			pedidoDatasource = new PedidoDatasource(new MemoriaDataSource());
		else if (s.equals("A"))
			pedidoDatasource = new PedidoDatasource(a);
		else
		{
			System.out.println("Opcao invalida! \n");
			decideFormaGravacao();
			return;
		}
		
		mostraMenu();
	}
	
	private static void processaMenu(int opcao)
	{
		switch (opcao) {
		case 1:
			incluiPedido();
			mostraMenu();
			break;
		case 2:
			editaPedido();
			mostraMenu();
			break;
		case 3:
			listaPedido();
			System.out.println(pedidoDatasource.getPedidoList());
			System.out.println("\n\nFim da lista de pedidos\n");
			mostraMenu();
			break;
			
		case 4:
			excluiPedido();
			mostraMenu();
			break;
		
		case 5:
			System.out.println("Deseja sair do progama? (S/N) \n");
			String s = scanner.next();
			s = s.toUpperCase();
			if(s.equals("S"))
			{
				System.out.println("Programa terminado\n");
				System.exit(0);
			}
			else if (s.equals("N"))
			{
				mostraMenu();
				break;
			}
			
		default:
			System.out.println("Opcao invalida.\n\n");
			mostraMenu();
			break;
		}
	}
	
	private static void mostraMenu()
	{
		System.out.println("======Menu Principal=======");
		System.out.println("====1 - Incluir Pedido=====");
		System.out.println("====2 - Editar Pedido======");
		System.out.println("====3 - Listar Pedidos=====");
		System.out.println("====4 - Excluir Pedido=====");
		System.out.println("=========5 - Sair==========");
				
		System.out.println("\nEntre com a opcao");
		processaMenu(scanner.nextInt());
	}
	
	private static void editaPedido()
	{
		System.out.println("Entre com o codigo do pedido: ");
		Pedido p = new Pedido();
		p.setCodigo(scanner.nextInt());
		p = pedidoDatasource.consultaPedido(p);
		if(p != null)
		{
			p.setDataHoraEdicao();
			System.out.println("Codigo do pedido: " + p.getCodigo());
			System.out.println("Entre com o nome do cliente: (" + p.getNomeCliente() +")");
			p.setNomeCliente(scanner.next());
			System.out.println("Entre com a filial: (" + p.getNomeFilial() + ")");
			p.setNomeFilial(scanner.next());
			System.out.println("-------Detalhes do pedido--------");
			for (int i = 0; i < p.getItemPedidoList().size(); i++) {
				
				ItemPedido ip = p.getItemPedidoList().get(i);
				
				System.out.println("Entre com o nome do produto: (" + ip.getNome() +")");
				ip.setNome(scanner.next());
				System.out.println("Entre o valor unitario: ("+ip.getValor()+")");
				ip.setValor(scanner.nextDouble());
				System.out.println("Entre com a quantidade: ("+ip.getQuantidade()+")");
				ip.setQuantidade(scanner.nextInt());
				
			}
			
			pedidoDatasource.editaPedido(p);
		}
		else
		{
			System.err.println("Erro: Pedido nao encontrado.\n\n");
		}
	}
	
	private static void incluiPedido()
	{
		
		Pedido p = new Pedido();
		System.out.println("Codigo do pedido: " + p.getCodigo());
		System.out.println("Entre com o nome do cliente: ");
		p.setNomeCliente(scanner.next());
		System.out.println("Entre com a filial: ");
		p.setNomeFilial(scanner.next());
		System.out.println("-------Detalhes do pedido--------");
		
		//Detalhes do pedido
		
		boolean terminouDetalhes = false;
		
		while (!terminouDetalhes)
		{
			ItemPedido ip = incluiDetalhePedido();
			
			p.adicionaItemPedido(ip);
			
			String flagContinuarCadastrando = "";
			System.out.println("Cadastrar mais itens? S/N");
			flagContinuarCadastrando = scanner.next();
			if(flagContinuarCadastrando.contains("N") || flagContinuarCadastrando.contains("n")) {
				terminouDetalhes = true;
				break;
			}
			
			System.out.println("\n --------");
			
		}
		
		pedidoDatasource.addPedido(p);
		
	}
	
	private static ItemPedido incluiDetalhePedido()
	{
		ItemPedido ip = new ItemPedido();
		
		System.out.println("Entre com o nome do produto: ");
		ip.setNome(scanner.next());
		System.out.println("Entre o valor unitario: ");
		ip.setValor(scanner.nextDouble());
		System.out.println("Entre com a quantidade: ");
		ip.setQuantidade(scanner.nextInt());
		
		return ip;
	}
	
	private static void excluiPedido()
	{
		System.out.println("Entre com o codigo do pedido: ");
		Pedido p = new Pedido();
		p.setCodigo(scanner.nextInt());
		StringBuilder sb = new StringBuilder();
		sb.append("Pedido ").append(p.getCodigo());
		System.out.println(pedidoDatasource.excluiPedido(p) ? sb.append(" excluído com sucesso.") : sb.append(" não econtrado."));
		sb.append("\n");
		sb.toString();
	}
	
	private static void listaPedido()
	{
		StringBuilder result = new StringBuilder();

		if (pedidoDatasource.getPedidoList().size() <= 0) {
			result.append("\n\nNenhum pedido cadastrado.");
		}
		
		for (Pedido pedido : pedidoDatasource.getPedidoList())
		{
			result.append(pedido.toString()+"\n");
		} 
		
		result.toString();
		
	}
}
