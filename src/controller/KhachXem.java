package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SachDao;
import dao.TacgiaDao;
import dao.TheloaiDao;
import model.Sach;
import model.Tacgia;
import model.Theloai;

/**
 * Servlet implementation class KhachXem
 */
@WebServlet("/KhachXem/*")
public class KhachXem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachXem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loai = request.getRequestURI();
		if(loai.contains("theloai"))
		{
			TheloaiDao tldao = new TheloaiDao();
			List<Theloai> tl = tldao.findall();
			request.setAttribute("list", tl);
			request.getRequestDispatcher("/view/KhachXem/theloai.jsp").forward(request, response);
		}
		else if(loai.contains("chitietloai"))
		{
			try {
				String id = request.getParameter("loai");
				TheloaiDao tldao = new TheloaiDao();
				Theloai tl = tldao.findId(Long.parseLong(id));
				request.setAttribute("item", tl);
				request.getRequestDispatcher("/view/KhachXem/chitietloai.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(loai.contains("chitiettacgia"))
		{
			try {
				String id = request.getParameter("loai");
				TacgiaDao tldao = new TacgiaDao();
				Tacgia tl = tldao.findId(Long.parseLong(id));
				request.setAttribute("item", tl);
				request.getRequestDispatcher("/view/KhachXem/chitiettacgia.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		else if(loai.contains("tacgia"))
		{
			TacgiaDao tldao = new TacgiaDao();
			List<Tacgia> tl = tldao.findall();
			request.setAttribute("list", tl);
			request.getRequestDispatcher("/view/KhachXem/tacgia.jsp").forward(request, response);
			return;
		}
		else if(loai.contains("sachxem"))
		{
			SachDao tldao = new SachDao();
			List<Sach> tl = tldao.findall();
			String tk = request.getParameter("ten");
			if(tk != null)
			{
				List<Sach> sn = new ArrayList<Sach>();
				
				for(Sach s : tl)
				{
					if(s.getTensach().contains(tk))
						sn.add(s);
				}
				request.setAttribute("list", sn);
				request.getRequestDispatcher("/view/KhachXem/sachxem.jsp").forward(request, response);
				return;
				
			}
			request.setAttribute("list", tl);
			request.getRequestDispatcher("/view/KhachXem/sachxem.jsp").forward(request, response);
			return;
		}
		else if(loai.contains("sach"))
		{
			try {
				String id = request.getParameter("id");
				SachDao tldao = new SachDao();
				Sach tl = tldao.findId(Long.parseLong(id));
				request.setAttribute("item", tl);
				request.getRequestDispatcher("/view/KhachXem/sach.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(loai.contains("lienhe"))
		{
			request.getRequestDispatcher("/view/KhachXem/lienhe.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
