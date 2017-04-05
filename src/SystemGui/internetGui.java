package SystemGui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class internetGui extends JPanel {
//    public static void main(String[] a){
//        try{internetGui gui = new internetGui();
//        gui.test();}
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void test() throws Exception {
        // TODO Auto-generated method stub

        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame("");
//                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    frame.getContentPane().add(new internetGui(), BorderLayout.CENTER);
                    frame.setSize(800, 600);
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        NativeInterface.runEventPump();
    }

    public internetGui() throws IOException, URISyntaxException {
        super(new BorderLayout());
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("网络查询"));

        final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.navigate("http://book.douban.com");
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));

//         webBrowser.setBarsVisible(false); //显示前进返回刷新按钮
        add(buttonPanel, BorderLayout.SOUTH);
        webBrowserPanel.setVisible(true);

    }
}