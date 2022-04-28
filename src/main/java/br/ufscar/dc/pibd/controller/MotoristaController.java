package br.ufscar.dc.pibd.controller;

import br.ufscar.dc.pibd.dao.MotoristaDAO;
import br.ufscar.dc.pibd.dao.VeiculoDAO;
import br.ufscar.dc.pibd.dao.CorridaDAO;
import br.ufscar.dc.pibd.domain.Motorista;
import br.ufscar.dc.pibd.domain.User;
import br.ufscar.dc.pibd.domain.Veiculo;
import br.ufscar.dc.pibd.domain.Corrida;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/motoristas/*")
public class MotoristaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MotoristaDAO dao;

    private CorridaDAO daoCorrida;

    private VeiculoDAO daoVeiculo;

    @Override
    public void init() {
        dao = new MotoristaDAO();
        daoCorrida = new CorridaDAO();
        daoVeiculo = new VeiculoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/corridas":
                    apresentaCorridasFeitas(request, response);
                    break;
                case "/corridasPendentes":
                    apresentaCorridasPendentes(request, response);
                    break;
                case "/carros":
                    apresentaCarros(request, response);
                    break;
                case "/carro":
                    apresentaCarro(request, response);
                    break;
                case "/adicionaCarro":
                    adicionaCarro(request, response);
                    break;
                case "/deletaCarro":
                    deletaCarro(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            System.out.print("cheguei no exception");
            throw new ServletException(e);
        }

    }

    private void apresentaCorridasFeitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User userLogged = (User) request.getSession().getAttribute("usuarioLogado");

        Motorista motoristaFisica = dao.getFisicaFromMotById(userLogged.getId()); // Recupera a pessoa fisica de motorista
        
        String mesAno = request.getParameter("monthYear");
        Integer year = null;
        Integer month = null;

        //if (userLogged == null) response.getWriter().println("null");
        //if (userLogged != null) response.getWriter().println("not null");

        List<Corrida> corridas = new ArrayList<>();
        Double totalRecebido = 0.0;
        Integer corridasTotais = 0;

        if(mesAno!=null){
            String []yearMonth = mesAno.split("-");
            year = Integer.parseInt(yearMonth[0]);
            month = Integer.parseInt(yearMonth[1]);
        }

        if(year == null){
            year = Calendar.getInstance().get(Calendar.YEAR);
  
        }
        if(month == null){
            month = Calendar.getInstance().get(Calendar.MONTH); 
            month = month + 1;  
        }

        corridas = daoCorrida.getAllCorridasByMotoristaMesEAno(motoristaFisica.getCpf(), year, month);
        totalRecebido = dao.totalValorMotoristaMesEAno(motoristaFisica.getCpf(), year, month);
        corridasTotais = dao.totalCorridasMotoristaMesEAno(motoristaFisica.getCpf(), year, month);

        request.setAttribute("corridas", corridas);
        request.setAttribute("totalRecebido", totalRecebido);
        request.setAttribute("corridasTotais", corridasTotais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/motorista/corridasFeitas.jsp");

        dispatcher.forward(request, response);
    }

    private void apresentaCorridasPendentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userLogged = (User) request.getSession().getAttribute("usuarioLogado");

        Motorista motoristaFisica = dao.getFisicaFromMotById(userLogged.getId());

        String mesAno = request.getParameter("monthYear");
        Integer year = null;
        Integer month = null;

        if(mesAno!=null && !mesAno.isEmpty()) {
            String []yearMonth = mesAno.split("-");
            year = this.parseInt(yearMonth[0]);
            month = this.parseInt(yearMonth[1]);
        } else {
          year = Calendar.getInstance().get(Calendar.YEAR);
          month = Calendar.getInstance().get(Calendar.MONTH) + 1; 
        }

        List<Corrida> corridasPendentes = daoCorrida.getAllCorridasPendentesByMotoristaMesEAno(motoristaFisica.getCpf(), year, month);

        request.setAttribute("corridasPendentes", corridasPendentes);
        request.setAttribute("totalCorridasPendentes", corridasPendentes.size());
        request.setAttribute("year", year);
        request.setAttribute("month", String.format("%02d", month));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/motorista/corridasPendentes.jsp");

        dispatcher.forward(request, response);
    }

    private void apresentaCarros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userLogged = (User) request.getSession().getAttribute("usuarioLogado");

        Motorista motoristaFisica = dao.getFisicaFromMotById(userLogged.getId());

        List<Veiculo> veiculos = daoVeiculo.getAllVeiculosByMotorista(motoristaFisica.getCpf());

        request.setAttribute("veiculos", veiculos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/motorista/meusCarros.jsp");

        dispatcher.forward(request, response);
    }

    private void apresentaCarro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userLogged = (User) request.getSession().getAttribute("usuarioLogado");

        Motorista motoristaFisica = dao.getFisicaFromMotById(userLogged.getId());

        String chassi = request.getParameter("chassi");

        Veiculo veiculo = null;

        if (chassi != null && !chassi.isEmpty())
          veiculo = daoVeiculo.getVeiculoByMotoristaChassi(motoristaFisica.getCpf(), chassi);

        request.setAttribute("veiculo", veiculo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/motorista/detalhamentoCarro.jsp");

        dispatcher.forward(request, response);
    }

    private void adicionaCarro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userLogged = (User) request.getSession().getAttribute("usuarioLogado");

        Motorista motoristaFisica = dao.getFisicaFromMotById(userLogged.getId());

        String chassi = request.getParameter("chassi");
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        Integer ano = this.parseInt(request.getParameter("ano"));
        String cor = request.getParameter("cor");
        Integer maxOcupacao = this.parseInt(request.getParameter("maxOcupacao"));
        String garagemCep = request.getParameter("garagemCep");
        Integer garagemNum = this.parseInt(request.getParameter("garagemNum"));
        Integer garagemNumVaga = this.parseInt(request.getParameter("garagemNumVaga"));

        Veiculo veiculo = new Veiculo(chassi, cor, placa, ano, modelo, marca, maxOcupacao, garagemCep, garagemNum, garagemNumVaga, motoristaFisica.getCpf());

        daoVeiculo.add(veiculo);

        response.sendRedirect(request.getContextPath() + "/motoristas/carros");
    }

    private void deletaCarro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chassi = request.getParameter("chassi");

        if (chassi != null && !chassi.isEmpty())
          daoVeiculo.delete(chassi);

        response.sendRedirect(request.getContextPath() + "/motoristas/carros");
    }

    private Integer parseInt(String str) {
      try {
        return Integer.parseInt(str);
      } catch (NumberFormatException e) {
        return 0;
      }
    }
}