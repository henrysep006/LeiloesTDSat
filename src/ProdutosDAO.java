/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
     public void venderProduto(int id){
         try {
            conn = new conectaDAO().connectDB();
            
            String comand="update produtos set status='Vendido' where id=?";
            
            prep=conn.prepareStatement(comand);
              prep.setInt(1, id);
               prep.executeUpdate();
                conn.close();
         }catch(SQLException ex) {
          
             JOptionPane.showMessageDialog(null, "Algo deu errado");
        }
    }
    public void cadastrarProduto (ProdutosDTO produto) {
        
        
        try {
            conn = new conectaDAO().connectDB();
            
            String comand="insert into produtos values (?,?,?,?)";
          
            prep=conn.prepareStatement(comand);
              prep.setInt(1, listagem.size());
            prep.setString(2, produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());
            
            prep.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
           
        } catch (SQLException ex) {
          
             JOptionPane.showMessageDialog(null, "Não foi possível cadastrar esse produto " + ex.getMessage());
        }
          
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
         conn = new conectaDAO().connectDB();
         try{
             
             prep=conn.prepareStatement("SELECT * FROM produtos");
             
             resultset=prep.executeQuery();
            
             
           while(resultset.next()){
               
               ProdutosDTO prod =new ProdutosDTO();
               
               prod.setId(resultset.getInt("ID"));
               prod.setNome(resultset.getString("Nome"));
               prod.setValor(resultset.getInt("Valor"));
               prod.setStatus(resultset.getString("Status"));
               
               listagem.add(prod);
           }
         
           return listagem;
       
         }catch(SQLException ex) {
          
             JOptionPane.showMessageDialog(null, "Não foi possível acessar a lista de produtos");
        }
         return null;
    }
        
        
        
         public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
         conn = new conectaDAO().connectDB();
         try{
             
             prep=conn.prepareStatement("SELECT * FROM produtos WHERE status='Vendido'");
             
             resultset=prep.executeQuery();
             resultset.next();
             
           while(resultset.next()){
               
               ProdutosDTO prod =new ProdutosDTO();
               
               prod.setId(resultset.getInt("ID"));
               prod.setNome(resultset.getString("Nome"));
               prod.setValor(resultset.getInt("Valor"));
               prod.setStatus(resultset.getString("Status"));
               
               listagem.add(prod);
           }
          conn.close();
           return listagem;
       
         }catch(SQLException ex) {
          
             JOptionPane.showMessageDialog(null, "Não foi possível acessar a lista de produtos");
        }
         return null;
    }
        
   
    
    
    
    
        
}

