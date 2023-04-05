package controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import dao.SachDao;
import dao.TacgiaDao;
import dao.TheloaiDao;
import model.Sach;
import model.Tacgia;
import model.Theloai;

/**
 * Servlet implementation class Sachs
 */
@MultipartConfig
@WebServlet("/Sachs/*")
public class Sachs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sachs() {
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
			TheloaiDao tl = new TheloaiDao();
			List<Theloai> ltl = tl.findall();
			TacgiaDao tg = new TacgiaDao();
			List<Tacgia> ltg = tg.findall();
			request.setAttribute("theloai", ltl);
			request.setAttribute("tacgia", ltg);
			request.getRequestDispatcher("/view/Saches/Create.jsp").forward(request, response);
		}
		if(loai.contains("Edit"))
		{
			TheloaiDao tl = new TheloaiDao();
			List<Theloai> ltl = tl.findall();
			TacgiaDao tg = new TacgiaDao();
			List<Tacgia> ltg = tg.findall();
			request.setAttribute("theloai", ltl);
			request.setAttribute("tacgia", ltg);
			String id = request.getParameter("id");
			SachDao tldao = new SachDao();
			Sach sach = tldao.findId(Long.parseLong(id));
			request.setAttribute("item", sach);
			request.getRequestDispatcher("/view/Saches/Edit.jsp").forward(request, response);
		}
		if(loai.contains("Index"))
		{
			
			SachDao tgdao = new SachDao();
			List<Sach> list = tgdao.findall();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/Saches/Index.jsp").forward(request, response);
		}
		if(loai.contains("Delete"))
		{
			
			try {
				String id = request.getParameter("id");
				SachDao tldao = new SachDao();
				tldao.delete(Long.parseLong(id));
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			response.sendRedirect("/doan/Sachs/Index.jsp");
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
			Sach s = new Sach();
			try {
				BeanUtils.populate(s, request.getParameterMap());
				Part part = request.getPart("anh");
				String rpath = request.getServletContext().getRealPath("images/book");
				String fname = Path.of(part.getSubmittedFileName()).getFileName().toString();
				part.write(rpath+"/"+ fname);
				s.setAnh(fname);
				TheloaiDao tldao = new TheloaiDao();
				String idtl = request.getParameter("matheloai");
				Theloai tl = tldao.findId(Long.parseLong(idtl));
				TacgiaDao tgdao = new TacgiaDao();
				String idtg = request.getParameter("matacgia");
				Tacgia tg = tgdao.findId(Long.parseLong(idtg));
				s.setTacgia(tg);
				s.setTheloai(tl);
				SachDao sdao = new SachDao();
				sdao.insert(s);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(loai.contains("Edit"))
		{
			Sach s = new Sach();
			try {
				BeanUtils.populate(s, request.getParameterMap());
				Part part = request.getPart("anh");
				String rpath = request.getServletContext().getRealPath("images/book");
				String fname = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if(part.getSubmittedFileName() != "" )
				{
					part.write(rpath+"/"+ fname);
					s.setAnh(fname);
				}
				TheloaiDao tldao = new TheloaiDao();
				String idtl = request.getParameter("matheloai");
				Theloai tl = tldao.findId(Long.parseLong(idtl));
				TacgiaDao tgdao = new TacgiaDao();
				String idtg = request.getParameter("matacgia");
				Tacgia tg = tgdao.findId(Long.parseLong(idtg));
				s.setTacgia(tg);
				s.setTheloai(tl);
				SachDao sdao = new SachDao();
				sdao.update(s);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		response.sendRedirect("/doan/Sachs/Index.jsp");
	}

}
