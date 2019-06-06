package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.protocol.ResponseServer;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;

import biz.Biz;
import domain.LoginEntity;
import domain.MyDeckItem;

/**
 * Servlet implementation class GetDeckController
 */
@WebServlet(name = "GetDeck", urlPatterns = { "/getdeck" })
public class GetDeckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDeckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		LoginEntity user = (LoginEntity)session.getAttribute("logined_user");
		String id = user.getId();
		MyDeckItem ob = new MyDeckItem();
		ob.setDeck_name(request.getParameter("deck_name"));
		ob.setDeck_class(request.getParameter("deck_class"));
		ob.setDeck_type(request.getParameter("deck_type"));
		ob.setDeck_link(request.getParameter("deck_link"));
		ob.setUser(id);
		
		Biz biz = new Biz();
		int n = biz.insertMyDeckItem(ob);
		
		if(n>0) {
			response.sendRedirect("/HearthStone/mydeck");
		}
	}

}
