package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dao.TheloaiDao;
import model.Theloai;

/**
 * Servlet implementation class Theloai
 */
@WebServlet("/Theloais/*")
public class Theloais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Theloais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loai = request.getRequestURI();
		if(loai.contains("Create"))
		{
			request.getRequestDispatcher("/view/Theloais/Create.jsp").forward(request, response);
		}
		if(loai.contains("Edit"))
		{
			String id = request.getParameter("id");
			TheloaiDao tldao = new TheloaiDao();
			Theloai tl = tldao.findId(Long.parseLong(id));
			request.setAttribute("theloai", tl);
			request.getRequestDispatcher("/view/Theloais/Edit.jsp").forward(request, response);
		}
		if(loai.contains("Index"))
		{
			
			TheloaiDao tldao = new TheloaiDao();
			List<Theloai> list = tldao.findall();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/Theloais/Index.jsp").forward(request, response);
		}
		if(loai.contains("Delete"))
		{
			
			try {
				String id = request.getParameter("id");
				TheloaiDao tldao = new TheloaiDao();
				tldao.delete(Long.parseLong(id));
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			response.sendRedirect("/doan/Theloais/Index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loai = request.getRequestURI();
		if(loai.contains("Create"))
		{
			Theloai tl = new Theloai();
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				TheloaiDao tldao = new TheloaiDao();
				tldao.insert(tl);
				System.out.print(tl.getMatheloai());
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(loai.contains("Edit"))
		{
			Theloai tl = new Theloai();
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				TheloaiDao tldao = new TheloaiDao();
				tldao.update(tl);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		response.sendRedirect("/doan/Theloais/Index.jsp");
	}

}
