package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawl.Crawler;
import domain.CustomDeckItem;

/**
 * Servlet implementation class CustomDeckController
 */
@WebServlet("/CustomDeckController")
public class CustomDeckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomDeckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Crawler cwl = new Crawler();
		int n = cwl.updateMetaDeta();
		List<CustomDeckItem> deck_list = cwl.getCustomDeckDataByDB();
		RequestDispatcher rd = request.getRequestDispatcher("/custom.jsp");
		request.setAttribute("custom_list",deck_list);
		rd.forward(request, response);
	}
}
