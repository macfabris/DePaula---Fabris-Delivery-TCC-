package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.dao.GrupoProdutoDao;
import br.com.ffsd.tcc.dao.ProdutoDao;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.modelo.Produto;

public class PaginaProdutosLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao(conexao);
		List<GrupoProduto> grupoProdutos = grupoProdutoDao.getLista();
		req.setAttribute("grupoProduto", grupoProdutos);
		ProdutoDao produtoDao = new ProdutoDao(conexao);
		List<Produto> produtos = produtoDao.getLista();
		req.setAttribute("produtos", produtos);
		return "produtos.jsp";
	}

}
