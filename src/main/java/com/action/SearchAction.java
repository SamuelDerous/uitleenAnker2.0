/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.TblProduct;
import databank.dao.ProductDao;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author zenodotus
 */
public class SearchAction implements Action {
    
    private String search;
    private String lid;
    private ArrayList<Document> lijst;

    @Override
    public String execute() throws Exception {
        try {
                lijst = indexering(search);
                return SUCCESS;
                
            } catch(Exception ex) {
                ex.printStackTrace();
                return ERROR;
            }
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public ArrayList<Document> getLijst() {
        return lijst;
    }

    public void setLijst(ArrayList<Document> lijst) {
        this.lijst = lijst;
    }
    
    
    
    public ArrayList<Document> indexering(String queryStr) throws Exception {
    
            boolean correct = true;
            StandardAnalyzer analyzer = new StandardAnalyzer();
            Directory index = new RAMDirectory();
            
           IndexWriterConfig config = new IndexWriterConfig(analyzer);
            
            IndexWriter w = new IndexWriter(index, config);
            ProductDao productDao = new ProductDao();
            List<TblProduct> producten = productDao.getAlleProducten();
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
    
}
