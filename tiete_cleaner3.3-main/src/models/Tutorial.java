package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tutorial extends JPanel implements MouseListener {

    private JFrame janela;

    private Rectangle botaoContinuar =
            new Rectangle(0, 0, 300, 70);

    private Image fundoTutorial;

    public Tutorial(JFrame janela) {

        this.janela = janela;

        addMouseListener(this);

        setFocusable(true);

        fundoTutorial = new ImageIcon(
                getClass().getResource(
                        "/resources2.0/Background.png"
                )
        ).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D graficos =
                (Graphics2D) g;

        graficos.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        // fundo
        g.drawImage(
                fundoTutorial,
                0,
                0,
                getWidth(),
                getHeight(),
                null
        );

        // camada escura
        g.setColor(new Color(0,0,0,160));

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // título
        g.setColor(Color.WHITE);

        Font tituloFonte =
                new Font("Arial", Font.BOLD, 60);

        g.setFont(tituloFonte);

        String titulo = "COMO JOGAR";

        FontMetrics fmTitulo =
                g.getFontMetrics();

        int larguraTitulo =
                fmTitulo.stringWidth(titulo);

        g.drawString(
                titulo,
                (getWidth() - larguraTitulo) / 2,
                120
        );

        // texto tutorial
        Font textoFonte =
                new Font("Arial", Font.PLAIN, 28);

        g.setFont(textoFonte);

        String linha1 =
                "A / ←  = MOVER PARA ESQUERDA";

        String linha2 =
                "D / →  = MOVER PARA DIREITA";

        String linha3 =
                "W / ↑  = SUBIR";

        String linha4 =
                "S / ↓  = DESCER";

        String linha5 =
                "OBJETIVO:";

        String linha6 =
                "COLETE O LIXO CORRETO";

        String linha7 =
                "PARA LIMPAR O RIO";

        FontMetrics fm =
                g.getFontMetrics();

        g.drawString(
                linha1,
                (getWidth() - fm.stringWidth(linha1)) / 2,
                240
        );

        g.drawString(
                linha2,
                (getWidth() - fm.stringWidth(linha2)) / 2,
                300
        );

        g.drawString(
                linha3,
                (getWidth() - fm.stringWidth(linha3)) / 2,
                360
        );

        g.drawString(
                linha4,
                (getWidth() - fm.stringWidth(linha4)) / 2,
                420
        );

        g.drawString(
                linha5,
                (getWidth() - fm.stringWidth(linha5)) / 2,
                520
        );

        g.drawString(
                linha6,
                (getWidth() - fm.stringWidth(linha6)) / 2,
                580
        );

        g.drawString(
                linha7,
                (getWidth() - fm.stringWidth(linha7)) / 2,
                640
        );

        // botão continuar
        int centroX =
                (getWidth() - 300) / 2;

        botaoContinuar.setBounds(
                centroX,
                getHeight() - 140,
                300,
                70
        );

        g.setColor(Color.WHITE);

        g.fillRoundRect(
                botaoContinuar.x,
                botaoContinuar.y,
                botaoContinuar.width,
                botaoContinuar.height,
                20,
                20
        );

        g.setColor(Color.BLACK);

        g.drawRoundRect(
                botaoContinuar.x,
                botaoContinuar.y,
                botaoContinuar.width,
                botaoContinuar.height,
                20,
                20
        );

        Font botaoFonte =
                new Font("Arial", Font.BOLD, 30);

        g.setFont(botaoFonte);

        String continuar = "CONTINUAR";

        FontMetrics fmBotao =
                g.getFontMetrics();

        int larguraTexto =
                fmBotao.stringWidth(continuar);

        g.drawString(
                continuar,
                botaoContinuar.x +
                        (botaoContinuar.width - larguraTexto) / 2,
                botaoContinuar.y + 45
        );
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (botaoContinuar.contains(e.getPoint())) {

            Fase fase = new Fase();

            janela.setContentPane(fase);

            janela.revalidate();

            fase.requestFocusInWindow();
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