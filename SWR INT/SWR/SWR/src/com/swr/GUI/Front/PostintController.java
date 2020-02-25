/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.Service.ServiceComments;
import com.swr.Service.ServicePosts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.jfoenix.controls.JFXListView;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.swr.Entite.Likes;
import com.swr.Entite.User;
import com.swr.Service.ServiceLikes;
import com.swr.Service.ServiceUser;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Soulah
 */
public class PostintController implements Initializable {
    @FXML
    private Label nbnotif;
    private TextArea contenupost;
    @FXML
    private Button addpost;
    ServiceUser us = new ServiceUser();
          ServicePosts sp = new ServicePosts();
         List<Posts> a;
          ServiceComments sc = new ServiceComments();
          ServiceLikes sl = new ServiceLikes();
         List<Comments> b;
    @FXML
    private Button homebtn;
    @FXML
    private TextArea addposttxt;
    @FXML
    private TextArea addcmttxt;
    @FXML
    private Button addcmt;
    @FXML
    private JFXListView<Label> PostsLists;
    @FXML
    private JFXListView<Label> CommentsLists;
    @FXML
    private Button addpost1;
    @FXML
    private Button addpost2;
    @FXML
    private Button addpost3;
    @FXML
    private Button ModifyP;
    @FXML
    private Button DeleteC;
    @FXML
    private Button ModifyC;
    @FXML
    private Button addpost112;
    @FXML
    private Button Modifypost;
    @FXML
    private Button modifycmtbtn;
    @FXML
    private Button fbbtn;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView improfil;
    @FXML
    private ImageView logout;
    private javafx.scene.image.Image image;
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        addposttxt.setPromptText("Have Something in mind? Please Share it with us"); 
        addposttxt.getParent().requestFocus();
        addcmttxt.setPromptText("Have Something to add? Make this community great"); 
        addcmttxt.getParent().requestFocus();
      //  addcmttxt.setDisable(true);
            try {
       //  issLit();
      //  isSelected();
        ListPosts();
     
        
        
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        
        
        
      
    }
    public void setResNom() {
       
       
        
        
    }
/*
    public void setContenupost() {
        //this.contenupost.setText(a.get(0).getContenuP());
         try {
           a=sp.readAll();
           this.contenupost.setText(a.get(0).getContenuP());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
*/

    @FXML
    private void gotoo(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Homepage.fxml"));
            try {
                Parent root = loader.load();
                homebtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }
        public void setImage(javafx.scene.image.Image image) {
       this.improfil.setImage(image);
    }
    
    public void setLabelUser(String userlabel) {
        this.userlabel.setText(userlabel);
    }

    @FXML
    private void ajouterpost(ActionEvent event) throws SQLException {
        
          ObservableList<User> data =FXCollections.observableArrayList();
           User u= new User();
            
             u =us.listuser(userlabel.getText());
             /*   for(int i=0 ; i<data.size() ; i++){
              u= new User(data.get(i).getIdu(),data.get(i).getNom(),data.get(i).getPrenom());}*/
                 String contenuP = addposttxt.getText();
             Posts p = new Posts(contenuP,u);
            
             try {
          sp.ajouter1(p);
          postonFacebook(contenuP,u);
          PostsLists.getItems().clear();
          ListPosts();
         addposttxt.setText("");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    
    
    private void clear(){
    addposttxt.setText("");
    }

    @FXML
    private void ajoutercmt(ActionEvent event) throws SQLException {
          String contenuC = addcmttxt.getText();
           User u= new User();
            
            u =us.listuser(userlabel.getText());
          
   
          
            int index= PostsLists.getSelectionModel().getSelectedIndex();
           int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
                     Posts p=new Posts(id);
                     Comments c = new Comments(contenuC,p,u);
                      sc.ajouter(c);
                      sp.nbcmtPlus(id);
                      CommentsLists.getItems().clear();
                      ListComments(id);
                      addcmttxt.setText("");
                     
                 
                 
                    }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
            
         
    }
    
    
        public void ListPosts() throws SQLException
    {
         
        ObservableList<Posts> data =FXCollections.observableArrayList();
        data= (ObservableList<Posts>) sp.readAll();
       
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
             User x=sp.listuser(data.get(i).getIduser());
            Label lbl = new Label(""+data.get(i).getContenuP()+"\n\n"+ "| Username : "+x.getNom()+"  "+x.getPrenom()+"       | Views : "+data.get(i).getViews()+"     | Date : "+data.get(i).getDateP());
            PostsLists.getItems().add(lbl);
           
            
            
        }
        PostsLists.setExpanded(true);
        PostsLists.depthProperty().set(1);
        PostsLists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
       
    }
         public void ListComments(int x) throws SQLException
    {
         
    ObservableList<Comments> data =FXCollections.observableArrayList();
        data= (ObservableList<Comments>) sc.getpostscomments(x);
        
         for(int i=0 ; i<data.size() ; i++)
        {
             User z=sp.listuser(data.get(i).getIduser());
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label(data.get(i).getContenuC());
            CommentsLists.getItems().add(lbl);
            
        }
        CommentsLists.setExpanded(true);
        CommentsLists.depthProperty().set(1);
        CommentsLists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

      
       
    }
         
     public int isSelected() throws SQLException{
         
          int id = 0;
         while(true){
          int index = issLit();

         ObservableList<Integer> l= FXCollections.observableArrayList();
       
                  try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                  id=l.get(0);}
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
          
                    
                  //int id=l.get(index);
                  
           return id;     
         }
        
     }
     public int issLit(){
          int index = 0;//PostsLists.getSelectionModel().getSelectedIndex();
          return index;
     }
     
     public void like() throws SQLException{
       
     }

    @FXML
    private void likeaction(ActionEvent event) throws SQLException {
        
     int index= PostsLists.getSelectionModel().getSelectedIndex();
           int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        ObservableList<User> data =FXCollections.observableArrayList();
         ObservableList<Posts> dataX =FXCollections.observableArrayList();
          Posts p=new Posts();
         User u= new User();
          u =us.listuser(userlabel.getText());
                /*for(int i=0 ; i<data.size() ; i++){
              u= new User(data.get(i).getIdu(),data.get(i).getNom(),data.get(i).getPrenom());}*/
       
        
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{ 
                        id=l.get(index);
                    
                         dataX= (ObservableList<Posts>)  sp.list(id);
                          for(int i=0 ; i<dataX.size() ; i++){
                           p= new Posts(id,dataX.get(i).getContenuP(),dataX.get(i).getNbcmt(),dataX.get(i).getViews(),dataX.get(i).getLikes(),dataX.get(i).getReport(),dataX.get(i).getIduser(),dataX.get(i).getDateP());
                          }
                       Likes lk=new Likes(u,id);
                       
                    if(!sl.checkstatus(u.getIdu(), p.getIdP())){
                     sl.ajouter(lk);
                     sp.like(id);
                     System.out.println(id);
                     
                     
                    }
                    else{System.out.println("already liked");}
                       
                 
                 
                    }
                    }catch (SQLException ex) {
            System.out.println(ex.getMessage());}
            } 

  

    @FXML
    private void commentAction(ActionEvent event) {
       int index= PostsLists.getSelectionModel().getSelectedIndex();
        //System.out.println();
       
    
       int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
                     
                     CommentsLists.getItems().clear();
                   sp.views(id);
                     ListComments(id);
                    }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }

    @FXML
    private void ReportAction(ActionEvent event) {
        
    }

    @FXML
    private void modifyPost(ActionEvent event) throws SQLException {
         int index= PostsLists.getSelectionModel().getSelectedIndex();
       ObservableList<Posts> data =FXCollections.observableArrayList();
   
        User u= new User();
          u =us.listuser(userlabel.getText());
          String o="Not your Post";
    
       int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
                    
                      data= (ObservableList<Posts>) sp.slctd(id,u.getIdu());
                      if(data.isEmpty()){alert(o);}
                     for(int i=0 ; i<data.size() ; i++)
                      {
            
                      addposttxt.setText(data.get(i).getContenuP());
           
            
                          }
                     
                   
                     PostsLists.getItems().clear();
                      ListPosts();
                     
                    }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        
    }

    @FXML
    private void deleteP(ActionEvent event) throws SQLException {
         int index= PostsLists.getSelectionModel().getSelectedIndex();
        //System.out.println();
        User u= new User();
         u =us.listuser(userlabel.getText());
          String o="Not yout Post";
    
       int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
                     boolean r=sp.deleteX(id,u.getIdu());
                     if(!r){alert(o);}
                     else{System.out.println("Done");}
                     
                    
                     PostsLists.getItems().clear();
                      ListPosts();
                     
                    }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }

    @FXML
    private void ModifyCmt(ActionEvent event) throws SQLException {
          int index= PostsLists.getSelectionModel().getSelectedIndex();
     Label a= CommentsLists.getSelectionModel().getSelectedItem();
      User u= new User();
       u =us.listuser(userlabel.getText());
     String o="Not your Comment";
        System.out.println(a.getText());
        Comments cmt=sc.getcomment(a.getText(),u.getIdu());
        
        if(cmt.getIdC()==0){alert(o);}
            
        //System.out.println(cmt.getContenuC());
        else
        addcmttxt.setText(cmt.getContenuC());
     
         
            }
                   //  PostsLists.getItems().clear();
                     // ListPosts();
                     
          
    

    @FXML
    private void deleteCmnt(ActionEvent event) throws SQLException {
        Label a= CommentsLists.getSelectionModel().getSelectedItem();
          User u= new User();
           u =us.listuser(userlabel.getText());
           String o="Not your Comment";
        //System.out.println(a.getText());
        Comments cmt=sc.getcomment(a.getText(),u.getIdu());
        if(cmt.getIdC()== 0){alert(o);}
        else{
        System.out.println(cmt.getIdC());
       
    
         
            
       int id=-1;
        ObservableList<Integer> l= FXCollections.observableArrayList();
        try {
                      
                
                  
                    if(cmt.getIdC()!=0){
                          sc.deleteX(cmt.getIdC(), u.getIdu());
                    sp.nbcmtDown(id);
                     CommentsLists.getItems().clear();
                     ListComments(cmt.getIdP());}
                    else
                        alert(o);
                        
                    
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        }
    }

    @FXML
    private void modifyPostt(ActionEvent event) throws SQLException {
        int index= PostsLists.getSelectionModel().getSelectedIndex();
        
         ObservableList<User> data =FXCollections.observableArrayList();
         ObservableList<Integer> l= FXCollections.observableArrayList();
           User u= new User();
           int id=-1;
            
             u =us.listuser(userlabel.getText());
             /*   for(int i=0 ; i<data.size() ; i++){
              u= new User(data.get(i).getIdu(),data.get(i).getNom(),data.get(i).getPrenom());}*/
                 String contenuP = addposttxt.getText();
             Posts p = new Posts(contenuP,u);
              try {
                    l= sp.listid();
                    if(index==-1){System.out.println("hehehe");}
                    else{
                        
                     id=l.get(index);
          sp.Modifiy(p,id);
          PostsLists.getItems().clear();
          ListPosts();
         addposttxt.setText("");}
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        
    }

    @FXML
    private void ModifyComment(ActionEvent event) throws SQLException {
         
     Label a= CommentsLists.getSelectionModel().getSelectedItem();
        //System.out.println(a.getText());
       
       
       String z= addcmttxt.getText();
      User u= new User();
         
             u =us.listuser(userlabel.getText());
              Comments cmt=sc.getcomment(a.getText(),u.getIdu());
               System.out.println(cmt.getIdC());
             sc.Modify(z, u,cmt.getIdC());
             CommentsLists.getItems().clear();
                     ListComments(cmt.getIdP());
                  
    }
    
    public void alert(String a){
        Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Warning Dialog");
alert.setHeaderText("Look, a Warning Dialog");
alert.setContentText(a);

alert.showAndWait();
    }

    @FXML
    private void postonFb(ActionEvent event) {
          String contenuP = addposttxt.getText();
        String accessToken="EAApI7rQZCTmUBAGtNCF9IqyWhnrrYnFYLlF4rYtTcZBokVKVKTtASxJzViQQIB8UfwoJDCIfxmAqTZBPIZB8SlaZABjUACey0obR3ekAY62hSdkj6LlCNy4sNrzSlDnSKXIEYcUBLsj3iRehkrRiyf2OmofcejtJZCdxz487yhv5DEgB3FNgTHhyxlqOZATT8YLOZA5mcP0aKgZDZD";
        FacebookClient fbClient=new DefaultFacebookClient(accessToken,Version.VERSION_3_2);
        FacebookType response = fbClient.publish("115735156639592/feed",FacebookType.class, Parameter.with("message",contenuP));
        System.out.println("fb.com/"+response.getId());
    }
    
    public void postonFacebook(String p ,User u){
        String accessToken="EAApI7rQZCTmUBAN0QkPR9yrRYzuE74A2ZBY0bub6kZBfibYMfvmwX69UTzlVc5RzJkf3RlCs5uYb6oDXfdqVhjH8nTZBQb83RZCJXZCITeOm1lo3iLrRN2tKqLv7UBtVMuIjlvn4BuUTOMJdmE296GqV5AKbCJFLt1EqN5U30dH4CgvkG4ZBH8FiFegUxZA51ZAS74jGXbvOW9gZDZD";
        FacebookClient fbClient=new DefaultFacebookClient(accessToken,Version.VERSION_3_2);
        FacebookType response = fbClient.publish("115735156639592/feed",FacebookType.class, Parameter.with("message",p+"      | Username : "+u.getNom()+ " "+u.getPrenom() ));
        System.out.println("fb.com/"+response.getId());
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }
    
    

    
}


