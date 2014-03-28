package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.FornecedorDao;
import br.com.ffsd.tcc.dao.GrupoProdutoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.ProdutoDao;
import br.com.ffsd.tcc.dao.StatusPedidoDao;
import br.com.ffsd.tcc.dao.UnidadeDeMedidaDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Fornecedor;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.modelo.StatusPedido;
import br.com.ffsd.tcc.modelo.UnidadeDeMedida;

public class AlteraProdutoLogica implements Logica{

	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Produto produto = new Produto();
		produto.setId(Integer.parseInt(req.getParameter("id")));
		produto.setNome(req.getParameter("nome"));
		produto.setDescricao(req.getParameter("descricao"));
		produto.setPcCusto(Double.parseDouble(req.getParameter("pcCusto")));
		produto.setPorcentLucro(Double.parseDouble(req.getParameter("porcentLucro")));
		//produto.setValor(Double.parseDouble(req.getParameter("valor")));
		//convertendo DATA
		String dataEmTexto = req.getParameter("dataCadastro");
		java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.setTime(data);
		produto.setDataCadastro(dataCadastro);
		produto.grupoProduto.setId(Integer.parseInt(req.getParameter("idGrupoProduto")));
		produto.unidadeDeMedida.setId(Integer.parseInt(req.getParameter("idUnidadeDeMedida")));
		
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		ProdutoDao produtoDao = new ProdutoDao(conexao);
		produtoDao.altera(produto);
		
		ClienteDao clienteDao = new ClienteDao(conexao);
		List<Cliente> clientes = clienteDao.getLista();
		req.setAttribute("clientes", clientes);
		
		List<Produto> produtos = produtoDao.getLista();
		req.setAttribute("produtos", produtos);
		
		GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao(conexao);
		List<GrupoProduto> gruposProduto = grupoProdutoDao.getLista();
		req.setAttribute("gruposProduto", gruposProduto);
		
		UnidadeDeMedidaDao unidadeDeMedidaDao = new UnidadeDeMedidaDao(conexao);
		List<UnidadeDeMedida> unidadesDeMedida = unidadeDeMedidaDao.getLista();
		req.setAttribute("unidadesDeMedida", unidadesDeMedida);
			
		PedidoDao pedidoDao = new PedidoDao(conexao);
		List<Pedido> pedidos = pedidoDao.getLista();
		req.setAttribute("pedidos", pedidos);
		
		StatusPedidoDao statusPedidoDao = new StatusPedidoDao(conexao);
		List<StatusPedido> status = statusPedidoDao.getLista();
		req.setAttribute("status", status);
		
		FornecedorDao fornecedorDao = new FornecedorDao(conexao);
		List<Fornecedor> fornecedores = fornecedorDao.getLista();
		req.setAttribute("fornecedores", fornecedores);
		
		
		return "Adm.jsp";
	}
	
}