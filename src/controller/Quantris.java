package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import dao.KhachDao;
import dao.QuocgiaDao;
import model.Khachhang;
import model.Quocgia;

/**
 * Servlet implementation class Quantris
 */
@WebServlet("/Quantris/*")
public class Quantris extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quantris() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loai = request.getRequestURI();
		if(loai.contains("dangnhap"))
		{
			request.getRequestDispatcher("/view/Quantris/dangnhap.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loai = request.getRequestURI();
		if(loai.contains("dangnhap"))
		{
			try {
				String md5Hex = DigestUtils .md5Hex(request.getParameter("matkhau")).toUpperCase();
				KhachDao dangnhap = new KhachDao();
				Khachhang dn = dangnhap.dangnhapquantri(request.getParameter("tennguoidung"), md5Hex);
				
				System.out.println(dn.getTennguoidung());
				if(dn != null)
				{
					HttpSession ss = request.getSession();
					ss.setAttribute("khachhang", dn);
					ss.setAttribute("quanly", "quanly");
					response.sendRedirect("/doan/Trangchu");
				}
				else
				{
					request.getRequestDispatcher("/view/Quantris/dangnhap.jsp").forward(request, response);
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
