import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

public class Display{

    public static Display display = new Display();


    static class ImagePanel extends JPanel{

        private Image img;
        public Dimension res;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            res = size;
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, res.width,res.height,null);
        }

    }

    InputStream is;
    Font font;

    JFrame frame;
    JPanel mainPanel, gamePanel, bottomPanel, narrationPanel, buttonPanel;
    JButton showCommandsButton,showInventoryButton,showOutputButton;
    JLabel statusBar, commandBar, narrationBar, actionsBar;
    JTextField commandInput;
    Dimension resolution;
    Timer narrationTimer,statusTimer,commandsTimer, outPutTimer;
    String buffer;
    ByteArrayOutputStream baos;
    JScrollPane commandScrollPane;

    private String commandToString(Commands commands){
        switch (commands){
            case NewGame:
                return "NewGame [name]";
            case Continue:
                return "Continue";
            case Settings:
                return "Settings";
            case Credits:
                return "Credits";
            case Exit:
                return "Exit";
            case Move:
                return "Move [direction]";
            case Take:
                return "Take [object]";
            case LookAround:
                return "LookAround";
            case Inspect:
                return "Inspect [object]";
            case Inventory:
                return "Inventory";
            case Use:
                return "Use [object]";
            case leaveNote:
                return "LeaveNote [WIP]";
            case SaveGame:
                return "SaveGame";
            case MusicVolume:
                return "MusicVolume [volume]";
            case EffectVolume:
                return "EffectVolume [volume]";
            case Fullscreen:
                return "Fullscreen [true/false]";
            case Menu:
                return "Menu";
            case Back:
                return "Back";
        }
        return "";
    }

    private ImagePanel initMainPanel(String imageName){
        ImagePanel panel = new ImagePanel(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\" + imageName).getImage());

        panel.setPreferredSize(resolution);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder( BorderFactory.createEmptyBorder(-5, 0, 0, 0) );
        panel.add(gamePanel);
        return panel;
    }

    private JPanel initGamePanel(){
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension((resolution.height/16)*9,resolution.height));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        panel.add(statusBar, BorderLayout.NORTH);
        panel.add(narrationPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }

    public void changeResolution() { //TODO: Implement resolution changing
        Settings settings = new Settings();
        resolution = settings.getResolution();
    }

    private JLabel initStatusBar(){
        JLabel label = new JLabel();

        label.setFont(font.deriveFont(Font.PLAIN,100));
        label.setText("Location");
        label.setBackground(new Color(86,22,95));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);

        return label;
    }

    private JPanel initBottomPanel(){
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension((resolution.height/16)*9,400));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        actionsBar = new JLabel();
        //Setting up the label
        actionsBar.setFont(font.deriveFont(Font.PLAIN,60));
        actionsBar.setText("Actions:");
        actionsBar.setBackground(new Color(86,22,95));
        actionsBar.setForeground(Color.WHITE);
        actionsBar.setBorder(BorderFactory.createEmptyBorder(10,20,-5,0));
        actionsBar.setOpaque(true);

        commandScrollPane = new JScrollPane(commandBar,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        commandScrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(actionsBar,BorderLayout.NORTH);
        panel.add(commandScrollPane,BorderLayout.CENTER);
        panel.add(commandInput,BorderLayout.SOUTH);

        return panel;
    }

    public void setButtonsVisible(boolean visibility){
        buttonPanel.setVisible(visibility);
    }

    private JLabel initCommandBar(){
        JLabel label = new JLabel();

        label.setFont(font.deriveFont(Font.PLAIN,48));
        label.setText("<html><p style='margin-top:-5'>- placeholder command");
        label.setBackground(new Color(86,22,95));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0,50,20,0));
        label.setOpaque(true);

        return label;
    }

    private JTextField initCommandInput(){
        JTextField textField = new JTextField();

        textField.setFont(font.deriveFont(Font.PLAIN,48));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);
        textField.setPreferredSize(new Dimension((resolution.height/16)*9,60));
        textField.setBorder(BorderFactory.createEmptyBorder(10,50,10,0));
        textField.setText("> command...");
        textField.setFocusable(true);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                //Do nothing
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = textField.getText();
                    String[] args = input.split(" ");
                    GameLogic.gameLogic.useCommand(args);
                    textField.setText("> command...");
                    textField.requestFocus();
                }
                else if(textField.getText().equals("> command...")) {
                    textField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                //Do nothing
            }
        });

        return textField;
    }

    private JPanel initNarrationPanel(){
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension((resolution.height/16)*9,resolution.height));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        panel.add(narrationBar, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel initButtonPanel(){
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setAlignment(FlowLayout.LEFT);

        panel.setPreferredSize(new Dimension((resolution.width/16)*9,45));
        panel.setLayout(layout);
        panel.setBorder( BorderFactory.createEmptyBorder(0, -10, 0, 0) );
        //panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.setBackground(Color.WHITE);
        panel.add(showCommandsButton);
        panel.add(showInventoryButton);
        panel.add(showOutputButton);
        return panel;
    }

    private ImageIcon resizeIcon(ImageIcon icon){
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;
        return new ImageIcon( newimg );
    }

    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            narrationBar.setText(narrationBar.getText() + text + "<br>");
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }

    public void openInventory(){
        actionsBar.setText("Inventory:");
        if(outPutTimer != null)
            outPutTimer.stop();
        setInventoryText();
    }
    public void openOutputLog(){
        actionsBar.setText("Output Log:");
        updateOutLog();
    }
    public void openCommandList(){
        actionsBar.setText("Actions:");
        if(outPutTimer != null)
            outPutTimer.stop();
        setCommandList(GameData.gameData.getCurrentGameState().getCurrentAction().getCommands());
    }

    private void initButtons(){
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        JButton showCommand,showInventory,showOutput;
        showCommand = new JButton();
        showInventory = new JButton();
        showOutput = new JButton();

        showCommand.setBackground(new Color(86,22,95));
        showInventory.setBackground(new Color(86,22,95));
        showOutput.setBackground(new Color(86,22,95));

        showCommand.setBorder(BorderFactory.createEmptyBorder());
        showInventory.setBorder(BorderFactory.createEmptyBorder());
        showOutput.setBorder(BorderFactory.createEmptyBorder());

        showCommand.setPreferredSize(new Dimension(45,45));
        showInventory.setPreferredSize(new Dimension(45,45));
        showOutput.setPreferredSize(new Dimension(45,45));

        ImageIcon cmdIcon = resizeIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\commands_icon.png"));
        ImageIcon invIcon = resizeIcon(new ImageIcon(System.getProperty("user.dir") +"\\src\\main\\java\\inventory_icon.png"));
        ImageIcon outIcon = resizeIcon(new ImageIcon(System.getProperty("user.dir") +"\\src\\main\\java\\log_icon.png"));

        showCommand.setIcon(cmdIcon);
        showInventory.setIcon(invIcon);
        showOutput.setIcon(outIcon);

        showCommand.validate();


        showCommandsButton = showCommand;
        showInventoryButton = showInventory;
        showOutputButton = showOutput;

        showCommandsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openCommandList();
            }
        });
        showInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openInventory();
            }
        });
        showOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openOutputLog();
            }
        });
    }

    private JLabel initNarrationBar(){
        JLabel label = new JLabel();
        String narrationText = "<html><p style='margin-top:30; margin-left:20'>This is a placeholder";

        label.setFont(font.deriveFont(Font.PLAIN,60));
        label.setText(narrationText);
        label.setBackground(Color.white);
        label.setForeground(Color.black);
        label.setOpaque(true);

        return label;
    }

    public void setNarrationText(final String text) {
        final int textLength = text.length();
        narrationBar.setText("<html><p style='margin-top:30; margin-left:20'>");

        if(narrationTimer != null)
            narrationTimer.stop();

        narrationTimer= new Timer(100,null);
        narrationTimer.addActionListener(new ActionListener() {
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(index < textLength) {
                    if(text.charAt(index) == '\n'){
                        narrationBar.setText(narrationBar.getText() + "</p><p style='margin-top:-10;margin-left:20'>");
                    }
                    else if(text.charAt(index) == '['){
                        narrationBar.setText(narrationBar.getText() + "<font color='red'>[");
                    }
                    else if(text.charAt(index) == ']'){
                        narrationBar.setText(narrationBar.getText() + "]</font>");
                    }
                    else{
                        narrationBar.setText(narrationBar.getText() + text.charAt(index));
                    }
                    index += 1;
                }else{
                    narrationBar.setText(narrationBar.getText() + "</p></html>");
                    narrationTimer.stop();
                }

            }
        });
        narrationTimer.start();
    }

    public void setInventoryText(){
        if(GameData.gameData.getCurrentGameState().getPlayer().getInventory().size() > 0){
            ArrayList<String> commands = GameData.gameData.getCurrentGameState().getPlayer().getInventory();
            commandBar.setText("<html><p style='margin-top:-5'>- ");

            if (commandsTimer != null)
                commandsTimer.stop();

            commandsTimer = new Timer(50, null);
            commandsTimer.addActionListener(new ActionListener() {
                int indexCommand = 0, index = 0;
                int textLength = commands.get(indexCommand).length();

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (indexCommand <= commands.size()) {
                        if (index < textLength) {
                            commandBar.setText(commandBar.getText() + commands.get(indexCommand).charAt(index));
                            index += 1;
                        } else {
                            indexCommand += 1;
                            if (indexCommand < commands.size()) {
                                commandBar.setText(commandBar.getText() + "</p><p style='margin-top:-10'>- ");
                                textLength = commands.get(indexCommand).length();
                                index = 0;
                            }
                        }
                    } else {
                        commandBar.setText(commandBar.getText() + "</p></html>");
                        commandsTimer.stop();
                    }

                }
            });
            commandsTimer.start();
        }else{
            commandBar.setText("<html><p style='margin-top:-5'>- Empty</p></html> ");
        }
    }

    public void setCommandList(final Commands[] enumCommands) {
        if(actionsBar.getText().equals("Actions:")) {
            java.util.List<String> commands = new ArrayList<String>();
            for (int i = 0; i < enumCommands.length; i++) {
                commands.add(commandToString(enumCommands[i]) + "<br>");
            }
            commandBar.setText("<html><p style='margin-top:-5'>- ");

            if (commandsTimer != null)
                commandsTimer.stop();

            commandsTimer = new Timer(50, null);
            commandsTimer.addActionListener(new ActionListener() {
                int indexCommand = 0, index = 0;
                int textLength = commands.get(indexCommand).length();

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (indexCommand <= commands.size()) {
                        if (index < textLength) {
                            commandBar.setText(commandBar.getText() + commands.get(indexCommand).charAt(index));
                            index += 1;
                        } else {
                            indexCommand += 1;
                            if (indexCommand < commands.size()) {
                                commandBar.setText(commandBar.getText() + "</p><p style='margin-top:-10'>- ");
                                textLength = commands.get(indexCommand).length();
                                index = 0;
                            }
                        }
                    } else {
                        commandBar.setText(commandBar.getText() + "</p></html>");
                        commandsTimer.stop();
                    }

                }
            });
            commandsTimer.start();
        }
    }

    public void setStatusText(final String text) {
        final int textLength = text.length();
        statusBar.setText("<html><p style='margin-left: 20;margin-bottom: 20'>");

        if(statusTimer != null)
            statusTimer.stop();

        statusTimer = new Timer(100,null);
        statusTimer.addActionListener(new ActionListener() {
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(index < textLength) {
                    statusBar.setText(statusBar.getText() + text.charAt(index));
                    index += 1;
                }else{
                    statusTimer.stop();
                }

            }
        });
        statusTimer.start();
    }

    public void updateOutLog(){
        commandBar.setText("<html>");
        if (commandsTimer != null)
            commandsTimer.stop();

        if (outPutTimer != null)
            outPutTimer.stop();

        outPutTimer = new Timer(100,null);
        outPutTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                commandBar.setText("<html><p style='margin-top:-5'>" + baos.toString().replaceAll("\r","<br>").replaceAll("\n","</p><p style='margin-top:-5'>"));
                commandScrollPane.getVerticalScrollBar().setValue( commandScrollPane.getVerticalScrollBar().getMaximum() );
            }
        });
        outPutTimer.start();
    }

    public void setBackgroundImage(String imageName){
        frame.remove(mainPanel);
        mainPanel = initMainPanel(imageName);
        frame.add(mainPanel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private int lerp(float point1, float point2, float alpha) {
        return (int) (point1 + alpha * (point2 - point1));
    }

    public void setPanelColors(final Color color){
        final Color startColor = statusBar.getBackground();
        // We are assuming that all of the panels have the same color
        Timer timer = new Timer(50,null);
        timer.addActionListener(new ActionListener() {
            float lerpValue = 0;
            Color currentColor;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(lerpValue < 1) {
                    currentColor = new Color(lerp(startColor.getRed(),color.getRed(),lerpValue),
                            lerp(startColor.getGreen(),color.getGreen(),lerpValue),
                            lerp(startColor.getBlue(),color.getBlue(),lerpValue));

                    statusBar.setBackground(currentColor);
                    actionsBar.setBackground(currentColor);
                    bottomPanel.setBackground(currentColor);
                    commandBar.setBackground(currentColor);
                    showCommandsButton.setBackground(currentColor);
                    showInventoryButton.setBackground(currentColor);
                    showOutputButton.setBackground(currentColor);
                    lerpValue += 0.05;
                }else{
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public Display(){
        String font_path = System.getProperty("user.dir") + "\\src\\main\\resources\\OpenMine.ttf";
        try {
            is = new BufferedInputStream(new FileInputStream(font_path));
            font = Font.createFont(Font.TRUETYPE_FONT,is);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        changeResolution();
        commandInput = initCommandInput();
        commandBar = initCommandBar();
        statusBar = initStatusBar();
        narrationBar = initNarrationBar();
        initButtons();
        buttonPanel = initButtonPanel();
        narrationPanel = initNarrationPanel();
        bottomPanel = initBottomPanel();
        gamePanel = initGamePanel();
        mainPanel = initMainPanel("idle_background.jpg");

        setButtonsVisible(false);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        initFrame();
    }

    public void initFrame(){
        if(frame != null){
            frame.dispose();
        }
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setUndecorated(new Settings().getFullscreen());
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bostrom's Dream");
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Display();
    }
}


