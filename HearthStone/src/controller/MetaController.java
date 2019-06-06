package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawl.Crawler;
import domain.MetaItem;
import logicUtil.LogicUtil;

@WebServlet("/MetaController")
public class MetaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MetaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String meta_mode = request.getParameter("meta_mode");
		
		Crawler crawler = new Crawler();
		int n = crawler.updateMetaDeta();
		List<MetaItem> items = new ArrayList<MetaItem>();
		items = crawler.getMetaDataByDB();

	
		RequestDispatcher rd = request.getRequestDispatcher("/meta.jsp");
		request.setAttribute("items",items);
		//System.out.println(n);
		rd.forward(request, response);
		
		
	}

}
