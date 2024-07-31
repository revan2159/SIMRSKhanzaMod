

package keuangan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public final class DlgLhtPembayaranPihakKe3BankMandiri extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private double total=0;
    private Scanner sc;
    private StringBuffer data;
    private String f,json="";
    private javax.swing.JFileChooser jfc = new JFileChooser();   
    private JsonNode root;
    private ObjectMapper mapper = new ObjectMapper();

    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgLhtPembayaranPihakKe3BankMandiri(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);
        Object[] rowRwJlDr={
            "No.Pembayaran","Tgl.Pembayaran","No.Rekening Sumber","No.Rekening Tujuan","Nama Rekening Tujuan","Kota/Kabupaten",
            "Nominal","Nomor Tagihan","Metode Pembayaran","Bank Tujuan","Kode Transaksi","Asal Transaksi","Status Transaksi"
        };
        tabMode=new DefaultTableModel(null,rowRwJlDr){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 13; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(115);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(120);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(200);
            }else if(i==10){
                column.setPreferredWidth(86);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(90);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppMT940 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new javax.swing.JLabel();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppMT940.setBackground(new java.awt.Color(255, 255, 254));
        ppMT940.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppMT940.setForeground(new java.awt.Color(50, 50, 50));
        ppMT940.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppMT940.setText("Eksekusi File MT940");
        ppMT940.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppMT940.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppMT940.setName("ppMT940"); // NOI18N
        ppMT940.setPreferredSize(new java.awt.Dimension(160, 26));
        ppMT940.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppMT940ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppMT940);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pembayaran Pihak Ke 3 Bank Mandiri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setComponentPopupMenu(jPopupMenu1);
        tbBangsal.setName("tbBangsal"); // NOI18N
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(28, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        label17.setText("Key Word :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass5.add(label17);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(150, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(50, 50, 50));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel10);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptPembayaranPihakke3BankMandiri.jasper","report","::[ Data Pembayaran Pihak Ke 3 Bank Mandiri ]::",
               "select pembayaran_pihak_ke3_bankmandiri.nomor_pembayaran,pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran,pembayaran_pihak_ke3_bankmandiri.no_rekening_sumber,"+
               "pembayaran_pihak_ke3_bankmandiri.no_rekening_tujuan,pembayaran_pihak_ke3_bankmandiri.atas_nama_rekening_tujuan,pembayaran_pihak_ke3_bankmandiri.kota_atas_nama_rekening_tujuan,"+
               "pembayaran_pihak_ke3_bankmandiri.nominal_pembayaran,pembayaran_pihak_ke3_bankmandiri.nomor_tagihan,pembayaran_pihak_ke3_bankmandiri.kode_metode,metode_pembayaran_bankmandiri.nama_metode,"+
               "pembayaran_pihak_ke3_bankmandiri.kode_bank,bank_tujuan_transfer_bankmandiri.nama_bank,pembayaran_pihak_ke3_bankmandiri.kode_transaksi,pembayaran_pihak_ke3_bankmandiri.asal_transaksi,pembayaran_pihak_ke3_bankmandiri.status_transaksi "+
               "from pembayaran_pihak_ke3_bankmandiri inner join metode_pembayaran_bankmandiri on metode_pembayaran_bankmandiri.kode_metode=pembayaran_pihak_ke3_bankmandiri.kode_metode "+
               "inner join bank_tujuan_transfer_bankmandiri on bank_tujuan_transfer_bankmandiri.kode_bank=pembayaran_pihak_ke3_bankmandiri.kode_bank where "+
               "pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran between '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:01' and '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59' "+
               (TCari.getText().equals("")?"":"and (pembayaran_pihak_ke3_bankmandiri.nomor_pembayaran like '%"+TCari.getText().trim()+"%' or pembayaran_pihak_ke3_bankmandiri.no_rekening_tujuan like '%"+TCari.getText().trim()+"%' "+
               "or pembayaran_pihak_ke3_bankmandiri.atas_nama_rekening_tujuan like '%"+TCari.getText().trim()+"%' or pembayaran_pihak_ke3_bankmandiri.status_transaksi like '%"+TCari.getText().trim()+"%' "+
               "or pembayaran_pihak_ke3_bankmandiri.nomor_tagihan like '%"+TCari.getText().trim()+"%' "+
               "or metode_pembayaran_bankmandiri.nama_metode like '%"+TCari.getText().trim()+"%' or pembayaran_pihak_ke3_bankmandiri.kode_transaksi like '%"+TCari.getText().trim()+"%' or "+
               "bank_tujuan_transfer_bankmandiri.nama_bank like '%"+TCari.getText().trim()+"%' or pembayaran_pihak_ke3_bankmandiri.asal_transaksi like '%"+TCari.getText().trim()+"%') ")+
               "order by pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnAll,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();

}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
        }else{
            Valid.pindah(evt, TKd, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void ppMT940ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppMT940ActionPerformed
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile().toString();
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                sc = new Scanner(new File(f));  
                sc.useDelimiter(":");
                data=new StringBuffer();
                json="";
                while (sc.hasNext()){  
                    json=sc.nextLine();
                    if(json.contains(":61:")||json.contains(":86:")){
                        data.append(json+";");
                    }
                }   
                json="{"+data.toString().replaceAll(";:86:","\",\"referensi\":\"").replaceAll(";:61:","\"},{\"transaksi\":\"").replaceAll(":61:","\"transaksi\":\"")+"}";
                json="{\"data\":["+json.substring(0,json.length()-2)+"\"}]}";
                sc.close();   
                System.out.println(json);
                if(!json.equals("")){
                    root = mapper.readTree(json);
                    if(root.path("data").isArray()){
                        for(JsonNode list:root.path("data")){
                            if(list.path("transaksi").asText().substring(6,7).equals("D")){
                                System.out.println("Melakukan pemrosesan \"Terkonfirmasi\" transaksi "+list.path("referensi").asText());
                                Sequel.mengedit3("pembayaran_pihak_ke3_bankmandiri","nomor_pembayaran=?","status_transaksi='Terkonfirmasi'",1,new String[]{
                                    list.path("referensi").asText()
                                });
                            }else if(list.path("transaksi").asText().substring(6,7).equals("R")){
                                if(list.path("transaksi").asText().substring(7,8).equals("D")){
                                    System.out.println("Melakukan pemrosesan \"Pembayaran Gagal\" transaksi "+list.path("referensi").asText());
                                    Sequel.mengedit3("pembayaran_pihak_ke3_bankmandiri","nomor_pembayaran=?","status_transaksi='Pembayaran Gagal'",1,new String[]{
                                        list.path("referensi").asText()
                                    });
                                }
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Proses eksekusi file MT940 sudah selesai, silahkan cek kembali daftar transaksi..");
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppMT940ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLhtPembayaranPihakKe3BankMandiri dialog = new DlgLhtPembayaranPihakKe3BankMandiri(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private javax.swing.JMenuItem ppMT940;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil(){
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                   "select pembayaran_pihak_ke3_bankmandiri.nomor_pembayaran,pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran,pembayaran_pihak_ke3_bankmandiri.no_rekening_sumber,"+
                   "pembayaran_pihak_ke3_bankmandiri.no_rekening_tujuan,pembayaran_pihak_ke3_bankmandiri.atas_nama_rekening_tujuan,pembayaran_pihak_ke3_bankmandiri.kota_atas_nama_rekening_tujuan,"+
                   "pembayaran_pihak_ke3_bankmandiri.nominal_pembayaran,pembayaran_pihak_ke3_bankmandiri.nomor_tagihan,metode_pembayaran_bankmandiri.nama_metode,bank_tujuan_transfer_bankmandiri.nama_bank,"+
                   "pembayaran_pihak_ke3_bankmandiri.kode_transaksi,pembayaran_pihak_ke3_bankmandiri.asal_transaksi,pembayaran_pihak_ke3_bankmandiri.status_transaksi "+
                   "from pembayaran_pihak_ke3_bankmandiri inner join metode_pembayaran_bankmandiri on metode_pembayaran_bankmandiri.kode_metode=pembayaran_pihak_ke3_bankmandiri.kode_metode "+
                   "inner join bank_tujuan_transfer_bankmandiri on bank_tujuan_transfer_bankmandiri.kode_bank=pembayaran_pihak_ke3_bankmandiri.kode_bank where pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran between ? and ? "+
                   (TCari.getText().equals("")?"":"and (pembayaran_pihak_ke3_bankmandiri.nomor_pembayaran like ? or pembayaran_pihak_ke3_bankmandiri.no_rekening_tujuan like ? or "+
                   "pembayaran_pihak_ke3_bankmandiri.atas_nama_rekening_tujuan like ? or pembayaran_pihak_ke3_bankmandiri.status_transaksi like ? or pembayaran_pihak_ke3_bankmandiri.nomor_tagihan like ? or "+
                   "metode_pembayaran_bankmandiri.nama_metode like ? or pembayaran_pihak_ke3_bankmandiri.kode_transaksi like ? or "+
                   "bank_tujuan_transfer_bankmandiri.nama_bank like ? or pembayaran_pihak_ke3_bankmandiri.asal_transaksi like ?) ")+"order by pembayaran_pihak_ke3_bankmandiri.tgl_pembayaran");
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:01");
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3,"%"+TCari.getText().trim()+"%");
                    ps.setString(4,"%"+TCari.getText().trim()+"%");
                    ps.setString(5,"%"+TCari.getText().trim()+"%");
                    ps.setString(6,"%"+TCari.getText().trim()+"%");
                    ps.setString(7,"%"+TCari.getText().trim()+"%");
                    ps.setString(8,"%"+TCari.getText().trim()+"%");
                    ps.setString(9,"%"+TCari.getText().trim()+"%");
                    ps.setString(10,"%"+TCari.getText().trim()+"%");
                    ps.setString(11,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps.executeQuery();
                total=0;
                while(rs.next()){
                    total=total+rs.getDouble("nominal_pembayaran");
                    tabMode.addRow(new Object[]{
                        rs.getString("nomor_pembayaran"),rs.getString("tgl_pembayaran"),rs.getString("no_rekening_sumber"),rs.getString("no_rekening_tujuan"),rs.getString("atas_nama_rekening_tujuan"),rs.getString("kota_atas_nama_rekening_tujuan"),
                        Valid.SetAngka(rs.getDouble("nominal_pembayaran")),rs.getString("nomor_tagihan"),rs.getString("nama_metode"),rs.getString("nama_bank"),rs.getString("kode_transaksi"),rs.getString("asal_transaksi"),rs.getString("status_transaksi")
                    });
                }
                if(total>0){
                    tabMode.addRow(new Object[]{
                        "Total :","","","","","",Valid.SetAngka(total),"","","","","",""
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
}