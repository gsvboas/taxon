package br.ufscar.dc.pibd.controller.admin.agendamentos;

import br.ufscar.dc.pibd.domain.admin.agendamentos.AgendamentoForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/agendamentos/criar"})
public class CriarAgendamentoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("criar.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cnpj = req.getParameter("cnpj");
        String data = req.getParameter("data");
        String hora = req.getParameter("hora");
        AgendamentoForm agendamento = new AgendamentoForm(cnpj, data, hora);

        resp.sendRedirect("../agendamentos");
    }
}
