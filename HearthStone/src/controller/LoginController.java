package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.api.Session;

import biz.Biz;
import dao.Dao;
import domain.LoginEntity;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		Biz biz = new Biz();
		LoginEntity ob = biz.loginUser(id, pw);
		if(ob.getId()!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("logined_user", ob);
			response.sendRedirect("/HearthStone/mydeck");
		}else {
			response.sendRedirect("/HearthStone/");
		}
	}

}
