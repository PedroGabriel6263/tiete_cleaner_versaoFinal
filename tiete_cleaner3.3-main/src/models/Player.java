    package models;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyEvent;

    public class Player {
        private int x,y;
        private int dx,dy;
        private Image imagem; // atributo
        private int altura;
        private int largura;

        public Player(int larguraTela, int alturaTela){
            this.x = 100;
            this.y = 100;
            altura = alturaTela / 10;   // 10% da altura da tela
            largura = altura;
        }

        public void load(){
            ImageIcon seletorDeImagem = // pega a imagem do personagem
                    new ImageIcon(getClass().getResource("/resources2.0/surfista.png"));
            imagem = seletorDeImagem.getImage() // faz a variável imagem receber o arquivo do personagem
                    .getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        }

        public void atalhoTeclado(KeyEvent tecla){ // Lê e aciona entradas do teclado
            int codigo = tecla.getKeyCode();

            if(codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP){
                dy = -8;
            }

            if(codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN){
                dy = 8;
            }

            if(codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT){
                dx = -10;
            }

            if(codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
                dx = 8;
            }
        }


        public void atalhoDesselecionado (KeyEvent tecla){ // Lê e desaciona entradas do teclado
            int codigo = tecla.getKeyCode();

            if(codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_UP){
                dy = 0;
            }

            if(codigo == KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN){
                dy = 0;
            }

            if(codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT){
                dx = 0;
            }

            if(codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
                dx = 0;
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Image getImagem() {
            return imagem;
        }

        public void update(int larguraTela, int alturaTela){
            x += dx;
            y += dy;


            if (x < 0) x = 0;
            if (y < 0) y = 0;
            if(dx == 0){ //se o jogador esta parado ele retorna, simulando a força da correnteza.
                x -= 4;
            }


            if (x > larguraTela - largura) {
                x = larguraTela - largura;
            }

            if (y > alturaTela - altura) {
                y = alturaTela - altura;
            }
        }
    }
