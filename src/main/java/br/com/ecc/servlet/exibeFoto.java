package br.com.ecc.servlet;

import br.com.ecc.util.Constantes;
import br.com.ecc.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/image")
public class exibeFoto extends HttpServlet{
	private static final long serialVersionUID = 1460571643688705941L;

	private String imagePath;


	public void init() throws ServletException {
	this.imagePath = Util.retornaPathFotoSistemaOperacional();
	}

	// Actions ------------------------------------------------------------------------------------
//https://www.caelum.com.br/apostila-java-web/servlets/#5-8-para-saber-mais-web-servlet-e-initparam-annotation
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestedImage = request.getParameter("imagem");
		File image = new File(imagePath, requestedImage);

		if (image.exists()) {
			String contentType = getServletContext().getMimeType(image.getName());
			response.reset();
			response.setContentType(contentType);
			response.setHeader("Content-Length", String.valueOf(image.length()));

			Files.copy(image.toPath(), response.getOutputStream());
		}
	}
}
