package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/loan")
public class LoanCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Loan Calculator</h2>");
        out.println("<form method='post'>");
        out.println("Loan Amount: <input name='amount'><br><br>");
        out.println("Interest Rate (%): <input name='rate'><br><br>");
        out.println("Years: <input name='years'><br><br>");
        out.println("<input type='submit' value='Calculate'>");
        out.println("</form>");
    }


    // return the data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double amount = Double.parseDouble(request.getParameter("amount"));
        double rate = Double.parseDouble(request.getParameter("rate"));
        int years = Integer.parseInt(request.getParameter("years"));

        double monthlyRate = rate / 100 / 12;
        int months = years * 12;

        double total_payment = amount * (monthlyRate * Math.pow(1 + monthlyRate, months));
                

        double payment = total_payment / (Math.pow(1 + monthlyRate, months) - 1);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Monthly Payment: $" +
                String.format("%.2f", payment) + "</h2>");
        out.println("<h2>Total Payments: " +
                String.format("%.0f", (double)months) + "</h2>");
        out.println("<h2>Total Cost: $" +
                String.format("%.2f", total_payment) + "</h2>");
        out.println("<br><a href='loan'>Back</a>");
    }
}
