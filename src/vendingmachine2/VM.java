/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine2;

import galtox.Time.TimeScan;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Saragih ukuran icon 40x60
 *
 */
public class VM extends javax.swing.JFrame {

    private boolean moneyValidator() {
        boolean moneyValid = false;

        /*Salah IF
        if (("50000".equals(txt_money.getText())) && (rbtn_biru.isSelected())) {
            moneyValid = true;
        } else if (("20000".equals(txt_money.getText())) && (rbtn_hijau.isSelected())) {
            moneyValid = true;
        } else if (("10000".equals(txt_money.getText())) && (rbtn_ungu.isSelected())) {
            moneyValid = true;
        } else if (("5000".equals(txt_money.getText())) && (rbtn_coklat.isSelected())) {
            moneyValid = true;
        }*/
        if ((("50000".equals(txt_money.getText())) && (rbtn_biru.isSelected()))
                || (("20000".equals(txt_money.getText())) && (rbtn_hijau.isSelected()))
                || (("10000".equals(txt_money.getText())) && (rbtn_ungu.isSelected()))
                || (("5000".equals(txt_money.getText())) && (rbtn_coklat.isSelected()))) {
            moneyValid = true;
        }

        return moneyValid;
    }

    // Bagian Timer untuk batas VM menunggu penggunaan uang
    private Timer timer;
    private int seconds = 0, minutes = 5, hours = 0;
    private int interval = 1000;

    public void start() {
        timer.start();

    }

    public void stop() {
        timer.stop();

    }

    public void moneyStat()throws IOException{
        //format txt uang (totalMoney , totalSheet, sheet5K, sheet10K, sheet20K, sheet50K)
        
        String record, record2, currentStock, nameProduk, nameProduk2, tmpID;
        int totalMoney = 0;
        int totalSheet = 0;
        int sheet5K = 0;
        int sheet10K = 0;
        int sheet20K = 0;
        int sheet50K = 0;

        
        File db = new File("money.txt");
        File tempDB = new File("tmp_money.txt");

        BufferedReader br = new BufferedReader( new FileReader(db) );
        BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
        
            while ((record = br.readLine()) != null) {
                
                StringTokenizer st = new StringTokenizer(record, ",");
                
                totalMoney = Integer.parseInt(st.nextToken());
                totalSheet = Integer.parseInt(st.nextToken());
                sheet5K = Integer.parseInt(st.nextToken());
                sheet10K = Integer.parseInt(st.nextToken());
                sheet20K = Integer.parseInt(st.nextToken());
                sheet50K = Integer.parseInt(st.nextToken());
                
            }
        br.close();
        
        /**
         * *
         */
        totalMoney = totalMoney + Integer.parseInt(txt_money.getText());
        totalSheet++;
        
        if(null != txt_money.getText())switch (txt_money.getText()) {
            case "5000":
                sheet5K++;
                break;
            case "10000":
                sheet10K++;
                break;
            case "20000":
                sheet20K++;            
                break;
            case "50000":
                sheet50K++;
                break;
            default:
                break;
        }

        bw.write(totalMoney+","+totalSheet+","+sheet5K+","+sheet10K+","+sheet20K+","+sheet50K);
        bw.flush();
	bw.newLine();
	bw.close();		

        db.delete();
        tempDB.renameTo(db);
        
    }
    
    private void initMulai() {
        timer = new Timer(interval, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (seconds == 0 && (minutes > 0 || hours > 0)) {
                    seconds = 59;
                    minutes--;
                } else {
                    seconds--;
                }
                if (minutes == 0 && hours > 0) {
                    minutes = 59;
                    hours--;
                }
                if (seconds == 0 && minutes == 0 && hours == 0) {
                    stop();
                    seconds = minutes = hours = 0;
                    txtRemaining.setText("00:00");
                    lbl_money.setText("0");
                }
                TimeScan ts = new TimeScan(seconds, minutes, hours);
                if (minutes >= 0 && hours > 0 && seconds >= 0) {
                    txtRemaining.setText(ts.getHours() + ":" + ts.getMinutes() + ":" + ts.getSeconds());
                } else if (minutes > 0 && seconds >= 0 && hours <= 0) {
                    txtRemaining.setText(ts.getMinutes() + ":" + ts.getSeconds());
                } else if (minutes == 0 && hours == 0 && seconds > 0) {
                    if (seconds > 10) {
                        txtRemaining.setText(ts.getSeconds());
                    } else {
                        txtRemaining.setText(String.valueOf(seconds));
                        txtRemaining.setForeground(Color.red);
                    }
                }
            }
        });
    }

    //akhir timer batas penunggu uang
    /**
     * Creates new form VM
     */
    public VM() {
        initComponents();
        btn_ambil.setVisible(false);
        visibot_sprite.setVisible(false);
        visibot_coca.setVisible(false);
        visibot_aw.setVisible(false);
        visibot_aquarius.setVisible(false);
        visibot_minute.setVisible(false);
        visibot_ciel.setVisible(false);
        visibot_tab.setVisible(false);
        visibot_burn.setVisible(false);
        visibot_fanta.setVisible(false);
        visibot_friesca.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblharga_aquarius = new javax.swing.JLabel();
        lblharga_sprite = new javax.swing.JLabel();
        lblharga_aw = new javax.swing.JLabel();
        lblharga_coca = new javax.swing.JLabel();
        lblharga_minute = new javax.swing.JLabel();
        lblharga_ciel = new javax.swing.JLabel();
        lblharga_tebs = new javax.swing.JLabel();
        lblharga_burn = new javax.swing.JLabel();
        lblharga_fanta = new javax.swing.JLabel();
        lblharga_friesca = new javax.swing.JLabel();
        btn_sprite = new javax.swing.JButton();
        btn_aquarius = new javax.swing.JButton();
        btn_coca = new javax.swing.JButton();
        btn_aw = new javax.swing.JButton();
        btn_minute = new javax.swing.JButton();
        btn_ciel = new javax.swing.JButton();
        btn_tebs = new javax.swing.JButton();
        btn_burn = new javax.swing.JButton();
        btn_fanta = new javax.swing.JButton();
        btn_friesca = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_money = new java.awt.TextField();
        btn_insertmoney = new java.awt.Button();
        rbtn_coklat = new javax.swing.JRadioButton();
        rbtn_ungu = new javax.swing.JRadioButton();
        rbtn_biru = new javax.swing.JRadioButton();
        rbtn_hijau = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        lbl_money = new java.awt.Label();
        txtRemaining = new javax.swing.JTextField();
        visibot_aquarius = new javax.swing.JLabel();
        icon_coca = new javax.swing.JLabel();
        icon_aw = new javax.swing.JLabel();
        visibot_minute = new javax.swing.JLabel();
        visibot_ciel = new javax.swing.JLabel();
        visibot_tab = new javax.swing.JLabel();
        icon_fanta = new javax.swing.JLabel();
        visibot_friesca = new javax.swing.JLabel();
        icon_aquarius1 = new javax.swing.JLabel();
        icon_aquarius2 = new javax.swing.JLabel();
        icon_sprite1 = new javax.swing.JLabel();
        icon_coca1 = new javax.swing.JLabel();
        visibot_coca = new javax.swing.JLabel();
        visibot_aw = new javax.swing.JLabel();
        icon_aw2 = new javax.swing.JLabel();
        icon_minute1 = new javax.swing.JLabel();
        icon_minute2 = new javax.swing.JLabel();
        icon_ciel1 = new javax.swing.JLabel();
        icon_ciel2 = new javax.swing.JLabel();
        icon_tebs1 = new javax.swing.JLabel();
        icon_tebs2 = new javax.swing.JLabel();
        visibot_burn = new javax.swing.JLabel();
        icon_burn2 = new javax.swing.JLabel();
        icon_burn3 = new javax.swing.JLabel();
        visibot_fanta = new javax.swing.JLabel();
        icon_fanta2 = new javax.swing.JLabel();
        icon_friesca1 = new javax.swing.JLabel();
        icon_friesca2 = new javax.swing.JLabel();
        visibot_sprite = new javax.swing.JLabel();
        icon_sprite3 = new javax.swing.JLabel();
        icon_coca3 = new javax.swing.JLabel();
        btn_ambil = new javax.swing.JButton();
        icon_aquarius3 = new javax.swing.JLabel();
        icon_aw3 = new javax.swing.JLabel();
        icon_minute3 = new javax.swing.JLabel();
        icon_ciel3 = new javax.swing.JLabel();
        icon_fanta3 = new javax.swing.JLabel();
        icon_friesca3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vending Machine Beta Version");
        setBackground(java.awt.SystemColor.activeCaption);
        setForeground(new java.awt.Color(255, 51, 51));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblharga_aquarius.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_aquarius.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_aquarius.setText("Rp 10.000");
        lblharga_aquarius.setOpaque(true);
        getContentPane().add(lblharga_aquarius, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        lblharga_sprite.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_sprite.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_sprite.setText("Rp 5.000");
        lblharga_sprite.setOpaque(true);
        getContentPane().add(lblharga_sprite, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        lblharga_aw.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_aw.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_aw.setText("Rp 5.000");
        lblharga_aw.setOpaque(true);
        getContentPane().add(lblharga_aw, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        lblharga_coca.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_coca.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_coca.setText("Rp 5.000");
        lblharga_coca.setOpaque(true);
        getContentPane().add(lblharga_coca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        lblharga_minute.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_minute.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_minute.setText("Rp 5.000");
        lblharga_minute.setOpaque(true);
        getContentPane().add(lblharga_minute, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        lblharga_ciel.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_ciel.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_ciel.setText("Rp 5.000");
        lblharga_ciel.setOpaque(true);
        getContentPane().add(lblharga_ciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        lblharga_tebs.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_tebs.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_tebs.setText("Rp 10.000");
        lblharga_tebs.setOpaque(true);
        getContentPane().add(lblharga_tebs, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        lblharga_burn.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_burn.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_burn.setText("Rp 10.000");
        lblharga_burn.setOpaque(true);
        getContentPane().add(lblharga_burn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, -1, -1));

        lblharga_fanta.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_fanta.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_fanta.setText("Rp 5.000");
        lblharga_fanta.setOpaque(true);
        getContentPane().add(lblharga_fanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, -1, -1));

        lblharga_friesca.setBackground(new java.awt.Color(255, 255, 255));
        lblharga_friesca.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        lblharga_friesca.setText("Rp 10.000");
        lblharga_friesca.setOpaque(true);
        getContentPane().add(lblharga_friesca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 640, -1, -1));

        btn_sprite.setText("Sprite");
        btn_sprite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_spriteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sprite, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 90, -1));

        btn_aquarius.setText("Aquarius");
        btn_aquarius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aquariusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_aquarius, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 100, -1));

        btn_coca.setText("Coca Cola");
        btn_coca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cocaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_coca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 90, -1));

        btn_aw.setText("AW");
        btn_aw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_awActionPerformed(evt);
            }
        });
        getContentPane().add(btn_aw, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 90, -1));

        btn_minute.setText("Minute Maid");
        btn_minute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minuteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_minute, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        btn_ciel.setText("Ciel");
        btn_ciel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cielActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 90, -1));

        btn_tebs.setText("Tebs");
        btn_tebs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tebsActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tebs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 90, -1));

        btn_burn.setText("Burn");
        btn_burn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_burnActionPerformed(evt);
            }
        });
        getContentPane().add(btn_burn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 90, -1));

        btn_fanta.setText("Fanta");
        btn_fanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fantaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_fanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 90, -1));

        btn_friesca.setText("Friesca");
        btn_friesca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_friescaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_friesca, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 660, 90, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_money.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_moneyKeyTyped(evt);
            }
        });
        jPanel1.add(txt_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 27, 70, -1));

        btn_insertmoney.setLabel("insert money");
        btn_insertmoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertmoneyActionPerformed(evt);
            }
        });
        jPanel1.add(btn_insertmoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 23, -1, -1));

        rbtn_coklat.setText("Coklat");
        rbtn_coklat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_coklatActionPerformed(evt);
            }
        });
        jPanel1.add(rbtn_coklat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        rbtn_ungu.setText("Ungu");
        rbtn_ungu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_unguActionPerformed(evt);
            }
        });
        jPanel1.add(rbtn_ungu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        rbtn_biru.setText("Biru");
        rbtn_biru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_biruActionPerformed(evt);
            }
        });
        jPanel1.add(rbtn_biru, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        rbtn_hijau.setText("Hijau");
        rbtn_hijau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_hijauActionPerformed(evt);
            }
        });
        jPanel1.add(rbtn_hijau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 220, 120));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setText("ON SALE");
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        lbl_money.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        lbl_money.setText("0");
        jPanel2.add(lbl_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 190, -1));

        txtRemaining.setEditable(false);
        txtRemaining.setBackground(new java.awt.Color(255, 255, 255));
        txtRemaining.setFont(new java.awt.Font("Lucida Fax", 1, 11)); // NOI18N
        txtRemaining.setForeground(new java.awt.Color(102, 60, 130));
        txtRemaining.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRemaining.setText("00:00");
        jPanel2.add(txtRemaining, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 70, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 220, 100));

        visibot_aquarius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baquarius.png"))); // NOI18N
        getContentPane().add(visibot_aquarius, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 60, 90));

        icon_coca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_coca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bcola.png"))); // NOI18N
        getContentPane().add(icon_coca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 50, 90));

        icon_aw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kaw.png"))); // NOI18N
        getContentPane().add(icon_aw, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 90, -1));

        visibot_minute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bmaid.png"))); // NOI18N
        getContentPane().add(visibot_minute, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 120, 90));

        visibot_ciel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ciel.png"))); // NOI18N
        getContentPane().add(visibot_ciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 90, 80));

        visibot_tab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Btab.png"))); // NOI18N
        getContentPane().add(visibot_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 50, 80));

        icon_fanta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bfanta.png"))); // NOI18N
        getContentPane().add(icon_fanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 50, 90));

        visibot_friesca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fresca.png"))); // NOI18N
        getContentPane().add(visibot_friesca, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        icon_aquarius1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baquarius.png"))); // NOI18N
        getContentPane().add(icon_aquarius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, 90));

        icon_aquarius2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baquarius.png"))); // NOI18N
        getContentPane().add(icon_aquarius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 60, 100));

        icon_sprite1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprite.png"))); // NOI18N
        getContentPane().add(icon_sprite1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 100));

        icon_coca1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_coca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bcola.png"))); // NOI18N
        getContentPane().add(icon_coca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 50, 90));

        visibot_coca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        visibot_coca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bcola.png"))); // NOI18N
        getContentPane().add(visibot_coca, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 100, 110));

        visibot_aw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kaw.png"))); // NOI18N
        getContentPane().add(visibot_aw, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 90, -1));

        icon_aw2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kaw.png"))); // NOI18N
        getContentPane().add(icon_aw2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 90, -1));

        icon_minute1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bmaid.png"))); // NOI18N
        getContentPane().add(icon_minute1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 90, 70));

        icon_minute2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bmaid.png"))); // NOI18N
        getContentPane().add(icon_minute2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 90, 70));

        icon_ciel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ciel.png"))); // NOI18N
        getContentPane().add(icon_ciel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 90, 80));

        icon_ciel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ciel.png"))); // NOI18N
        getContentPane().add(icon_ciel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 90, 80));

        icon_tebs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Btab.png"))); // NOI18N
        getContentPane().add(icon_tebs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 50, 80));

        icon_tebs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Btab.png"))); // NOI18N
        getContentPane().add(icon_tebs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 50, 80));

        visibot_burn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/burn.png"))); // NOI18N
        getContentPane().add(visibot_burn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 60, 80));

        icon_burn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/burn.png"))); // NOI18N
        getContentPane().add(icon_burn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 60, 80));

        icon_burn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_burn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/burn.png"))); // NOI18N
        getContentPane().add(icon_burn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 50, 80));

        visibot_fanta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bfanta.png"))); // NOI18N
        getContentPane().add(visibot_fanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 50, 90));

        icon_fanta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_fanta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bfanta.png"))); // NOI18N
        getContentPane().add(icon_fanta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 50, 90));

        icon_friesca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fresca.png"))); // NOI18N
        getContentPane().add(icon_friesca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, 90, -1));

        icon_friesca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fresca.png"))); // NOI18N
        getContentPane().add(icon_friesca2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 90, -1));

        visibot_sprite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprite.png"))); // NOI18N
        getContentPane().add(visibot_sprite, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 60, 110));

        icon_sprite3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprite.png"))); // NOI18N
        getContentPane().add(icon_sprite3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 100));

        icon_coca3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_coca3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bcola.png"))); // NOI18N
        getContentPane().add(icon_coca3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 50, 90));

        btn_ambil.setText("Ambil");
        btn_ambil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ambilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ambil, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));

        icon_aquarius3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baquarius.png"))); // NOI18N
        getContentPane().add(icon_aquarius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 60, 90));

        icon_aw3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kaw.png"))); // NOI18N
        getContentPane().add(icon_aw3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 90, -1));

        icon_minute3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bmaid.png"))); // NOI18N
        getContentPane().add(icon_minute3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 90, 70));

        icon_ciel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ciel.png"))); // NOI18N
        getContentPane().add(icon_ciel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 90, 80));

        icon_fanta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bfanta.png"))); // NOI18N
        getContentPane().add(icon_fanta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 50, 90));

        icon_friesca3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fresca.png"))); // NOI18N
        getContentPane().add(icon_friesca3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, -1, -1));

        setSize(new java.awt.Dimension(584, 728));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertmoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertmoneyActionPerformed
        // masukkin uang
        //inisialisasi variabel
        int money = 0;
        boolean moneyValid = false;

        //panggil method moneyValid untuk mengecek keaslian uang secara sederhana
        moneyValid = moneyValidator();
        // jika hasil pengecheckan mengatakan uang asli maka uang dapat diterima
        if (true == moneyValid) {
            //apabila uang  dapat diterima maka uang masuk
            seconds = 0;
            minutes = 1;
            hours = 0;
            money = money + Integer.parseInt(txt_money.getText()) + Integer.parseInt(lbl_money.getText());
            lbl_money.setText(Integer.toString(money));
            try{moneyStat();}catch(IOException e){JOptionPane.showMessageDialog(this,e.getMessage());}
            initMulai();
            stop();
            start();
            //apabila uang tidak diterima unag keluar kembali
        } else {
            JOptionPane.showMessageDialog(null,"Uang tidak dapat diterima.", "Slug", JOptionPane.INFORMATION_MESSAGE);
        }

        //kembali ke kondisi awal yaitu kosong
        txt_money.setText(null);
        rbtn_biru.setSelected(false);
        rbtn_hijau.setSelected(false);
        rbtn_coklat.setSelected(false);
        rbtn_ungu.setSelected(false);

    }//GEN-LAST:event_btn_insertmoneyActionPerformed

    private void btn_spriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_spriteActionPerformed
        // Jika ditekan tombol sprite
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
            if ((lbl_money.getText()).equals("0")){
               stop();
            }
            
            visibot_sprite.setVisible(true);
            btn_ambil.setVisible(true);

            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_spriteActionPerformed

    private void rbtn_coklatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_coklatActionPerformed
        // cant choose double colour of money

        if (rbtn_coklat.isSelected()) {
            rbtn_biru.setSelected(false);
            rbtn_hijau.setSelected(false);
            rbtn_ungu.setSelected(false);
        }
    }//GEN-LAST:event_rbtn_coklatActionPerformed

    private void rbtn_hijauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_hijauActionPerformed
        // cant choose double colour of money

        if (rbtn_hijau.isSelected()) {
            rbtn_biru.setSelected(false);
            rbtn_coklat.setSelected(false);
            rbtn_ungu.setSelected(false);
        }
    }//GEN-LAST:event_rbtn_hijauActionPerformed

    private void rbtn_biruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_biruActionPerformed
        // cant choose double colour of money

        if (rbtn_biru.isSelected()) {
            rbtn_coklat.setSelected(false);
            rbtn_hijau.setSelected(false);
            rbtn_ungu.setSelected(false);
        }
    }//GEN-LAST:event_rbtn_biruActionPerformed

    private void rbtn_unguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_unguActionPerformed
        // cant choose double colour of money

        if (rbtn_ungu.isSelected()) {
            rbtn_biru.setSelected(false);
            rbtn_hijau.setSelected(false);
            rbtn_coklat.setSelected(false);
        }
    }//GEN-LAST:event_rbtn_unguActionPerformed

    private void btn_aquariusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aquariusActionPerformed
        // Jika ditekan tombol aquarius
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 10000) {

            money = Integer.parseInt(lbl_money.getText()) - 10000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_aquariusActionPerformed

    private void btn_cocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cocaActionPerformed
        // Jika ditekan tombol coca cola
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_cocaActionPerformed

    private void btn_awActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_awActionPerformed
        // Jika ditekan tombol AW
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_awActionPerformed

    private void btn_minuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minuteActionPerformed
        // Jika ditekan tombol minute maid
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_minuteActionPerformed

    private void btn_cielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cielActionPerformed
        // Jika ditekan tombol ciel
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_cielActionPerformed

    private void btn_tebsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tebsActionPerformed
        // Jika ditekan tombol tebs
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 10000) {

            money = Integer.parseInt(lbl_money.getText()) - 10000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_tebsActionPerformed

    private void btn_burnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_burnActionPerformed
        // Jika ditekan tombol burn
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 10000) {

            money = Integer.parseInt(lbl_money.getText()) - 10000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_burnActionPerformed

    private void btn_fantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fantaActionPerformed
        // Jika ditekan tombol fanta
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 5000) {

            money = Integer.parseInt(lbl_money.getText()) - 5000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_fantaActionPerformed

    private void btn_friescaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_friescaActionPerformed
        // Jika ditekan tombol friesca
        int money = 0;
        if  (Integer.parseInt(lbl_money.getText()) >= 10000) {

            money = Integer.parseInt(lbl_money.getText()) - 10000;

            lbl_money.setText(Integer.toString(money));
            
        } else if ((Integer.parseInt(lbl_money.getText()) <= 0 )) {
            JOptionPane.showMessageDialog(null,"Masukkan Uang Anda", "Display", JOptionPane.INFORMATION_MESSAGE);
         
        } else if ((Integer.parseInt(lbl_money.getText()) > 0 )&& (Integer.parseInt(lbl_money.getText()) < 10000)) {
            JOptionPane.showMessageDialog(null,"Uang tidak cukup.", "Display", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_friescaActionPerformed

    private void txt_moneyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_moneyKeyTyped
        // Tidak bisa input angka
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == java.awt.event.KeyEvent.VK_BACK_SPACE) || (karakter == java.awt.event.KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_moneyKeyTyped

    private void btn_ambilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ambilActionPerformed
        // TODO add your handling code here:
        visibot_sprite.setVisible(false);
        visibot_aquarius.setVisible(false);
        visibot_coca.setVisible(false);
        visibot_aw.setVisible(false);
        visibot_minute.setVisible(false);
        visibot_ciel.setVisible(false);
        visibot_tab.setVisible(false);
        visibot_burn.setVisible(false);
        visibot_fanta.setVisible(false);
        visibot_friesca.setVisible(false);
        btn_ambil.setVisible(false);
    }//GEN-LAST:event_btn_ambilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ambil;
    private javax.swing.JButton btn_aquarius;
    private javax.swing.JButton btn_aw;
    private javax.swing.JButton btn_burn;
    private javax.swing.JButton btn_ciel;
    private javax.swing.JButton btn_coca;
    private javax.swing.JButton btn_fanta;
    private javax.swing.JButton btn_friesca;
    private java.awt.Button btn_insertmoney;
    private javax.swing.JButton btn_minute;
    private javax.swing.JButton btn_sprite;
    private javax.swing.JButton btn_tebs;
    private javax.swing.JLabel icon_aquarius1;
    private javax.swing.JLabel icon_aquarius2;
    private javax.swing.JLabel icon_aquarius3;
    private javax.swing.JLabel icon_aw;
    private javax.swing.JLabel icon_aw2;
    private javax.swing.JLabel icon_aw3;
    private javax.swing.JLabel icon_burn2;
    private javax.swing.JLabel icon_burn3;
    private javax.swing.JLabel icon_ciel1;
    private javax.swing.JLabel icon_ciel2;
    private javax.swing.JLabel icon_ciel3;
    private javax.swing.JLabel icon_coca;
    private javax.swing.JLabel icon_coca1;
    private javax.swing.JLabel icon_coca3;
    private javax.swing.JLabel icon_fanta;
    private javax.swing.JLabel icon_fanta2;
    private javax.swing.JLabel icon_fanta3;
    private javax.swing.JLabel icon_friesca1;
    private javax.swing.JLabel icon_friesca2;
    private javax.swing.JLabel icon_friesca3;
    private javax.swing.JLabel icon_minute1;
    private javax.swing.JLabel icon_minute2;
    private javax.swing.JLabel icon_minute3;
    private javax.swing.JLabel icon_sprite1;
    private javax.swing.JLabel icon_sprite3;
    private javax.swing.JLabel icon_tebs1;
    private javax.swing.JLabel icon_tebs2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label1;
    private java.awt.Label lbl_money;
    private javax.swing.JLabel lblharga_aquarius;
    private javax.swing.JLabel lblharga_aw;
    private javax.swing.JLabel lblharga_burn;
    private javax.swing.JLabel lblharga_ciel;
    private javax.swing.JLabel lblharga_coca;
    private javax.swing.JLabel lblharga_fanta;
    private javax.swing.JLabel lblharga_friesca;
    private javax.swing.JLabel lblharga_minute;
    private javax.swing.JLabel lblharga_sprite;
    private javax.swing.JLabel lblharga_tebs;
    private javax.swing.JRadioButton rbtn_biru;
    private javax.swing.JRadioButton rbtn_coklat;
    private javax.swing.JRadioButton rbtn_hijau;
    private javax.swing.JRadioButton rbtn_ungu;
    private javax.swing.JTextField txtRemaining;
    private java.awt.TextField txt_money;
    private javax.swing.JLabel visibot_aquarius;
    private javax.swing.JLabel visibot_aw;
    private javax.swing.JLabel visibot_burn;
    private javax.swing.JLabel visibot_ciel;
    private javax.swing.JLabel visibot_coca;
    private javax.swing.JLabel visibot_fanta;
    private javax.swing.JLabel visibot_friesca;
    private javax.swing.JLabel visibot_minute;
    private javax.swing.JLabel visibot_sprite;
    private javax.swing.JLabel visibot_tab;
    // End of variables declaration//GEN-END:variables
}
