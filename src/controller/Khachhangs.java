package controller;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import dao.CtdhDao;
import dao.DonhangDao;
import dao.GiohangDao;
import dao.KhachDao;
import dao.QuocgiaDao;
import dao.SachDao;
import dao.SachyeuthichDao;
import dao.TacgiaDao;
import dao.TheloaiDao;
import dao.YeuthichDao;
import model.Ctdh;
import model.Donhang;
import model.Giohang;
import model.Khachhang;
import model.Quocgia;
import model.Sach;
import model.Tacgia;
import model.Theloai;

/**
 * Servlet implementation class Khachhangs
 */
@MultipartConfig
@WebServlet("/Khachhangs/*")
public class Khachhangs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Khachhangs() {
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
			QuocgiaDao qg = new QuocgiaDao();
			List<Quocgia> lqg = qg.findall();
			request.setAttribute("list", lqg);
			request.getRequestDispatcher("/view/Khachhangs/Create.jsp").forward(request, response);
		}
		if(loai.contains("dangnhap"))
		{
			request.getRequestDispatcher("/view/Khachhangs/dangnhap.jsp").forward(request, response);
		}
		if(loai.contains("dangxuat"))
		{
			HttpSession ss = request.getSession();
			ss.removeAttribute("khachhang");
			ss.removeAttribute("quanly");
			request.getRequestDispatcher("/view/Home/Index.jsp").forward(request, response);
		}
		if(loai.contains("Edit"))
		{
			QuocgiaDao qg = new QuocgiaDao();
			List<Quocgia> lqg = qg.findall();
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			request.setAttribute("item",kh);
			request.setAttribute("list", lqg);
			request.getRequestDispatcher("/view/Khachhangs/Edit.jsp").forward(request, response);
		}
		if(loai.contains("lichsuhang"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			DonhangDao dondao = new DonhangDao();
			List<Donhang> ls = dondao.findall();
			List<Donhang> listdon = new ArrayList<Donhang>();
			for(Donhang d : ls)
			{
				
				if(d.getKhachhang().getMakhachhang().toString().equalsIgnoreCase(kh.getMakhachhang().toString()))
				{
					listdon.add(d);
				}
			}
			System.out.println(listdon.size());
			request.setAttribute("list", listdon);
			request.getRequestDispatcher("/view/Khachhangs/lichsuhang.jsp").forward(request, response);
		}
		if(loai.contains("dathang"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			if(kh == null)
			{
				response.sendRedirect("/doan/Trangchu");
				return;
			}
			KhachDao kd = new KhachDao();
			Khachhang g = kd.findId((kh.getMakhachhang()));
			
			List<Giohang> listgiohang = g.getGiohangs();
			
			DonhangDao dondao = new DonhangDao();
			Donhang dh = new Donhang();
			dh.setKhachhang(g);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now(); 
			dh.setNgaydat(dtf.format(now).toString());
			GiohangDao ghdao = new GiohangDao();
			List<Giohang> lgiohang = ghdao.getKhachhang(g.getMakhachhang().toString());
			int tongtien = 0;
			int km = 0;
			int canthanhtoan =0;
			for(Giohang gh : lgiohang)
			{
				tongtien += gh.getSach().getGia()* gh.getSl();
				km += gh.getSl()* gh.getSach().getGiamgia();
			}
			canthanhtoan = tongtien - km;
			dh.setTongtien(tongtien);
			dondao.insert(dh);
			int madon = dondao.getmadon(g.getMakhachhang().toString());

			
			CtdhDao ctdon = new CtdhDao();
			for(Giohang gh : lgiohang)
			{
				ctdon.addct(gh.getSach().getMasach().toString(), madon, gh.getSl(), gh.getSach().getGia());

			}
			ghdao.removegiohang(g.getMakhachhang().toString());
			request.getRequestDispatcher("/view/Khachhangs/dathang.jsp").forward(request, response);
			
		}
		if(loai.contains("themgiohang"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			KhachDao kd = new KhachDao();
			Khachhang g = kd.findId((kh.getMakhachhang()));
			String id = request.getParameter("id");
			
			GiohangDao ghdao = new GiohangDao();
			ghdao.addgiohang(id, g.getMakhachhang().toString());
			response.sendRedirect("/doan/Khachhangs/giohang");
			return;
			
			
		}
		if(loai.contains("xoagiohang"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			KhachDao kd = new KhachDao();
			Khachhang g = kd.findId((kh.getMakhachhang()));
			String id = request.getParameter("id");
			
			GiohangDao ghdao = new GiohangDao();
			ghdao.removegiohang(id, g.getMakhachhang().toString());
			response.sendRedirect("/doan/Khachhangs/giohang");
			return;
			
			
		}
		if(loai.contains("giohang"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			if(kh == null)
			{
				response.sendRedirect("/doan/Trangchu");
				return;
			}
			KhachDao kd = new KhachDao();
			Khachhang g = kd.findId((kh.getMakhachhang()));
			
			
			GiohangDao ghdao = new GiohangDao();
			
			List<Giohang> listgiohang = ghdao.getKhachhang(g.getMakhachhang().toString());
			int tongtien = 0;
			int km = 0;
			int canthanhtoan =0;
			for(Giohang gh : listgiohang)
			{
				tongtien += gh.getSach().getGia()* gh.getSl();
				km += gh.getSl()* gh.getSach().getGiamgia();
			}
			canthanhtoan = tongtien - km;
			request.setAttribute("tongtien", tongtien);
			request.setAttribute("km", km);
			request.setAttribute("canthanhtoan", canthanhtoan);
			request.setAttribute("list", listgiohang);
			
			request.getRequestDispatcher("/view/Khachhangs/giohang.jsp").forward(request, response);
		}
		
		if(loai.contains("themyeuthich"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			String id = request.getParameter("id");
			
			YeuthichDao ghdao = new YeuthichDao();
			ghdao.addyeuthich(id, kh.getMakhachhang().toString());
			response.sendRedirect("/doan/Khachhangs/yeuthich");
			return;
			
			
		}
		if(loai.contains("xoayeuthich"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			String id = request.getParameter("id");
			
			YeuthichDao ghdao = new YeuthichDao();
			ghdao.removeyeuthich(id, kh.getMakhachhang().toString());
			response.sendRedirect("/doan/Khachhangs/yeuthich");
			return;
			
			
		}
		if(loai.contains("yeuthich"))
		{
			HttpSession ss = request.getSession();
			Khachhang kh = (Khachhang) ss.getAttribute("khachhang");
			
			YeuthichDao sdao = new YeuthichDao();
			List<Sach> lists = sdao.getKhachhang(kh.getMakhachhang().toString());
			request.setAttribute("list", lists);
			
			request.getRequestDispatcher("/view/Khachhangs/yeuthich.jsp").forward(request, response);
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
			Khachhang tl = new Khachhang();
			String p1 = request.getParameter("matkhau");
			String p2 = request.getParameter("matkhau2");
			
			if(p1.equalsIgnoreCase(p2) == false)
			{
				System.out.println(p1);
				System.out.println(p2);
				return;
			}
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				
				String md5Hex = DigestUtils .md5Hex(p1).toUpperCase();
				tl.setMatkhau(md5Hex);
				
				QuocgiaDao qgd = new QuocgiaDao();
				Quocgia qg = qgd.findId(Integer.parseInt(request.getParameter("maquocgia") ));
				tl.setQuocgia(qg);
				KhachDao tldao = new KhachDao();
				tldao.insert(tl);
				request.getRequestDispatcher("/view/Khachhangs/dangnhap.jsp").forward(request, response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(loai.contains("dangnhap"))
		{
			try {
				String md5Hex = DigestUtils .md5Hex(request.getParameter("matkhau")).toUpperCase();
				KhachDao dangnhap = new KhachDao();
				Khachhang dn = dangnhap.dangnhap(request.getParameter("tennguoidung"), md5Hex);
				if(dn != null)
				{
					HttpSession ss = request.getSession();
					ss.setAttribute("khachhang", dn);
					System.out.println(dn.getTenkhachhang());
					//request.setAttribute("khachhang", dn);
					response.sendRedirect("/doan/Trangchu");
				}
				else
				{
					request.getRequestDispatcher("/view/Khachhangs/dangnhap.jsp").forward(request, response);
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		if(loai.contains("EditMK"))
		{
			String id = request.getParameter("makhachhang");
			KhachDao tldao = new KhachDao();
			Khachhang tl = tldao.findId(Long.parseLong(id));
			
			String mkht = request.getParameter("cpass");
			String mkm = request.getParameter("matkhau");
			String mkm2 = request.getParameter("matkhau");
			
			if(mkm.equalsIgnoreCase(mkm2) == false)
				return;
			String md5Hex = DigestUtils .md5Hex(mkm).toUpperCase();
			String md5Hexl = DigestUtils .md5Hex(mkht).toUpperCase();
			if(md5Hexl.equalsIgnoreCase(tl.getMatkhau() ) == false )
				return;
			tl.setMatkhau(md5Hex);
			tldao.update(tl);
			response.sendRedirect("/doan/Trangchu");
		}
		if(loai.contains("Edit"))
		{
			Khachhang tl = new Khachhang();
			try {
				BeanUtils.populate(tl, request.getParameterMap());
				Part part = request.getPart("anh");
				String rpath = request.getServletContext().getRealPath("images/user");
				String fname = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if(part.getSubmittedFileName() != "" )
				{
					part.write(rpath+"/"+ fname);
					tl.setAnhdaidien(fname);
				}
				
				QuocgiaDao qgd = new QuocgiaDao();
				Quocgia qg = qgd.findId(Integer.parseInt(request.getParameter("maquocgia") ));
				tl.setQuocgia(qg);
				KhachDao tldao = new KhachDao();
				tldao.update(tl);
				HttpSession ss = request.getSession();
				ss.removeAttribute("khachhang");
				ss.setAttribute("khachhang", tl);
				response.sendRedirect("/doan/Trangchu");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}

}
