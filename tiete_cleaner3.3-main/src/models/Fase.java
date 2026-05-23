package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Fase extends JPanel implements ActionListener, MouseListener {

    private Player player;
    private Timer timer;
    private int fundoDaTela = 0;

    private String[] tipos = {"papel", "plastico", "metal", "vidro", "organico"};
    private String tipoAtual;

    private int tempoTroca = 0;
    private int poluicao = 220;

    private ArrayList<Item> itens = new ArrayList<>();

    private Random random = new Random();

    private int erros = 0;
    private boolean gameOver = false;

    private int acertos = 0;
    private boolean venceu = false;

    private Clip somAcerto;
    private Clip somErro;

    private Rectangle botaoRestart = new Rectangle(0, 0, 200, 60);

    private Image lixeiraAzulImg;
    private Image lixeiraVermelhaImg;
    private Image lixeiraVerdeImg;
    private Image lixeiraAmareloImg;
    private Image lixeiraMarromImg;

    private BufferedImage waterSheet;
    private BufferedImage[] waterFrames;

    private int currentWaterFrame = 0;

    private int waterAnimationTimer = 0;

    public Fase() {

        setFocusable(true);

        setDoubleBuffered(true);

        tipoAtual = tipos[random.nextInt(tipos.length)];

        try {

            waterSheet = ImageIO.read(
                    getClass().getResource("/resources2.0/water.png")
            );

            waterFrames = new BufferedImage[5];

            for (int i = 0; i < 5; i++) {

                waterFrames[i] = waterSheet.getSubimage(
                        i * 16,
                        0,
                        16,
                        16
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();

        player = new Player(
                tamanhoTela.width,
                tamanhoTela.height
        );

        player.load();

        lixeiraAzulImg = new ImageIcon(
                getClass().getResource("/resources2.0/lixeiraAzul.png")
        ).getImage();

        lixeiraVermelhaImg = new ImageIcon(
                getClass().getResource("/resources2.0/lixeiraVermelha.png")
        ).getImage();

        lixeiraVerdeImg = new ImageIcon(
                getClass().getResource("/resources2.0/lixeiraVerde.png")
        ).getImage();

        lixeiraAmareloImg = new ImageIcon(
                getClass().getResource("/resources2.0/lixeiraAmarela.png")
        ).getImage();

        lixeiraMarromImg = new ImageIcon(
                getClass().getResource("/resources2.0/lixeiraMarrom.png")
        ).getImage();

        addKeyListener(new TecladoAdapter());

        addMouseListener(this);

        timer = new Timer(16, this);
        timer.start();

        //Sons de erro e acerto
        try {

            AudioInputStream audioAcerto =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(
                                    "/resources2.0/audios/Acerto.wav"
                            )
                    );

            somAcerto = AudioSystem.getClip();

            somAcerto.open(audioAcerto);

            AudioInputStream audioErro =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(
                                    "/resources2.0/audios/Erro.wav"
                            )
                    );

            somErro = AudioSystem.getClip();

            somErro.open(audioErro);

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    private void tocarSom(Clip clip) {

        if (clip == null) return;

        clip.stop();

        clip.setFramePosition(0);

        clip.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D graficos = (Graphics2D) g;

        graficos.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        if (waterFrames != null) {

            for (int x = 0; x < getWidth(); x += 64) {

                for (int y = 0; y < getHeight(); y += 64) {

                    graficos.drawImage(
                            waterFrames[currentWaterFrame],
                            x,
                            y,
                            64,
                            64,
                            null
                    );
                }
            }
        }

        //camada de poluição

        g.setColor(new Color(40, 70, 30, poluicao));

        g.fillRect(0, 0, getWidth(), getHeight());

        graficos.drawImage(
                player.getImagem(),
                player.getX(),
                player.getY(),
                this
        );

        for (Item item : itens) {

            item.desenhar(graficos);

            g.setColor(Color.WHITE);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            30
                    )
            );

            g.drawString(
                    "Colete",
                    getWidth() / 2 - 100,
                    50
            );

            if (tipoAtual.equals("papel")) {

                g.drawImage(
                        lixeiraAzulImg,
                        getWidth() / 2 + 5,
                        10,
                        60,
                        60,
                        null
                );

            } else if (tipoAtual.equals("plastico")) {

                g.drawImage(
                        lixeiraVermelhaImg,
                        getWidth() / 2 + 5,
                        10,
                        60,
                        60,
                        null
                );

            } else if (tipoAtual.equals("vidro")) {

                g.drawImage(
                        lixeiraVerdeImg,
                        getWidth() / 2 + 5,
                        10,
                        60,
                        60,
                        null
                );

            } else if (tipoAtual.equals("metal")) {

                g.drawImage(
                        lixeiraAmareloImg,
                        getWidth() / 2 + 5,
                        10,
                        60,
                        60,
                        null
                );

            } else if (tipoAtual.equals("organico")) {

                g.drawImage(
                        lixeiraMarromImg,
                        getWidth() / 2 + 5,
                        10,
                        60,
                        60,
                        null
                );
            }

            int xBotao = (getWidth() - 200 / 2);

            int yBotao = getHeight() / 2 + 80;

            botaoRestart.setBounds(
                    xBotao,
                    yBotao,
                    200,
                    60
            );
        }

        if (gameOver) {

            String texto = "PERDEU";

            Font fonte =
                    new Font(
                            "Arial",
                            Font.BOLD,
                            50
                    );

            g.setFont(fonte);

            g.setColor(Color.RED);

            FontMetrics fm =
                    g.getFontMetrics(fonte);

            int larguraTexto =
                    fm.stringWidth(texto);

            int x =
                    (getWidth() - larguraTexto) / 2;

            int y =
                    getHeight() / 2;

            g.drawString(texto, x, y);
        }

        if (venceu) {

            String texto = "RIO LIMPO";

            Font fonte =
                    new Font(
                            "Arial",
                            Font.BOLD,
                            50
                    );

            g.setFont(fonte);

            g.setColor(Color.WHITE);

            FontMetrics fm =
                    g.getFontMetrics(fonte);

            int larguraTexto =
                    fm.stringWidth(texto);

            int x =
                    (getWidth() - larguraTexto) / 2;

            int y =
                    getHeight() / 2;

            g.drawString(texto, x, y);
        }

        if (gameOver || venceu) {

            int xBotao =
                    (getWidth() - botaoRestart.width) / 2;

            int yBotao =
                    getHeight() / 2 + 80;

            botaoRestart.setBounds(
                    xBotao,
                    yBotao,
                    botaoRestart.width,
                    botaoRestart.height
            );

            g.setColor(Color.WHITE);

            g.fillRect(
                    botaoRestart.x,
                    botaoRestart.y,
                    botaoRestart.width,
                    botaoRestart.height
            );

            g.setColor(Color.BLACK);

            g.drawRect(
                    botaoRestart.x,
                    botaoRestart.y,
                    botaoRestart.width,
                    botaoRestart.height
            );

            String texto = "RESTART";

            Font fonte =
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    );

            g.setFont(fonte);

            g.setColor(Color.black);

            FontMetrics fm =
                    g.getFontMetrics(fonte);

            int larguraTexto =
                    fm.stringWidth(texto);

            int xTexto =
                    botaoRestart.x +
                            (botaoRestart.width - larguraTexto) / 2;

            int yTexto =
                    botaoRestart.y +
                            (botaoRestart.height / 2) +
                            fm.getAscent() / 2;

            g.drawString(
                    texto,
                    xTexto,
                    yTexto
            );

            System.out.println(
                    "Player X: " + player.getX()
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (gameOver || venceu) {

            return;
        }

        player.update(
                getWidth(),
                getHeight()
        );

        fundoDaTela -= 2;

        if (fundoDaTela <= -getWidth()) {

            fundoDaTela = 0;
        }

        waterAnimationTimer++;

        if (waterAnimationTimer >= 10) {

            waterAnimationTimer = 0;

            currentWaterFrame++;

            if (currentWaterFrame >= waterFrames.length) {

                currentWaterFrame = 0;
            }
        }

        if (random.nextInt(100) < 5) {

            spawnItem();
        }

        for (Item item : itens) {

            item.update();
        }

        itens.removeIf(
                item -> item.getX() < 0
        );

        repaint();

        tempoTroca++;

        if (tempoTroca > 1200) {

            tipoAtual =
                    tipos[random.nextInt(tipos.length)];

            tempoTroca = 0;
        }

        Iterator<Item> iterator =
                itens.iterator();

        while (iterator.hasNext()) {

            Item item =
                    iterator.next();

            item.update();

            boolean colidiu =

                    (player.getX() <
                            item.getX() + item.getLargura()

                            &&

                            player.getX() + 50 >
                                    item.getX()

                            &&

                            player.getY() <
                                    item.getY() + item.getAltura()

                            &&

                            player.getY() + 50 >
                                    item.getY());

            {
                if (colidiu) {

                    if (item.getTipo().equals(tipoAtual)) {

                        System.out.println("Acertou");
                        tocarSom(somAcerto);

                        acertos++;

                        poluicao -= 20;

                        if (poluicao < 0) {

                            poluicao = 0;
                        }

                    } else {

                        System.out.println("Errou");
                        tocarSom(somErro);

                        erros++;

                        poluicao += 10;

                        if (poluicao > 255) {

                            poluicao = 255;
                        }
                    }

                    if (poluicao == 0) {

                        venceu = true;
                    }

                    if (poluicao == 255) {

                        gameOver = true;
                    }

                    iterator.remove();
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            player.atalhoTeclado(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            player.atalhoDesselecionado(e);
        }
    }

    private void spawnItem() {

        String[] tipos = {
                "papel",
                "plastico",
                "metal",
                "vidro",
                "organico"
        };

        String tipo =
                tipos[random.nextInt(tipos.length)];

        int x = getWidth();

        int y =
                random.nextInt(
                        Math.max(getHeight() - 50, 1)
                );

        Item novoItem =
                new Item(x, y, tipo);

        itens.add(novoItem);
    }

    public void reiniciarJogo(){

        erros = 0;

        acertos = 0;

        gameOver = false;

        venceu = false;

        poluicao = 220;

        itens.clear();

        player =
                new Player(
                        getWidth(),
                        getHeight()
                );

        player.load();

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e){

        if (botaoRestart.contains(e.getPoint())){

            reiniciarJogo();
        }
    }
}