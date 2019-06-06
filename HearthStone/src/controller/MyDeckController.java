package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.Biz;
import domain.LoginEntity;
import domain.MyDeckItem;

/**
 * Servlet implementation class MyDeckController
 */
@WebServlet(name = "MyDeck", urlPatterns = { "/mydeck" })
public class MyDeckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		LoginEntity ob = (LoginEntity)session.getAttribute("logined_user");
		if(ob!=null) {
			String user = ob.getId();
			Biz biz = new Biz();
			List<MyDeckItem> list = biz.selectMyDeckItem(user);
			RequestDispatcher rd = request.getRequestDispatcher("/mydeck.jsp");
			request.setAttribute("list",list);
			rd.forward(request, response);
		}else {
			response.sendRedirect("/HearthStone/login.jsp");
		}
		
	}

}
