package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MenuInicial extends JPanel implements MouseListener {

    private Image fundoMenu;

    private Clip musica;

    private Rectangle botaoJogar =
            new Rectangle(0, 0, 250, 70);

    private Rectangle botaoSair =
            new Rectangle(0, 0, 250, 70);

    private JFrame janela;

    public MenuInicial(JFrame janela) {

        this.janela = janela;

        addMouseListener(this);

        setFocusable(true);

        fundoMenu = new ImageIcon(
                getClass().getResource("/resources2.0/Background.png")).getImage();

        //MUSICA DO MENU
         try {

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(
                                    "/resources2.0/audios/Interlude-Normal.wav"
                            )
                    );

            musica = AudioSystem.getClip();

            musica.open(audio);

            musica.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D graficos = (Graphics2D) g;

        // fundo
        g.drawImage(
                fundoMenu,
                0,
                0,
                getWidth(),
                getHeight(),
                null
        );

        // botões
        int centroX = (getWidth() - 250) / 2;

        botaoJogar.setBounds(
                centroX,
                getHeight() / 2 - 20,
                250,
                70
        );

        botaoSair.setBounds(
                centroX,
                getHeight() / 2 + 90,
                250,
                70
        );

        // botão jogar
        g.setColor(Color.WHITE);

        g.fillRoundRect(
                botaoJogar.x,
                botaoJogar.y,
                botaoJogar.width,
                botaoJogar.height,
                20,
                20
        );

        g.setColor(Color.BLACK);

        g.drawRoundRect(
                botaoJogar.x,
                botaoJogar.y,
                botaoJogar.width,
                botaoJogar.height,
                20,
                20
        );

        Font botaoFonte =
                new Font("Arial", Font.BOLD, 30);

        g.setFont(botaoFonte);

        String jogar = "JOGAR";

        FontMetrics fmJogar =
                g.getFontMetrics();

        int larguraJogar =
                fmJogar.stringWidth(jogar);

        g.drawString(
                jogar,
                botaoJogar.x +
                        (botaoJogar.width - larguraJogar) / 2,
                botaoJogar.y + 45
        );

        // botão sair
        g.setColor(Color.WHITE);

        g.fillRoundRect(
                botaoSair.x,
                botaoSair.y,
                botaoSair.width,
                botaoSair.height,
                20,
                20
        );

        g.setColor(Color.BLACK);

        g.drawRoundRect(
                botaoSair.x,
                botaoSair.y,
                botaoSair.width,
                botaoSair.height,
                20,
                20
        );

        String sair = "SAIR";

        FontMetrics fmSair =
                g.getFontMetrics();

        int larguraSair =
                fmSair.stringWidth(sair);

        g.drawString(
                sair,
                botaoSair.x +
                        (botaoSair.width - larguraSair) / 2,
                botaoSair.y + 45
        );
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (botaoJogar.contains(e.getPoint())) {

            Tutorial tutorial = new Tutorial(janela);

            janela.setContentPane(tutorial);

            janela.revalidate();

            janela.repaint();

            tutorial.requestFocusInWindow();
        }

        if (botaoSair.contains(e.getPoint())) {

            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}