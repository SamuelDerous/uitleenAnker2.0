/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import databank.TblProduct;
import databank.adapter.HibernateFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lucene.Indexering;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author zenodotus
 */
public class Zoeken extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String zoekterm = request.getParameter("txtZoeken");
            try {
                ArrayList lijst = indexering(zoekterm);
                request.setAttribute("documenten", lijst);
                RequestDispatcher view = request.getRequestDispatcher("zoeken.jsp");
                view.forward(request, response);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    public ArrayList<Document> indexering(String queryStr) throws Exception {
    SessionFactory factory = HibernateFactory.getSessionFactory();
            boolean correct = true;
            Session session = factory.openSession();
            org.hibernate.Query zoeken = session.createQuery("from TblProduct");
            StandardAnalyzer analyzer = new StandardAnalyzer();
            Directory index = new RAMDirectory();
            
                IndexWriterConfig config = new IndexWriterConfig(analyzer);
            
            IndexWriter w = new IndexWriter(index, config);
            List<TblProduct> producten = zoeken.list();
            for(int i = 0; i < producten.size(); i++) {
                maakIndex(w, producten.get(i).getId(), producten.get(i).getNaam(), producten.get(i).getOpmerking());
            }
            w.close();
            Query q = new QueryParser("Naam", analyzer).parse(queryStr);
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;
            ArrayList<Document> lst = new ArrayList();
            //System.out.println("Found " + hits.length + " hits.");
            for(int i = 0; i < hits.length; ++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                lst.add(d);
                //System.out.println((i + 1) + ". " + d.get("Naam") + "\t" + d.get("beschrijving"));
            }
            reader.close();
            return lst;
            }
            
     
    
    
    public void maakIndex(IndexWriter w, int nummer, String naam, String beschrijving) {
        try {
       
        
        Document doc = new Document();
        doc.add(new TextField("Nummer", "" + nummer, Field.Store.YES));
        doc.add(new TextField("Naam", naam, Field.Store.YES));
        if(beschrijving != null) {
            doc.add(new TextField("Beschrijving", beschrijving, Field.Store.YES));
        }
        else {
            doc.add(new TextField("Beschrijving", "", Field.Store.YES));
        }
        w.addDocument(doc);
        
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
