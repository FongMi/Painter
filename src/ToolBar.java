
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class ToolBar extends JPanel implements ActionListener{
    
    MainWindow parant;
    JToolBar[] toolBar;
    String toolBarName[] = {"工具箱", "畫筆", "形狀", "其他", "調色盤"};
    
    /*畫筆按鈕*/
    JToggleButton[] pen_JTBtn;
    String penBtnName[][] = {{"Pen", "鉛筆，使用選取的線條寬度繪製任意形狀的線條"},{"Eraser", "橡皮擦，清除圖片的的一部份，並以背景色彩取代"}};
    String penImage[] = {"img/pencil.png", "img/eraser.png"};
    
    /*填滿按鈕*/
    JToggleButton fill_JTBtn;
    
    /*形狀按鈕*/
    JToggleButton[] shape_JTBtn;
    String shapeBtnName[][] = {{"Line", "直線"}, {"Rectangle", "矩形"}, {"Round_Rectangle", "圓角矩形"}, {"Oval", "橢圓形"}};
    String shapeImage[] = {"img/line.png", "img/rectangle.png", "img/round_rectangle.png", "img/oval.png"};
    
    /*線條設定*/
    String[] lineWidth = {"▂","▄","▆","█"};
    Integer[] lineWidth_px = {4, 8, 16, 24};
    JComboBox lineWidthList;
    
    /*字形設定*/
    String[] strings = {"Ariel", "Myriad", "Time New Roman", "標楷體", "新細明體", "微軟正黑體"};
    JComboBox stringList;

    /*功能按鈕*/
    JButton[] jBtn;
    String btnName[][] = {{"復原","復原(Ctrl+Z) 復原上次的動作"}, {"顏色","編輯色彩，從調色盤選取色彩"}};
    String btnImage[] = {"img/undo.png", "img/color.png"};

    /*調色盤按鈕*/
    static JToggleButton[] colorJTBtn;
    static JPanel[] colorPanel;
    static JButton[] colorsBtn;
    String colorToolTip[] = {"色彩1(前景色彩)，按一下此處，然後從調色盤選取色彩，鉛筆圖形都會使用此色彩","色彩2(背景色彩)，按一下此處，然後從調色盤選取色彩，橡皮擦會使用此色彩"};
    Color colors[] = {Color.BLACK, Color.WHITE, Color.CYAN, Color.BLUE, Color.RED, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.GREEN, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY};

    ToolBar(MainWindow p) {
        parant = p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(255, 250, 240));
        
        toolBar = new JToolBar[toolBarName.length];
        for (int i = 0; i < toolBarName.length; i++) {
            toolBar[i] = new JToolBar(toolBarName[i], JToolBar.VERTICAL);
            toolBar[i].setFloatable(false);
            toolBar[i].setLayout(new GridLayout(1, 1));
            toolBar[i].setBackground(new Color(255, 250, 240));
        }
        toolBar[0].setLayout(new FlowLayout());

        /*新增畫筆按鈕*/
        ButtonGroup buttonGroup = new ButtonGroup();
        pen_JTBtn = new JToggleButton[penBtnName.length];
        for (int i = 0; i < penBtnName.length; i++) {
            pen_JTBtn[i] = new JToggleButton();
            pen_JTBtn[i].setToolTipText(penBtnName[i][1]);
            pen_JTBtn[i].setIcon(new ImageIcon(this.getClass().getResource(penImage[i])));
            pen_JTBtn[i].addActionListener(this);
            pen_JTBtn[i].setFocusable(false);
            pen_JTBtn[0].setSelected(true);
            buttonGroup.add(pen_JTBtn[i]);
            toolBar[1].add(pen_JTBtn[i]);
        }
        
        /*新增填滿按鈕*/
        fill_JTBtn = new JToggleButton();
        fill_JTBtn.setIcon(new ImageIcon(this.getClass().getResource("img/fill.png")));
        fill_JTBtn.addActionListener(this);
        fill_JTBtn.setFocusable(false);
        toolBar[1].add(fill_JTBtn);
        
        /*新增形狀按鈕*/
        shape_JTBtn = new JToggleButton[shapeBtnName.length];
        for (int i = 0; i < shapeBtnName.length; i++) {
            shape_JTBtn[i] = new JToggleButton();
            shape_JTBtn[i].setToolTipText(shapeBtnName[i][1]);
            shape_JTBtn[i].setIcon(new ImageIcon(this.getClass().getResource(shapeImage[i])));
            shape_JTBtn[i].addActionListener(this);
            shape_JTBtn[i].setFocusable(false);
            buttonGroup.add(shape_JTBtn[i]);
            toolBar[2].add(shape_JTBtn[i]);
        }
        
        /*新增線條粗細設置*/
        lineWidthList = new JComboBox(lineWidth);
        lineWidthList.addActionListener(this);
        lineWidthList.setToolTipText("大小(Ctrl++, Ctrl+-)");
        toolBar[3].add(lineWidthList);
        
        /*新增字形設定--暫時不加*/
        stringList = new JComboBox();
        for (int i = 0; i < strings.length; i++) {
            stringList.addItem(strings[i]);
        }
        stringList.addActionListener(this);
        //toolBar[3].add(stringList);
        /*GraphicsEnvironment ge;
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        String fnt[]=ge.getAvailableFontFamilyNames();
        for(int i=220;i<fnt.length-2;i++)
            StringList.addItem(fnt[i]);
        */
        
        /*新增其他功能按鈕*/
        jBtn = new JButton[btnName.length];
        for (int i = 0; i < btnName.length; i++) {
            jBtn[i] = new JButton();
            jBtn[i].setToolTipText(btnName[i][1]);
            jBtn[i].setIcon(new ImageIcon(this.getClass().getResource(btnImage[i])));
            jBtn[i].addActionListener(this);
            jBtn[i].setFocusable(false);
            jBtn[i].setPreferredSize(new Dimension(32,32));
            toolBar[3].add(jBtn[i]);
        }

        /*新增調色盤按鈕*/
        ButtonGroup color_ButtonGroup = new ButtonGroup();
        colorJTBtn = new JToggleButton[2];
        colorPanel = new JPanel[2];
        colorsBtn = new JButton[colors.length];
        for (int i = 0; i < 2; i++) {
            colorJTBtn[i] = new JToggleButton();
            colorPanel[i] = new JPanel();
            colorPanel[i].setBackground(colors[i]);
            colorJTBtn[i].setToolTipText(colorToolTip[i]);
            colorJTBtn[i].setFocusable(false);
            colorJTBtn[i].add(colorPanel[i]);
            colorJTBtn[0].setSelected(true);
            colorJTBtn[i].setPreferredSize(new Dimension(32,32));
            color_ButtonGroup.add(colorJTBtn[i]);
            toolBar[3].add(colorJTBtn[i]);
        }
        for (int i = 0; i < colors.length; i++) {
            colorsBtn[i] = new JButton();
            colorsBtn[i].setBackground(colors[i]);
            colorsBtn[i].setFocusable(false);
            colorsBtn[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            colorsBtn[i].addActionListener(this);
            colorsBtn[i].setPreferredSize(new Dimension(20,20));
            toolBar[4].add(colorsBtn[i]);
        }
        
        /*ToolBar 版面設置*/
        toolBar[0].add(toolBar[1]);
        toolBar[0].add(toolBar[2]);
        toolBar[0].add(toolBar[3]);
        toolBar[0].add(toolBar[4]);
        this.add(toolBar[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < penBtnName.length; i++) {
            if (e.getSource() == pen_JTBtn[i]) {
                parant.page.status = Status.valueOf(penBtnName[i][0]);
            }
        }
        
        if (e.getSource() == fill_JTBtn) {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            parant.page.isFill = selected;
        }
        
        for (int i = 0; i < shapeBtnName.length; i++) {
            if (e.getSource() == shape_JTBtn[i]) {
                parant.page.status = Status.valueOf(shapeBtnName[i][0]);
            }
        }
        for (int i = 0; i < colors.length; i++) {
            if (e.getSource() == colorsBtn[i]) {
                if (colorJTBtn[0].isSelected()) {
                    colorPanel[0].setBackground(colors[i]);
                } else {
                    colorPanel[1].setBackground(colors[i]);
                }
            }
        }

        if (e.getSource() == lineWidthList) {
            parant.page.SetStroke(lineWidth_px[lineWidthList.getSelectedIndex()]);
        }
        
        if (e.getSource() == stringList) {
            parant.page.SetString(stringList.getSelectedItem().toString());
        }
        
        if (e.getSource() == jBtn[0]) {
            parant.page.Undo();
        }
        
        if (e.getSource() == jBtn[1]) {
            parant.page.ChooseColor();
        }
    }
}
