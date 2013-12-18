
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class ToolBar extends JPanel implements ActionListener {
    
    MainWindow parant;
    JToolBar[] toolBar;
    String toolBarName[] = {"畫筆", "形狀","粗細", "調色盤","其他" };
    
    /*畫筆按鈕*/
    JToggleButton[] pen_JTBtn;
    String penBtnName[][] = {{"Pen", "鉛筆，使用選取的線條寬度繪製任意形狀的線條"},
                             {"Eraser", "橡皮擦，清除圖片的的一部份，並以背景色彩取代"},
                             {"Select","待修正"}};
    
    String penImage[] = {"img/pencil.png", "img/eraser.png","img/eraser.png"};
    
    /*填滿按鈕*/
    JToggleButton fill_JTBtn;
    
    /*選取按鈕*/
    JToggleButton select_JTBtn;
    
    /*形狀按鈕*/
    JToggleButton[] shape_JTBtn;
    String shapeBtnName[][] = {{"Line", "直線"}, {"Rectangle", "矩形"}, {"Round_Rectangle", "圓角矩形"}, {"Oval", "橢圓形"}};
    String shapeImage[] = {"img/line.png", "img/rectangle.png", "img/round_rectangle.png", "img/oval.png"};
    
    /*線條設定*/
    String[] lineWidth = {"▁▁","▃▃","▅▅","▇▇"};
    Integer[] lineWidth_px =  {2, 5, 8, 11};
    JComboBox lineWidthList;
    
    /*字形設定*/
    String[] strings = {"Ariel", "Myriad", "Time New Roman",
                        "標楷體", "新細明體", "微軟正黑體"};
    JComboBox stringList;

    /*功能按鈕*/
    JButton[] jBtn;
    String btnName[][] = {{"復原","復原(Ctrl+Z) 復原上次的動作"},
                          {"顏色","編輯色彩，從調色盤選取色彩"}};
    
    String btnImage[] = {"img/undo.png", "img/color.png"};

    /*調色盤按鈕*/
    static JToggleButton[] colorJTBtn;
    static JPanel setcolorPanel[],selectColorPanel[];
    static JButton[] colorsBtn;
    String colorToolTip[] = {"色彩1(前景色彩)，按一下此處，然後從調色盤選取色彩，"
                               + "鉛筆圖形都會使用此色彩","色彩2(背景色彩)，"
                               + "按一下此處，然後從調色盤選取色彩，橡皮擦會使用此色彩"};
    Color colors[] = {Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, 
                       Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN,
                       Color.MAGENTA, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY};
    ToolBar(MainWindow p) {
        parant = p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(255, 250, 240));
        
        toolBar = new JToolBar[toolBarName.length];
        for (int i = 0; i < toolBarName.length; i++) {
            toolBar[i] = new JToolBar(/*toolBarName[i], JToolBar.VERTICAL*/);
            toolBar[i].setFloatable(false);
            //toolBar[i].setLayout(new GridLayout(1, 1));
            //toolBar[i].setBackground(new Color(255, 250, 240));
        }
        
        
        JToolBar[] storebar;
        storebar = new JToolBar[3];
        for(int i= 0;i<3;i++)
        {
            storebar[i] = new JToolBar();
            storebar[i].setFloatable(false);
            storebar[i].setLayout(new BorderLayout());
            storebar[i].add(BorderLayout.CENTER,toolBar[i]);
            storebar[i].add(BorderLayout.SOUTH,new JLabel(toolBarName[i],JLabel.CENTER));
            ToolBar.this.add(storebar[i]);
        }
        
        
        

        /*新增畫筆按鈕*/
        ButtonGroup buttonGroup = new ButtonGroup();
        pen_JTBtn = new JToggleButton[penBtnName.length];
        for (int i = 0; i < penBtnName.length; i++) {
            pen_JTBtn[i] = new JToggleButton();
            pen_JTBtn[i].setToolTipText(penBtnName[i][1]);
            pen_JTBtn[i].setIcon(new ImageIcon(this.getClass().getResource(penImage[i])));
            pen_JTBtn[i].setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
            pen_JTBtn[i].addActionListener(this);
            pen_JTBtn[i].setFocusable(false);
            pen_JTBtn[0].setSelected(true);
            buttonGroup.add(pen_JTBtn[i]);
            toolBar[0].add(pen_JTBtn[i]);
        }
        
        /*新增填滿按鈕*/
        fill_JTBtn = new JToggleButton();
        fill_JTBtn.setIcon(new ImageIcon(this.getClass().getResource("img/fill.png")));
        fill_JTBtn.addActionListener(this);
        fill_JTBtn.setFocusable(false);
        toolBar[0].add(fill_JTBtn);
        
        /*新增選取按鈕*//*
        select_JTBtn = new JToggleButton("選");
        select_JTBtn.addActionListener(this);
        select_JTBtn.setFocusable(false);
        buttonGroup.add(select_JTBtn);
        toolBar[0].add(select_JTBtn);*/
        
        /*新增形狀按鈕*/
        shape_JTBtn = new JToggleButton[shapeBtnName.length];
        for (int i = 0; i < shapeBtnName.length; i++) {
            shape_JTBtn[i] = new JToggleButton();
            shape_JTBtn[i].setToolTipText(shapeBtnName[i][1]);
            shape_JTBtn[i].setIcon(new ImageIcon(this.getClass().getResource(shapeImage[i])));
            shape_JTBtn[i].setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
            shape_JTBtn[i].addActionListener(this);
            shape_JTBtn[i].setFocusable(false);
            buttonGroup.add(shape_JTBtn[i]);
            toolBar[1].add(shape_JTBtn[i]);
        }
        
        /*新增線條粗細設置*/
        lineWidthList = new JComboBox(lineWidth);
        lineWidthList.addActionListener(this);
        lineWidthList.setToolTipText("大小(Ctrl++, Ctrl+-)");
        lineWidthList.setBorder(BorderFactory.createEmptyBorder(14,9,14,9));
        toolBar[2].add(lineWidthList);
        
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
        


        /*新增調色盤按鈕*/
        ButtonGroup color_ButtonGroup = new ButtonGroup();
        colorJTBtn = new JToggleButton[2];
        setcolorPanel = new JPanel[2];
        colorsBtn = new JButton[colors.length];
        for (int i = 0; i < 2; i++) {
            colorJTBtn[i] = new JToggleButton();
            setcolorPanel[i] = new JPanel();
            setcolorPanel[i].setBorder(BorderFactory.createEmptyBorder(6,0,16,0));
            setcolorPanel[i].setBackground(colors[i]);
            
            
            colorJTBtn[i].setLayout(new GridLayout(2,1));
            colorJTBtn[i].add(setcolorPanel[i]);
            colorJTBtn[i].add(new JLabel("色彩"+(i+1)));
            colorJTBtn[i].setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            
            colorJTBtn[i].setToolTipText(colorToolTip[i]);
            colorJTBtn[i].setFocusable(false);
            colorJTBtn[0].setSelected(true);
            color_ButtonGroup.add(colorJTBtn[i]);
            toolBar[3].add(colorJTBtn[i]);
        }
        
        selectColorPanel = new JPanel[colors.length];
        JToolBar colorbar = new JToolBar() ;
        for (int i = 0; i < colors.length; i++) {
            selectColorPanel[i] = new JPanel();
            colorsBtn[i] = new JButton();
            colorbar.setFloatable(false);
            colorbar.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            selectColorPanel[i].setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            selectColorPanel[i].setBackground(colors[i]);
            
            colorsBtn[i].add(selectColorPanel[i]);
            colorsBtn[i].setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            colorsBtn[i].setFocusable(false);
            colorsBtn[i].addActionListener(this);
            colorbar.add(colorsBtn[i]);
        }
        toolBar[3].add(colorbar);
        
        /*新增其他功能按鈕*/
        jBtn = new JButton[btnName.length];
        for (int i = 0; i < btnName.length; i++) {
            jBtn[i] = new JButton();
            jBtn[i].setToolTipText(btnName[i][1]);
            jBtn[i].setIcon(new ImageIcon(this.getClass().getResource(btnImage[i])));
            jBtn[i].addActionListener(this);
            jBtn[i].setFocusable(false);
            jBtn[i].setPreferredSize(new Dimension(32,32));
            toolBar[4].add(jBtn[i]);
        }
        
        
        /*ToolBar 版面設置*/
        //add(toolBar[0]);
        //add(toolBar[1]);
       // add(toolBar[2]);
        add(toolBar[3]);
        add(toolBar[4]);
        
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
        
        if (e.getSource() == select_JTBtn) {
            parant.page.status = Status.Select;
        }
        
        for (int i = 0; i < shapeBtnName.length; i++) {
            if (e.getSource() == shape_JTBtn[i]) {
                parant.page.status = Status.valueOf(shapeBtnName[i][0]);
            }
        }
        for (int i = 0; i < colors.length; i++) {
            if (e.getSource() == colorsBtn[i]) {
                if (colorJTBtn[0].isSelected()) {
                    setcolorPanel[0].setBackground(colors[i]);
                } else {
                    setcolorPanel[1].setBackground(colors[i]);
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
