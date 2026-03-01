package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/app") // go to /app after loading up site in tomcat. 
public class AdditionQuizServlet extends HttpServlet {

    public Vector<Integer> ones;
    public Vector<Integer> twos;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.ones = new Vector(5);
        this.twos = new Vector(5);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Addition Quiz!</h2>");
        out.println("<form method='post'>");
        int one;
        int two;
        for (int i = 0; i<5; i++) {
            one = (int)((Math.random()*100)%31);
            two = (int)((Math.random()*100)%31);
            out.println("" + one + " + " + two + " = <input name = '"+ i +"'> <br><br>");
            ones.add(one);
            twos.add(two);

        }
        out.println("<input type='submit' value='Submit Answers'>");
        out.println("</form>");
    }


    // return the data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Answers: </h2>");

        if (ones==null) {
            out.println("Whoops, you're not supposed to be here yet. Please go to addition-quiz/app <br><br>");
            
        } else {

        

        int numCorrect = 0;

        for (int i = 0; i < 5; i++) {
            int answer = Integer.parseInt(request.getParameter(""+i));
            int one = ones.get(i);
            int two = twos.get(i);
            if (answer == one+two) {
                out.println("" + one + " + " + two + " = " + answer + " Correct! <br><br>");
                numCorrect++;
            } else {
                out.println("" + one + " + " + two + " = " + answer + " Incorrect: " + (one+two) + "<br><br>");
            }
        }
        out.println("Number Correct: " + numCorrect);
    }
    }
}
