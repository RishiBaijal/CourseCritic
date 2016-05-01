package demo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public List<Servlet_class1> videoList = new ArrayList<>();

	public Servlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setContentType("text/plain");
		if (videoList.isEmpty()) {
			response.getOutputStream().println("The video list is empty.");
		} else {
			for (Servlet_class1 servlet_class1 : videoList) {
				response.getOutputStream().println("Name: " + servlet_class1.getName());
				response.getOutputStream().println("Type: " + servlet_class1.getType());
				response.getOutputStream().println("Uploader: " + servlet_class1.getUploader());
				response.getOutputStream().println("Size: " + servlet_class1.getSize());
				response.getOutputStream().println("Date: " + servlet_class1.getDate());
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// // TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/plain");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String uploader = request.getParameter("uploader");
		String size = request.getParameter("size");
		String date = request.getParameter("date");
		if (name == null || type == null || uploader == null || size == null || date == null) {
			response.sendError(400, "Missing arguments.");
		} else {
			Servlet_class1 servlet_class1 = new Servlet_class1(name, type, uploader, size, date);
			try
			{
				videoList.add(servlet_class1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			response.getOutputStream().println("Video has been added.");
		}
	}

}
