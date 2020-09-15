/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene;

import databank.TblProduct;
import databank.adapter.HibernateFactory;
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
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author zenodotus
 */
public class Indexering {
    
    public void indexering(String queryStr) throws Exception {
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
                maakIndex(w, producten.get(i).getNaam(), producten.get(i).getOpmerking());
            }
            w.close();
            Query q = new QueryParser("Naam", analyzer).parse(queryStr);
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;
            
            System.out.println("Found " + hits.length + " hits.");
            for(int i = 0; i < hits.length; ++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                System.out.println((i + 1) + ". " + d.get("Naam") + "\t" + d.get("beschrijving"));
            }
            reader.close();
            }
            
     
    
    
    public void maakIndex(IndexWriter w, String naam, String beschrijving) {
        try {
       
        
        Document doc = new Document();
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
