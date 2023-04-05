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

import dao.TacgiaDao;
import dao.TheloaiDao;
import model.Tacgia;
import model.Theloai;

/**
 * Servlet implementation class Tacgias
 */
@MultipartConfig
@WebServlet("/Tacgias/*")
public class Tacgias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tacgias() {
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
			request.getRequestDispatcher("/view/Tacgias/Create.jsp").forward(request, response);
		}
		if(loai.contains("Edit"))
		{
			String id = request.getParameter("id");
			TacgiaDao tldao = new TacgiaDao();
			Tacgia tl = tldao.findId(Long.parseLong(id));
			request.setAttribute("item", tl);
			request.getRequestDispatcher("/view/Tacgias/Edit.jsp").forward(request, response);
		}
		if(loai.contains("Index"))
		{
			
			TacgiaDao tgdao = new TacgiaDao();
			List<Tacgia> list = tgdao.findall();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/Tacgias/Index.jsp").forward(request, response);
		}
		if(loai.contains("Delete"))
		{
			
			try {
				String id = request.getParameter("id");
				TacgiaDao tldao = new TacgiaDao();
				tldao.delete(Long.parseLong(id));
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			response.sendRedirect("/doan/Tacgias/Index.jsp");
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
			Tacgia tl = new Tacgia();
			
			
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				Part part = request.getPart("anh");
				String rpath = request.getServletContext().getRealPath("images/user");
				String fname = Path.of(part.getSubmittedFileName()).getFileName().toString();
				System.out.println(rpath+"/"+ fname);
				part.write(rpath+"/"+ fname);
				tl.setAnh(fname);
				TacgiaDao tldao = new TacgiaDao();
				tldao.insert(tl);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(loai.contains("Edit"))
		{
			Tacgia tl = new Tacgia();
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				Part part = request.getPart("anh");
				
				String rpath = request.getServletContext().getRealPath("images/user");
				String fname = Path.of(part.getSubmittedFileName()).getFileName().toString();
				System.out.println(","+fname+",");
				System.out.println(request.getParameter("anh"));
				if(part.getSubmittedFileName() != "" )
				{
					part.write(rpath+"/"+ fname);
					tl.setAnh(fname);
				}
				System.out.println(tl.getMatacgia());
				TacgiaDao tldao = new TacgiaDao();
				tldao.update(tl);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		response.sendRedirect("/doan/Tacgias/Index.jsp");
	}

}
