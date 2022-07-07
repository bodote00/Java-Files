package PacMan;

import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;

public class PacManModel extends JPanel implements ActionListener
{

    private Dimension dimension;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean isDead = false;

    private final short BLOCK_SIZE = 24; //aky velky je blok
    private final short N_BLOCKS = 20;  //kolko je blokov v riadku a v stlpci (20 x 20)
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;  //velkost obrazovky pocet_blokov * velkost_blokov
    private final int MAX_GHOSTS = 12; //maximalny pocet duchov
    private final short PACMAN_SPEED = 3;  //rychlost pacmana
    private short GHOSTS_NUMBER = 5; //pocet duchov na zaciatku
    private short lives, score;
    private int[] dx, dy;  //delta_x a delta_y
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image goUp, goDown, goRight, goLeft;  //pac man animacie

    private int pacman_x, pacman_y, pacManDeltaX, pacManDeltaY;
    private int req_dx, req_dy;



    //0 = prekazka
    //1 = prekazka VLAVO od policka
    //2 = prekazka HORE od policka
    //4 = prekazka VPRAVO od policka
    //8 = prekazka DOLE od policka
    //16 = coin
    // hodnoty sa scitavaju, ziadna hodnota sa nemohla rovnat suctu predoslych, preto take cisla (1 + 2 = 3, a teda VPRAVO = 4.......)
    // je ich 20 x 20 (400)

    private final short myScreenStructure[] = {
            0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0,
            0, 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 24, 24, 24, 24, 24, 28,  0, 25, 24, 24, 24, 24, 24, 16, 20, 0,
            0, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 17, 20, 0,
            0, 17, 16, 16, 18, 18, 18, 18, 18, 22,  0, 19, 18, 18, 18, 18, 18, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 16, 16, 16, 20, 0,
            0, 25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28, 0,
            0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0,

    };

    private final short validSpeeds[] = {1, 2, 3, 4, 5};
    private final short maxSpeed = 7;

    private short currentSpeed = 2;
    private short[] screenData;
    private Timer timer;

    public PacManModel()
    {
        loadTheImages();
        initialVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initialGame();
    }


    private void loadTheImages()
    {
        heart = new ImageIcon("/src/images/heart.png").getImage();
        goDown = new ImageIcon("/src/images/down.gif").getImage();
        goUp = new ImageIcon("/src/images/up.gif").getImage();
        goLeft = new ImageIcon("/src/images/left.gif").getImage();
        goRight = new ImageIcon("/src/images/right.gif").getImage();
        ghost = new ImageIcon("/src/images/ghost.gif").getImage();
    }

    private void initialVariables()
    {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        dimension = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(50, this); //hra sa bude obnovovať každých 50 milisekund
        timer.start(); //spusti sa casovac
    }


    private void playGame(Graphics2D graphics2D)
    {
        if (isDead)
        {
            death();
        }
        else
        {
            movePacman();
            drawPacman(graphics2D);
            moveGhosts(graphics2D);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D graphics2D) {

        String start = "Press SPACE to start";
        graphics2D.setColor(Color.yellow);
        graphics2D.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void drawScore(Graphics2D graphics2D)
    {
        graphics2D.setFont(smallFont);
        graphics2D.setColor(new Color(4, 180, 80));
        String s = "Score: " + score;
        graphics2D.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        int i;
        for (i = 0; i < lives; i++)
        {
            graphics2D.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void checkMaze()
    {
        short i = 0;
        boolean isFinished = true;

        while (i < N_BLOCKS * N_BLOCKS && isFinished)
        {
            if ((screenData[i]) != 0)
            {
                isFinished = false;
            }
            i++;
        }

        if (isFinished)
        {
            score += 50;

            if (GHOSTS_NUMBER < MAX_GHOSTS)
            {
                GHOSTS_NUMBER++;
            }

            if (currentSpeed < maxSpeed)
            {
                currentSpeed++;
            }

            initialLevel();
        }
    }

    private void death()
    {
        lives--;

        if (lives <= 0)
        {
            inGame = false;
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D graphics2D) {

        int pos;
        int count;

        int i = 0;
        while (i < GHOSTS_NUMBER)
        {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(graphics2D, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                isDead = true;
            }
            i++;
        }
    }

    private void drawGhost(Graphics2D graphics2D, int x, int y)
    {
        graphics2D.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        int pos;
        short ch;

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacManDeltaX = req_dx;
                    pacManDeltaY = req_dy;
                }
            }

            if ((pacManDeltaX == -1 && pacManDeltaY == 0 && (ch & 1) != 0)
                    || (pacManDeltaX == 1 && pacManDeltaY == 0 && (ch & 4) != 0)
                    || (pacManDeltaX == 0 && pacManDeltaY == -1 && (ch & 2) != 0)
                    || (pacManDeltaX == 0 && pacManDeltaY == 1 && (ch & 8) != 0)) {
                pacManDeltaX = 0;
                pacManDeltaY = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacManDeltaX;
        pacman_y = pacman_y + PACMAN_SPEED * pacManDeltaY;
    }

    private void drawPacman(Graphics2D graphics2D)
    {
        if (req_dy == -1)
        {
            graphics2D.drawImage(goUp, pacman_x + 1, pacman_y + 1, this);
        }
        else if (req_dx == -1)
        {
            graphics2D.drawImage(goLeft, pacman_x + 1, pacman_y + 1, this);
        }
        else if(req_dx == 1)
        {
            graphics2D.drawImage(goRight, pacman_x + 1, pacman_y + 1, this);
        }
        else
        {
            graphics2D.drawImage(goDown, pacman_x + 1, pacman_y + 1, this);
        }
    }

    private void drawMaze(Graphics2D graphics2D)
    {
        short i = 0;
        short x = 0, y = 0;

        while(y < SCREEN_SIZE)
        {
            while (x < SCREEN_SIZE)
            {

                graphics2D.setColor(new Color(1,100,200));
                graphics2D.setStroke(new BasicStroke(4));

                if ((myScreenStructure[i] == 0))
                {
                    graphics2D.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((screenData[i] & 1) != 0)
                {
                    graphics2D.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0)
                {
                    graphics2D.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0)
                {
                    graphics2D.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0)
                {
                    graphics2D.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0)
                {
                    graphics2D.setColor(new Color(255,255,255));
                    graphics2D.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
                x += BLOCK_SIZE;
            }
            y += BLOCK_SIZE;
        }
    }

    private void initialGame()
    {
        score = 0;
        lives = 5;
        GHOSTS_NUMBER = 5;
        currentSpeed = 3;
        initialLevel();
    }

    private void initialLevel()
    {
        int i = 0;
        while(i < N_BLOCKS * N_BLOCKS)
        {
            screenData[i] = myScreenStructure[i];
            i++;
        }
        continueLevel();
    }

    private void continueLevel() {

        int dx = 1;
        int random;

        for (int i = 0; i < GHOSTS_NUMBER; i++)
        {
            ghost_y[i] = 4 * BLOCK_SIZE; //start position
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed)
            {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 7 * BLOCK_SIZE;
        pacman_y = 11 * BLOCK_SIZE;
        pacManDeltaX = 0;
        pacManDeltaY = 0;
        req_dx = 0;
        req_dy = 0;
        isDead = false;
    }


    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, dimension.width, dimension.height);

        drawMaze(graphics2D);
        drawScore(graphics2D);

        if (inGame)
        {
            playGame(graphics2D);
        }
        else
        {
            showIntroScreen(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        repaint();
    }


    class TAdapter extends KeyAdapter
    {

        public void keyPressed(KeyEvent keyEvent)
        {

            int key = keyEvent.getKeyCode();

            if (inGame)
            {
                if (key == KeyEvent.VK_LEFT)
                {
                    req_dx = -1;
                    req_dy = 0;
                }
                else if (key == KeyEvent.VK_RIGHT)
                {
                    req_dx = 1;
                    req_dy = 0;
                }
                else if (key == KeyEvent.VK_UP)
                {
                    req_dx = 0;
                    req_dy = -1;
                }
                else if (key == KeyEvent.VK_DOWN)
                {
                    req_dx = 0;
                    req_dy = 1;
                }
                else if (key == KeyEvent.VK_ESCAPE && timer.isRunning())
                {
                    inGame = false;
                }
            }
            else if (key == KeyEvent.VK_SPACE)
            {
                inGame = true;
                initialGame();
            }

        }
    }
}

