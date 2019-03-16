package br.com.vv.main;
import java.util.Scanner;

import br.com.vv.dados.PedidoDatasource;
import br.com.vv.model.ItemPedido;
import br.com.vv.model.Pedido;

public class Menu {
	
	private static Scanner scanner;
	
	public static void main (String[] args)
	{
		
		scanner = new Scanner(System.in);
		
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
			System.out.println(PedidoDatasource.getPedidoList());
			System.out.println("\n\nFim da lista de pedidos\n");
			mostraMenu();
			break;
			
		case 4:
			System.out.println("Entre com o codigo do pedido: ");
			System.out.println(PedidoDatasource.excluiPedido(scanner.nextInt()));
			mostraMenu();
			break;
		
		case 5:
			System.out.println("Programa terminado\n");
			System.exit(0);
			break;

		default:
			System.out.println("Opcao invalida.\n\n");
			mostraMenu();
			break;
		}
	}
	
	private static void mostraMenu()
	{
		System.out.println("======Menu Principal=======");
		System.out.println("===1 - Incluir Pedido======");
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
		p = PedidoDatasource.consultaPedido(p);
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
		}
		else
		{
			System.out.println("Erro: Pedido " + p.getCodigo() + " nao encontrado.\n\n");
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
		
		PedidoDatasource.criaPedido(p);
		
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
	

}
