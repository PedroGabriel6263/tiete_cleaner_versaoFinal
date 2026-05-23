package models;

import java.awt.*;
import javax.swing.*;


public class Item {
  private int x,y;
  private int largura = 65;
  private int altura = 65;    // criação dos lixos
  private int velocidade = 4;
  private String tipo;
  public String getTipo(){
      return tipo;
  }

    private static Image bananaImg;
    private static Image caixaDePapelaoImg;
    private static Image garrafaPlasticaImg;
    private static Image garrafavidroImg;
    private static Image lataImg;

    static {
        bananaImg = new ImageIcon(
                Item.class.getResource("/resources2.0/banana.png")
        ).getImage();

        caixaDePapelaoImg = new ImageIcon(
                Item.class.getResource("/resources2.0/caixaDePapelao.png")
        ).getImage();

        garrafaPlasticaImg = new ImageIcon(
                Item.class.getResource("/resources2.0/garrafaPlastica.png")
        ).getImage();

        garrafavidroImg = new ImageIcon(
                Item.class.getResource("/resources2.0/garrafavidro.png")
        ).getImage();

        lataImg = new ImageIcon(
                Item.class.getResource("/resources2.0/lata.png")
        ).getImage();
    }


    public Item(int x, int y, String tipo){
          this.x = x;
          this.y = y;
          this.tipo = tipo;
      }

      public void update(){
          x -= velocidade; //move item para direção do player
      }

      public void  desenhar(Graphics g){
          if(tipo.equals("papel")){
              g. drawImage(caixaDePapelaoImg, x, y, largura, altura, null);

          }else if(tipo.equals("plastico")){   // equals compara conteudo de texto
              g.drawImage(garrafaPlasticaImg, x, y, largura, altura, null);

          } else if (tipo.equals("vidro")) { // verifica o tipo e define a corz
              g. drawImage(garrafavidroImg, x, y, largura, altura, null);

          }else if(tipo.equals("metal")){
              g. drawImage(lataImg, x, y, largura, altura, null);

          }else if(tipo.equals("organico")){
              g. drawImage(bananaImg, x, y, largura, altura, null);
          }


      }

    public int getX(){ return x; }
    public int getY(){ return y; }               //acessos
    public int getLargura(){ return largura; }
    public int getAltura(){ return altura; }
}
