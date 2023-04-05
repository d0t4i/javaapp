package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.SachbanchayDao;
import dao.SachyeuthichDao;
import model.Sachbanchay;
import model.Sachyeuthich;
import model.Khachhang;
import dao.KhachDao;
import dao.QuocgiaDao;
import dao.SachDao;
import model.Quocgia;
import model.Sach;
/**
 * Servlet implementation class Trangchu
 */
@WebServlet("/Trangchu")
public class Trangchu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trangchu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SachbanchayDao sachbanchay = new SachbanchayDao();
		List<Sach> listchay =  sachbanchay.get();
		
		SachyeuthichDao sachyeuthich = new SachyeuthichDao();
		List<Sach> listyeuthich = sachyeuthich.get();		
		SachDao sach = new SachDao();
		List<Sach> sachm = sach.findall(0, 10);
		
		
		System.out.println(listyeuthich.size());
		
		//KhachDao khach = new KhachDao();
		//Khachhang kh = khach.findId(3L);
		//System.out.println(kh.getTenkhachhang());
		
		//Khachhang kh = new Khachhang();
		
		//Quocgia qg = new Quocgia();
		//qg.setMaquocgia(1);
		//kh.setMakhachhang(0L);
		//kh.setTenkhachhang("test");
		//kh.setQuocgia(qg);
		//khach.insert(kh);
		
		request.setAttribute("yeuthich", listyeuthich);
		request.setAttribute("chay", listchay);
		request.setAttribute("sach", sachm);
		
		request.getRequestDispatcher("/view/Home/Index.jsp").forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
